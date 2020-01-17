package com.wdk.code.general.server.web.args;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户管理
 * @db auto_code
 * @table sys_user
 * @author wdke
 * @date 2020/01/15 18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserArgs implements Serializable {

	//id
	private Integer id;

	//用户名
	@Length(max = 32,message = "最大长度为32")
	private String username;

	//用户密码
	@Length(max = 64,message = "最大长度为64")
	private String password;

	//加密密钥
	@Length(max = 64,message = "最大长度为64")
	private String passwordKey;

	//电话
	private Long phone;

	//邮箱
	@NotBlank(message = "cannot be empty")
	@Length(max = 64,message = "最大长度为64")
	private String email;

	//性别
	@NotBlank(message = "cannot be empty")
	@Length(max = 4,message = "最大长度为4")
	private String sex;



}

