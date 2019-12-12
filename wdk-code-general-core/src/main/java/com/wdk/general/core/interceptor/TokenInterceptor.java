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

package com.wdk.general.core.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.utills.JwtUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        String traceId = request.getParameter("__trace_id");

        UserContext.current().setTraceId(traceId);
        long start = System.currentTimeMillis();
        UserContext.current().setStartTime(start);

        String requestURI = request.getRequestURI();
        if (StringUtils.isEmpty(requestURI)) {
            return false;
        }

        if (requestURI.startsWith("/test")) {
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
        if(duration > 3000){
            logger.info("超时报警短信【__trace_id：{}】【duration：{}】", UserContext.current().getTraceId(), duration);
        }
        UserContext.release();
    }

    private boolean preHandleService(HttpServletRequest request, HttpServletResponse response) {
       String loginToken = request.getHeader("Authorization");
        if (StringUtils.isEmpty(loginToken)) {
            loginToken = request.getParameter("Authorization");
            if (StringUtils.isEmpty(loginToken)) {
                return false;
            }
        }

        if(!JwtUtils.isVerify(loginToken)){
            return false;
        }
        Claims claims = JwtUtils.parseJWT(loginToken);
//        DbMessage dbMessage=claims.get("dbMessage",DbMessage.class);
        UserContext.current().setDbMessage(claims.get("dbMessage",DbMessage.class));
        return true;
    }

}
