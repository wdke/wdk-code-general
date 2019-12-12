package com.wdk.general.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * created by wdk on 2019/12/11
 */

@Data
public class User implements Serializable {
    private Long uid ;
    private String name ;
    private Integer age ;
    private Date birthday ;
    private Double salary ;
    //省略get/set方法



}