package com.wdk.general.core.service;


import com.wdk.general.core.common.model.ResponseVo;
import com.wdk.general.core.web.vo.SignatureVo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface WeChatService {

    /**
     * 被动回复消息
     *
     * @param request
     * @return
     */
    String processRequest(HttpServletRequest request);


}
