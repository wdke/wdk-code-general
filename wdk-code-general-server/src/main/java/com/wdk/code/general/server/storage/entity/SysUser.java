package com.wdk.code.general.server.storage.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;import java.io.Serializable;

/**
 * 用户管理
 * @db auto_code
 * @table sys_user
 * @author wdke
 * @date 2020/01/15 18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUser implements Serializable {

	//id
	private Integer id;

	//用户名
	private String username;

	//用户密码
	private String password;

	//加密密钥
	private String passwordKey;

	//电话
	private Long phone;

	//邮箱
	private String email;

	//地址
	private String address;

	//性别
	private String sex;

	//出生年月
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date birthday;

	//描述
	private String description;

	//角色ID
	private Long roleId;

	//用户头像
	private String headImages;

	//创建日期
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime;

	//更新日期
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updateTime;


	//排序字段
	private String orderBy;



}

