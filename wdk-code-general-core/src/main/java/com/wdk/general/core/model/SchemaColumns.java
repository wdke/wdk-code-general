package com.wdk.general.core.model;

import ch.qos.logback.classic.db.names.ColumnName;
import com.alibaba.druid.util.StringUtils;
import lombok.Data;

import java.io.Serializable;

@Data
public class SchemaColumns implements Serializable {
    private String tableCatalog;

    private String tableSchema;

    private String tableName;

    private String columnName;

    private Long ordinalPosition;

    private String isNullable;

    private String dataType;

    private Long characterMaximumLength;

    private Long characterOctetLength;

    private Long numericPrecision;

    private Long numericScale;

    private Long datetimePrecision;

    private String characterSetName;

    private String collationName;

    private String columnKey;

    private String extra;

    private String privileges;

    private String columnComment;

    //model属性
    private String modelName;

    //model属性
    private String modelObjName;


}