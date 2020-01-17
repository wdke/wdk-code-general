package com.wdk.general.core.common.model;

import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.model.ProjectMetadata;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by wdk on 2019/12/13
 */
public class UserContext {

    private static ThreadLocal<UserContext> threadLocal = ThreadLocal.withInitial(() -> new UserContext());


    private String lastPages;
    private String remortIP;
    private String localIp;
    private DbMessage dbMessage;
    private ProjectMetadata projectMetadata;
    private String projectRoot;
    private Integer userId;
    private String username;
    private String teamId;
    private Object remarkObj;
    private String traceId;
    private long startTime;
    private String loginToken;
    private boolean creator;

    private String mybatisCorePath="/Users/wdke/Project/repository/org/mybatis/generator/mybatis-generator-core/1.3.7/mybatis-generator-core-1.3.7.jar";

    private HttpServletRequest request;
    private HttpServletResponse response;

    public String getLastPages() {
        return lastPages;
    }

    public void setLastPages(String lastPages) {
        this.lastPages = lastPages;
    }

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

    public static ThreadLocal<UserContext> getThreadLocal() {
        return threadLocal;
    }

    public static void setThreadLocal(ThreadLocal<UserContext> threadLocal) {
        UserContext.threadLocal = threadLocal;
    }

    public ProjectMetadata getProjectMetadata() {
        return projectMetadata;
    }

    public void setProjectMetadata(ProjectMetadata projectMetadata) {
        this.projectMetadata = projectMetadata;
    }

    public String getProjectRoot() {
        return projectRoot;
    }

    public String getProjectServerRoot() {
        return projectRoot + "/" + projectMetadata.getName() + "-server";
    }

    public String getProjectApiRoot() {
        return projectRoot + "/" + projectMetadata.getName() + "-Api";
    }

    public void setProjectRoot(String projectRoot) {
        this.projectRoot = projectRoot;
    }

    public Object getRemarkObj() {
        return remarkObj;
    }

    public void setRemarkObj(Object remarkObj) {
        this.remarkObj = remarkObj;
    }

    public String getTraceId() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getMybatisCorePath() {
        return mybatisCorePath;
    }

    public void setMybatisCorePath(String mybatisCorePath) {
        this.mybatisCorePath = mybatisCorePath;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
