package com.wdk.general.core.utils;


import com.wdk.general.core.common.constant.WechatConstant;
import com.wdk.general.core.common.model.ArticleItemVo;
import com.wdk.general.core.common.model.SignatureVo;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;


/**
 * @author wdke
 * @date 2019/4/25
 */
public class WeChatUtil {

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{WechatConstant.TOKEN, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        // Arrays.sort(arr);
        sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     * sha1加密
     *
     * @param jsapi_ticket
     * @param timestamp
     * @param url
     * @return
     */
    public static SignatureVo createSignature(String jsapi_ticket, Long timestamp, String url) {

        StringBuilder content = new StringBuilder();
        content.append("jsapi_ticket=").append(jsapi_ticket).append("&")
                .append("noncestr=").append(WechatConstant.TOKEN).append("&")
                .append("timestamp=").append(timestamp).append("&")
                .append("url=").append(url.indexOf("#") >= 0 ? url.substring(0, url.indexOf("#")) : url);

        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SignatureVo vo = new SignatureVo();
        vo.setAppId(WechatConstant.APP_ID);
        vo.setTimestamp(timestamp);
        vo.setSignature(tmpStr.toLowerCase());
        vo.setNoncestr(WechatConstant.TOKEN);
        vo.setUrl(url);

//        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return vo;
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
//        System.out.println("byteArray="+new String(byteArray));
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

    public static void main(String[] args) throws InterruptedException {
        String wdk = "wdksdasdas";
        System.out.println(wdk);
        System.out.println("byteToStr=" + byteToStr(wdk.getBytes()));


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(wdk.getBytes());
            System.out.println("digest.length=" + digest.length);
            System.out.println("byteToStr.length()=" + byteToStr(digest).length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

    private static void sort(String a[]) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) < 0) {
                    String temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 解析微信发来的请求(xml)
     *
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings({"unchecked"})
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    public static String mapToXML(Map map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        mapToXML2(map, sb);
        sb.append("</xml>");
        try {
            return sb.toString();
        } catch (Exception e) {
        }
        return null;
    }

    private static void mapToXML2(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXML2(hm, sb);
                }
                sb.append("</" + key + ">");

            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXML2((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + "><![CDATA[" + value + "]]></" + key + ">");
                }

            }

        }
    }

    /**
     * 回复文本消息
     *
     * @param requestMap
     * @param content
     * @return
     */
    public static String sendTextMsg(Map<String, String> requestMap, String content) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ToUserName", requestMap.get(WechatConstant.FromUserName));
        map.put("FromUserName", requestMap.get(WechatConstant.ToUserName));
        map.put("MsgType", WechatConstant.RESP_MESSAGE_TYPE_TEXT);
        map.put("CreateTime", new Date().getTime());
        map.put("Content", content);
        return mapToXML(map);
    }

    /**
     * 回复图文消息
     *
     * @param requestMap
     * @param items
     * @return
     */
    public static String sendArticleMsg(Map<String, String> requestMap, List<ArticleItemVo> items) {
        if (items == null || items.size() < 1) {
            return "";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ToUserName", requestMap.get(WechatConstant.FromUserName));
        map.put("FromUserName", requestMap.get(WechatConstant.ToUserName));
        map.put("MsgType", "news");
        map.put("CreateTime", new Date().getTime());
        List<Map<String, Object>> Articles = new ArrayList<Map<String, Object>>();
        for (ArticleItemVo itembean : items) {
            Map<String, Object> item = new HashMap<String, Object>();
            Map<String, Object> itemContent = new HashMap<String, Object>();
            itemContent.put("Title", itembean.getTitle());
            itemContent.put("Description", itembean.getDescription());
            itemContent.put("PicUrl", itembean.getPicUrl());
            itemContent.put("Url", itembean.getUrl());
            item.put("item", itemContent);
            Articles.add(item);
        }
        map.put("Articles", Articles);
        map.put("ArticleCount", Articles.size());
        return mapToXML(map);
    }

}