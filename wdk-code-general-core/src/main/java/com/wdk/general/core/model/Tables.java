package com.wdk.general.core.model;

import com.alibaba.druid.util.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * created by wdk on 2019/12/12
 */
@Data
public class Tables implements Serializable {


    //所属数据库
    private String tablSchema;

    //获取表信息
    private String tableName;

    //获取表描述
    private String tableComment;

    //model属性
    private String modelName;

    //model属性
    private String modelObjName;

}
