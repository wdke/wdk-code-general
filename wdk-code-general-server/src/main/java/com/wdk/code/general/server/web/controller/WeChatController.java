package com.wdk.code.general.server.web.controller;

import com.alibaba.fastjson.JSON;
import com.wdk.code.general.server.https.WechatApiService;
import com.wdk.general.core.common.constant.WechatConstant;
import com.wdk.general.core.service.WeChatService;
import com.wdk.general.core.utils.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wdke
 * @date 2019/4/25
 */
@RestController
@RequestMapping("wechat")
public class WeChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);


    @Autowired
    private WeChatService weChatService;

    @Autowired
    private WechatApiService wechatApiService;

    /**
     * 处理微信服务器发来的get请求，进行签名的验证
     * <p>
     * signature 微信端发来的签名
     * timestamp 微信端发来的时间戳
     * nonce     微信端发来的随机字符串
     * echostr   微信端发来的验证字符串
     */
    @GetMapping(value = "")
    public String validate(@RequestParam(value = "signature") String signature,
                           @RequestParam(value = "timestamp") String timestamp,
                           @RequestParam(value = "nonce") String nonce,
                           @RequestParam(value = "echostr") String echostr) {
        logger.info("微信认证：：signature->{},timestamp->{},nonce->{},echostr->{}", signature, timestamp, nonce, echostr);
        return WeChatUtil.checkSignature(signature, timestamp, nonce) ? echostr : null;

    }

    /**
     * 此处是处理微信服务器的消息转发的
     */
    @PostMapping(value = "")
    public String processMsg(HttpServletRequest request) {

        logger.info("微信消息：：request->{}", JSON.toJSONString(request.getParameterMap()));

        // 调用核心服务类接收处理请求
        return weChatService.processRequest(request);
    }

    /**
     * 此处是处理微信服务器的消息转发的
     */
    @PostMapping(value = "index")
    public String processMsgIndex(HttpServletRequest request) {

        logger.info("微信消息：Index：request->{}", JSON.toJSONString(request.getParameterMap()));

        // 调用核心服务类接收处理请求
        return weChatService.processRequest(request);
    }

    /**
     * 此处是处理微信服务器的消息转发的
     */
    @GetMapping(value = "token")
    public String token(HttpServletRequest request) {

        logger.info("微信消息：Index：request->{}", JSON.toJSONString(request.getParameterMap()));

        String result = wechatApiService.token("client_credential", WechatConstant.APP_ID, WechatConstant.APPSECRET);
        return result;
    }

}
