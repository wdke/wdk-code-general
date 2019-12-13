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

package com.wdk.general.core.web.Interceptor;

import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.web.param.LoginParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            response.sendRedirect("/login");
            return false;
        }

        if (requestURI.startsWith("/login")
                || requestURI.startsWith("/index")
                || requestURI.startsWith("/static")) {
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

        LoginParam userInfo = (LoginParam) request.getSession().getAttribute("userInfo");

        if (null == userInfo || StringUtils.isEmpty(userInfo.getUsername())) {

            try {
                response.sendRedirect("/login");
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        DbMessage dbMessage = (DbMessage) request.getSession().getAttribute("dbMessage");

        if (null == dbMessage && !request.getRequestURI().startsWith("/tables")) {

            try {
                response.sendRedirect("/tables/index");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        UserContext.current().setDbMessage(dbMessage);
        return true;
    }

}
