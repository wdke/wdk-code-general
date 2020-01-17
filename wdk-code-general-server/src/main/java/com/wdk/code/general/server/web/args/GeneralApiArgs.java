package com.wdk.code.general.server.web.args;

import lombok.Data;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * API信息维护表
 * @db auto_code
 * @table general_api
 * @author wdke
 * @date 2020/01/15 18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralApiArgs implements Serializable {

	//ID 唯一值
	private Integer id;

	//系统路径
	@Length(max = 128,message = "最大长度为128")
	private String path;

	//参数
	@Length(max = 512,message = "最大长度为512")
	private String args;

	//查询结果集
	@Length(max = 512,message = "最大长度为512")
	private String vo;

	//数据库表
	@Length(max = 512,message = "最大长度为512")
	private String froms;

	//查询条件
	@NotBlank(message = "cannot be empty")
	@Length(max = 512,message = "最大长度为512")
	private String wheres;

	//排序
	@NotBlank(message = "cannot be empty")
	@Length(max = 512,message = "最大长度为512")
	private String orderbys;

	//分页数据
	@NotBlank(message = "cannot be empty")
	@Length(max = 512,message = "最大长度为512")
	private String limits;

	//创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime;

	//更新时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updateTime;





}

