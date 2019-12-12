/**  
 * @title: newmerchant-xapi   
 * @file: UserContext.java
 * @package: com.wanda.application.xapi.newmerchant.web.interceptor    
 * @description: 用ThreadLocal存储用户信息    
 * @author: jinyi10
 * @email: jinyi10@wanda.cn  
 * @time: 2017年3月20日 下午5:50:20   
 * @Copyright (c) 2017, Wanda.cn All right reserved. 
 */

package com.wdk.general.core.interceptor;

import com.wdk.general.core.model.DbMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by loutao on 2018/6/20.
 */
public class UserContext {

    private static ThreadLocal<UserContext> threadLocal = new ThreadLocal<UserContext>() {
        @Override
        protected UserContext initialValue() {
            return new UserContext();
        }
    };

    private String remortIP;
    private String localIp;
    private DbMessage dbMessage;
    private String memberId;
    private String teamId;
    private Object remarkObj;
    private String traceId;
    private long startTime;
    private String loginToken;
    private boolean creator;

    private HttpServletRequest request;
    private HttpServletResponse response;


    public static void release() {
        threadLocal.remove();
    }

    public static UserContext current() {
        return threadLocal.get();
    }

    public String getRemortIP() {
        return remortIP;
    }

    public void setRemortIP(String remortIP) {
        this.remortIP = remortIP;
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public DbMessage getDbMessage() {
        return dbMessage;
    }

    public void setDbMessage(DbMessage dbMessage) {
        this.dbMessage = dbMessage;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Object getRemarkObj() {
        return remarkObj;
    }

    public void setRemarkObj(Object remarkObj) {
        this.remarkObj = remarkObj;
    }

    public String getTraceId()  {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public boolean isCreator() {
        return creator;
    }

    public void setCreator(boolean creator) {
        this.creator = creator;
    }

    //
}
