package com.wdk.code.general.server.https;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author wdke
 * @date 2019/4/25
 */
@FeignClient(url = "https://api.weixin.qq.com", name = "wechat")
public interface WechatApiService {

    /**
     * 获取ticket
     *
     * @param access_token
     * @param type
     * @return
     */
    @RequestMapping(value = "/cgi-bin/ticket/getticket?", method = RequestMethod.GET)
    String ticket(@RequestParam("access_token") String access_token, @RequestParam("type") String type);

    /**
     * 获取token
     *
     * @param grant_type
     * @param appid
     * @param secret
     * @return
     */
    @RequestMapping(value = "/cgi-bin/token", method = RequestMethod.GET)
    String token(@RequestParam("grant_type") String grant_type, @RequestParam("appid") String appid, @RequestParam("secret") String secret);

    /**
     * 上传图片
     *
     * @param access_token
     * @param file
     * @return
     */
    @RequestMapping(value = "cgi-bin/media/uploadimg", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadimg(@RequestParam("access_token") String access_token, @RequestPart("media") MultipartFile file);

}
