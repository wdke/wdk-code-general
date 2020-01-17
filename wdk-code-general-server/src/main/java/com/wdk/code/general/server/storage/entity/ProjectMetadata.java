package com.wdk.code.general.server.storage.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;import java.io.Serializable;

/**
 * 项目生成信息信息
 * @db auto_code
 * @table project_metadata
 * @author wdke
 * @date 2020/01/15 18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectMetadata implements Serializable {

	//id
	private Integer id;

	//用户ID
	private Integer userId;

	//项目gr...
	private String projectGroup;

	//artifact
	private String artifact;

	//项目类型
	private String projectType;

	//项目语言
	private String projectLanguage;

	//打包类型
	private String packaging;

	//JDK版本
	private String javaVersion;

	//项目版本
	private String version;

	//项目名称
	private String projectName;

	//项目描述
	private String description;

	//项目包路径
	private String packages;

	//项目端口号
	private Integer point;

	//创建日期
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date createTime;

	//更新时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date updateTime;


	//排序字段
	private String orderBy;



}

