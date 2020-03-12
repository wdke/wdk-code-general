package com.wdk.code.general.server.web.args;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 数据源信息
 *
 * @author wdke
 * @db auto_code
 * @table db_messages
 * @date 2020/01/15 18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DbMessagesArgs implements Serializable {

    //id
    private Integer id;

    //用户ID
    private Integer userId;

    //数据库类型
    @Length(max = 16, message = "最大长度为16")
    private String dbType;

    //数据库地址
    @Length(max = 32, message = "最大长度为32")
    private String host;

    //数据库名称
    @Length(max = 32, message = "最大长度为32")
    private String dbName;

    //数据库用户名
    @Length(max = 32, message = "最大长度为32")
    private String dbUsername;

    //数据库密码
    @Length(max = 32, message = "最大长度为32")
    private String dbPassword;

    //数据库端口号
    private Integer dbPort;

    public DbMessagesArgs() {
    }

    public DbMessagesArgs(Integer userId) {
        this.userId = userId;
    }
}

