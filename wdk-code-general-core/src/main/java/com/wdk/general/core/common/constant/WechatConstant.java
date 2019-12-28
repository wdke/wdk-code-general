package com.wdk.general.core.common.constant;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author wdke
 * @date 2019/6/27
 */
@Component
public class WechatConstant implements Serializable {
    //APPID
    public static String APP_ID = "wx9252789f09c168cf";//wx525b4a768fd4bb7d
    //appsecret
    public static String APPSECRET = "55f89a0a37cfdd4a4e1f344dd851a472";//b3ab7a19fef71d0d36a2f545cbcf9f03
    //APPID
    public static String APPLET_APP_ID = "wx525b4a768fd4bb7d";//wx525b4a768fd4bb7d
    //appsecret
    public static String APPLET_APPSECRET = "b3ab7a19fef71d0d36a2f545cbcf9f03";//b3ab7a19fef71d0d36a2f545cbcf9f03
    // Token
    public static String TOKEN = "wdke2019";

    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    public static final Object REQ_MESSAGE_TYPE_TEXT = "text";
    public static final Object REQ_MESSAGE_TYPE_IMAGE = "image";
    public static final Object REQ_MESSAGE_TYPE_VOICE = "voice";
    public static final Object REQ_MESSAGE_TYPE_VIDEO = "video";
    public static final Object REQ_MESSAGE_TYPE_LOCATION = "location";
    public static final Object REQ_MESSAGE_TYPE_LINK = "link";
    public static final Object REQ_MESSAGE_TYPE_EVENT = "event";
    public static final Object EVENT_TYPE_SUBSCRIBE = "subscribe";
    public static final Object EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    public static final Object EVENT_TYPE_SCAN = "scan";
    public static final Object EVENT_TYPE_LOCATION = "location";
    public static final Object EVENT_TYPE_CLICK = "click";

    public static final String FromUserName = "FromUserName";
    public static final String ToUserName = "ToUserName";
    public static final String MsgType = "MsgType";
    public static final String Content = "Content";
    public static final String Event = "Event";
    public static final String EventKey = "EventKey";

}
