package com.wdk.code.general.server.web.args;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * created by wdk on 2019/12/24
 */
@Data
public class LoginArgs implements Serializable {


	//用户名称
	@NotBlank(message = "cannot be empty")
	private String username;


	//用户密码
	@NotBlank(message = "cannot be empty")
	private String passord;
}
