package com.wdk.general.core.model;

import com.alibaba.druid.util.StringUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * created by wdk on 2019/12/13
 */
@Data
public class ProjectMetadata implements Serializable {


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


    private Integer point = 8880;


    private Boolean useActualColumnNames = true;


    public String getProJectJavaName() {
        if (StringUtils.isEmpty(projectName)) {
            return "";
        }


        String[] cols = projectName.split("-");
        StringBuffer col = new StringBuffer();
        for (int i = 0; i < cols.length; i++) {
            col.append(cols[i].substring(0, 1).toUpperCase() + cols[i].substring(1).toLowerCase());

        }
        return col.toString();
    }


    public String getPojectCodeRootPath() {
        return "";
    }

}
