package com.wdk.code.general.server.web.args;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * 项目生成信息信息
 *
 * @author wdke
 * @db auto_code
 * @table project_metadata
 * @date 2020/01/15 18
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectMetadataArgs implements Serializable {

    //id
    private Integer id;

    //用户ID
    private Integer userId;

    //项目gr...
    @Length(max = 16, message = "最大长度为16")
    private String projectGroup;

    //artifact
    @Length(max = 32, message = "最大长度为32")
    private String artifact;

    //项目类型
    @Length(max = 32, message = "最大长度为32")
    private String projectType;

    //项目语言
    @Length(max = 32, message = "最大长度为32")
    private String projectLanguage;

    //打包类型
    @Length(max = 32, message = "最大长度为32")
    private String packaging;

    //JDK版本
    @Length(max = 32, message = "最大长度为32")
    private String javaVersion;

    //项目版本
    @Length(max = 32, message = "最大长度为32")
    private String version;

    //项目名称
    @Length(max = 32, message = "最大长度为32")
    private String projectName;

    //项目描述
    @Length(max = 32, message = "最大长度为32")
    private String description;

    //项目包路径
    @Length(max = 32, message = "最大长度为32")
    private String packages;

    //项目端口号
    private Integer point;


    private Boolean useActualColumnNames = true;

    public ProjectMetadataArgs() {
    }


    public ProjectMetadataArgs(Integer userId) {
        this.userId = userId;
    }


}

