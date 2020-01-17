package com.wdk.code.general.server.web.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import java.io.Serializable;

/**
 * API信息维护表
 * @db auto_code
 * @table general_api
 * @author wdke
 * @date 2020/01/15 18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GeneralApiVo implements Serializable {

	//ID 唯一值
	private Integer id;

	//系统路径
	private String path;

	//参数
	private String args;

	//查询结果集
	private String vo;

	//数据库表
	private String froms;

	//查询条件
	private String wheres;

	//排序
	private String orderbys;

	//分页数据
	private String limits;

	//创建时间
	private Date createTime;

	//更新时间
	private Date updateTime;



}

