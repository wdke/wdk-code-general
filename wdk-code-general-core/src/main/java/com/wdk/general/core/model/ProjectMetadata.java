package com.wdk.general.core.model;

import com.alibaba.druid.util.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * created by wdk on 2019/12/13
 */
@Data
public class ProjectMetadata implements Serializable {

    private String group;

    private String artifact;

    private String type;

    private String language;

    private String packaging;

    private String javaVersion;

    private String version;

    private String name;

    private String description;

    private String packages;

    private Integer point=8880;



    public String getProJectJavaName(){
        if(StringUtils.isEmpty(name)){
            return "";
        }


        String[] cols=name.split("-");
        StringBuffer col=new StringBuffer();
        for(int i=0;i<cols.length;i++){
            col.append(cols[i].substring(0,1).toUpperCase()+cols[i].substring(1).toLowerCase());

        }
        return col.toString();
    }


    public String getPojectCodeRootPath(){
        return "";
    }

}
