/**
 * @title: newmerchant-xapi
 * @file: TokenInterceptor.java
 * @package: com.wanda.application.xapi.newmerchant.web.interceptor
 * @description: token拦截器
 * @author: jinyi10
 * @email: jinyi10@wanda.cn
 * @time: 2017年3月14日 下午2:55:16
 * @Copyright (c) 2017, Wanda.cn All right reserved.
 */

package com.wdk.code.general.server.web.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.wdk.code.general.server.redis.RedisStringDao;
import com.wdk.code.general.server.service.DbMessagesService;
import com.wdk.code.general.server.service.ProjectMetadataService;
import com.wdk.code.general.server.web.args.DbMessagesArgs;
import com.wdk.code.general.server.web.args.ProjectMetadataArgs;
import com.wdk.code.general.server.web.vo.DbMessagesVo;
import com.wdk.code.general.server.web.vo.ProjectMetadataVo;
import com.wdk.general.core.common.model.UserContext;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.model.ProjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by loutao on 2018/6/20.
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Value("${filePath}")
    private String filePath;

    @Autowired
    private RedisStringDao redisStringDao;

    @Autowired
    private ProjectMetadataService projectMetadataService;

    @Autowired
    private DbMessagesService dbMessagesService;

    private static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /* 解决跨域请求问题 */
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "HEAD,POST,GET,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization,Cache-Control,Pragma,Content-Type");

        UserContext.current().setRequest(request);
        UserContext.current().setResponse(response);
        String traceId = request.getSession().getId();

        UserContext.current().setTraceId(traceId);
        long start = System.currentTimeMillis();
        UserContext.current().setStartTime(start);

        String requestURI = request.getRequestURI();
        logger.info("requestURI->{}", requestURI);
        if (StringUtils.isEmpty(requestURI)) {
            response.sendRedirect("/wdk-code-general-server/main/login");
            return false;
        }

        if (requestURI.contains("/login")
                || requestURI.contains("/wechat")
                || requestURI.contains("/error")
                || requestURI.contains("/register")
                || requestURI.contains("/menus")
                || requestURI.contains("/static/")
                || requestURI.contains("/api/")) {
            return true;
        } else {
            return preHandleService(request, response);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long start = UserContext.current().getStartTime();
        long duration = System.currentTimeMillis() - start;
        if (duration > 3000) {
            logger.info("超时报警短信【__trace_id：{}】【duration：{}】", UserContext.current().getTraceId(), duration);
        }
        UserContext.release();
    }

    private boolean preHandleService(HttpServletRequest request, HttpServletResponse response) {

        /**
         * 处理用户信息
         */
        if (!initUserInfo(request, response)) {
            return false;
        }

        /**
         * 处理跳转信息
         */
        if (!sendRedirect(request, response)) {
            return false;
        }

        //处理数据源信息
        initDbMessage();

        //处理项目信息
        initProjectMetadata();

        return true;
    }

    /**
     * 跳转处理
     *
     * @param request
     * @param response
     */
    public boolean sendRedirect(HttpServletRequest request, HttpServletResponse response) {


        if (null != request.getSession().getAttribute("redirect")) {
            String redirect = (String) request.getSession().getAttribute("redirect");
            request.getSession().setAttribute("redirect", null);
            try {
                response.sendRedirect(redirect);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        return true;
    }

    /**
     * 处理用户信息
     *
     * @param request
     * @param response
     * @return
     */
    public boolean initUserInfo(HttpServletRequest request, HttpServletResponse response) {

        Integer userId = (Integer) request.getSession().getAttribute("userId");
        UserContext.current().setUserId(userId);

        String username = (String) request.getSession().getAttribute("username");
        if (StringUtils.isEmpty(username)) {
            try {
                if (null == request.getSession().getAttribute("redirect")) {
                    logger.info("redirect url:{}", request.getRequestURI());
                    request.getSession().setAttribute("redirect", request.getRequestURI());
                }
                response.sendRedirect("/wdk-code-general-server/main/login");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }

        UserContext.current().setUsername(username);
        return true;
    }

    /**
     * 处理项目信息
     */
    public void initProjectMetadata() {

        ProjectMetadata projectMetadata = JSON.parseObject(redisStringDao.get("pm_" + UserContext.current().getUsername()), ProjectMetadata.class);

        if (null != projectMetadata) {
            UserContext.current().setProjectMetadata(projectMetadata);
            UserContext.current().setProjectRoot(filePath + "/" + projectMetadata.getProjectName());
        } else {
            ProjectMetadataArgs projectMetadataArgs = new ProjectMetadataArgs();
            projectMetadataArgs.setUserId(UserContext.current().getUserId());
            List<ProjectMetadataVo> list = projectMetadataService.list(projectMetadataArgs);
            if (list.size() > 0) {
                projectMetadata = new ProjectMetadata();
                BeanUtils.copyProperties(list.get(0), projectMetadata);
                UserContext.current().setProjectMetadata(projectMetadata);
                UserContext.current().setProjectRoot(filePath + "/" + projectMetadata.getProjectName());
            }

        }
    }


    /**
     * 处理数据源信息
     */
    public void initDbMessage() {

        DbMessage dbMessage = JSON.parseObject(redisStringDao.get("db_" + UserContext.current().getUsername()), DbMessage.class);

        if (null != dbMessage) {
            UserContext.current().setDbMessage(dbMessage);
        } else {
            DbMessagesArgs dbMessagesArgs = new DbMessagesArgs();
            dbMessagesArgs.setUserId(UserContext.current().getUserId());
            List<DbMessagesVo> list = dbMessagesService.list(dbMessagesArgs);
            if (list.size() > 0) {
                dbMessage = new DbMessage();
                BeanUtils.copyProperties(list.get(0), dbMessage);
                UserContext.current().setDbMessage(dbMessage);

            }
        }
    }

}
