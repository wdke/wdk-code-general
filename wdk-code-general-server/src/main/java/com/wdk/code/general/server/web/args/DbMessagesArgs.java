package com.wdk.code.general.server.web.args;

import lombok.Data;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 数据源信息
 * @db auto_code
 * @table db_messages
 * @author wdke
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
	@Length(max = 16,message = "最大长度为16")
	private String dbType;

	//数据库地址
	@Length(max = 32,message = "最大长度为32")
	private String host;

	//数据库名称
	@Length(max = 32,message = "最大长度为32")
	private String dbName;

	//数据库用户名
	@Length(max = 32,message = "最大长度为32")
	private String dbUsername;

	//数据库密码
	@Length(max = 32,message = "最大长度为32")
	private String dbPassword;

	//数据库端口号
	private Integer dbPort;

	//创建日期
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime;

	//更新时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updateTime;





}

