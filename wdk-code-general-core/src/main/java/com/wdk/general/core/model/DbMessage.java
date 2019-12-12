package com.wdk.general.core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * created by wdk on 2019/12/11
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DbMessage implements Serializable {

    //用户ID
    private Integer userId;

    //数据库地址
    private String host;


    //数据库名称
    private String dbname;

    //数据库管理员名称
    private String dbuser;

    //数据库密码
    private String dbpassword;

    //数据库端口号
    private Integer dbport = 3306;

}
