package com.wdk.code.general.server.web.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.io.Serializable;

/**
 * 数据源信息
 * @db auto_code
 * @table db_messages
 * @author wdke
 * @date 2020/01/15 18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DbMessagesVo implements Serializable {

	//id
	private Integer id;

	//用户ID
	private Integer userId;

	//数据库类型
	private String dbType;

	//数据库地址
	private String host;

	//数据库名称
	private String dbName;

	//数据库用户名
	private String dbUsername;

	//数据库密码
	private String dbPassword;

	//数据库端口号
	private Integer dbPort;

	//创建日期
	private Date createTime;

	//更新时间
	private Date updateTime;



}

