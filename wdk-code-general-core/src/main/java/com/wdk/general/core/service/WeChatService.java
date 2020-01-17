package com.wdk.general.core.service;



import javax.servlet.http.HttpServletRequest;

public interface WeChatService {

    /**
     * 被动回复消息
     *
     * @param request
     * @return
     */
    String processRequest(HttpServletRequest request);


}
