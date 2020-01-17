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
import com.wdk.general.core.common.model.UserContext;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.model.ProjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by loutao on 2018/6/20.
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Value("${filePath}")
    private String filePath;

    @Autowired
    private RedisStringDao redisStringDao;

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
            }

        }

        UserContext.current().setUsername(username);

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
        DbMessage dbMessage = JSON.parseObject(redisStringDao.get("db_" + UserContext.current().getUsername()),DbMessage.class);

        if (null != dbMessage) {
            UserContext.current().setDbMessage(dbMessage);
        }

        ProjectMetadata projectMetadata = JSON.parseObject(redisStringDao.get("pm_"+ UserContext.current().getUsername()),ProjectMetadata.class);

        if (null != projectMetadata) {
            UserContext.current().setProjectMetadata(projectMetadata);
            UserContext.current().setProjectRoot(filePath + "/" + projectMetadata.getName());
        }


        return true;
    }

}
