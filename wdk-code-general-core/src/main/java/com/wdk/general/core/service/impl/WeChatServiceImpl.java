package com.wdk.general.core.service.impl;

/**
 * @author wdke
 * @date 2019/4/25
 */

import com.wdk.general.core.common.constant.WechatConstant;
import com.wdk.general.core.service.WeChatService;
import com.wdk.general.core.utils.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 核心服务类
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    private static final Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);


    @Value("${spring.profiles.active}")
    private String active;


    /**
     * 消息处理接口
     *
     * @param request
     * @return
     */
    @Override
    public String processRequest(HttpServletRequest request) {

        String openid = request.getParameter("openid");
        logger.info("微信消息信息：：openid->{}", openid);
//        userInfo
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent;
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = WeChatUtil.parseXml(request);
            logger.info("微信消息信息：：->{}", requestMap);
            // 消息类型
            String msgType = (String) requestMap.get(WechatConstant.MsgType);
            String mes = null;
            // 文本消息
            if (msgType.equals(WechatConstant.REQ_MESSAGE_TYPE_TEXT)) {

                respXml = WeChatUtil.sendTextMsg(requestMap, "不知道你在说啥。");
            }
            // 图片消息
            else if (msgType.equals(WechatConstant.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "暂不支持图片消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 语音消息
            else if (msgType.equals(WechatConstant.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "暂不支持语音消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 视频消息
            else if (msgType.equals(WechatConstant.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "暂不支持视频消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 地理位置消息
            else if (msgType.equals(WechatConstant.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "暂不支持地理位置消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 链接消息
            else if (msgType.equals(WechatConstant.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "暂不支持链接消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 事件推送
            else if (msgType.equals(WechatConstant.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = (String) requestMap.get(WechatConstant.Event).toLowerCase();

                // 关注
                if (eventType.equals(WechatConstant.EVENT_TYPE_SUBSCRIBE.toString())) {
                    respContent = "谢谢您的关注,欢迎您来到Phoenix AI课堂！";
                    respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
                }
                // 取消关注
                else if (eventType.equals(WechatConstant.EVENT_TYPE_UNSUBSCRIBE.toString())) {

                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(WechatConstant.EVENT_TYPE_SCAN.toString())) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(WechatConstant.EVENT_TYPE_LOCATION.toString())) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(WechatConstant.EVENT_TYPE_CLICK.toString())) {
                    // TODO 处理菜单点击事件
//                    respContent = menusClick(requestMap, openid);
                }
            }
            if (respXml == null)
                respXml = WeChatUtil.sendTextMsg(requestMap, "谢谢光临。");
            return respXml;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }


}
