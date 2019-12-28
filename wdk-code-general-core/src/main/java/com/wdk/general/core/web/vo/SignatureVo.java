package com.wdk.general.core.web.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wdke
 * @date 2019/5/9
 */
@Data
public class SignatureVo implements Serializable {

    private String appId;

    private Long timestamp;

    private String noncestr;

    private String url;

    private String signature;

}
