package com.wdk.general.core.common.enums;

import com.alibaba.druid.util.StringUtils;

/**
 * @author wdke
 * @date 2019/4/30
 */
public enum JdbcTypeEnums {

    VARCHAR("VARCHAR","String","java.lang.String","VARCHAR"),
    CHAR("CHAR","String","java.lang.String","CHAR"),
    ENUM("ENUM","String","java.lang.String","CHAR"),
    BIGINT("BIGINT","Long","java.lang.Long","BIGINT"),
    longtext("LONGTEXT","String","java.lang.String","LONGVARCHAR"),
    DATETIME("DATETIME","Date","java.util.Date","DATETIME"),
    INT("INT","Integer","java.lang.Integer","INTEGER"),
    MEDIUMINT("MEDIUMINT","Integer","java.lang.Integer","INTEGER"),
    SMALLINT("SMALLINT","Short","java.lang.Short","SMALLINT"),
    tinyint("TINYINT","Byte","java.lang.Byte","TINYINT"),
    DECIMAL("DECIMAL","Double","java.lang.Double","DECIMAL"),
    timestamp("TIMESTAMP","Date","java.util.Date","TIMESTAMP"),
    TEXT("TEXT","String","java.lang.String","LONGVARCHAR"),
    FLOAT("FLOAT","Float","java.lang.Float","FLOAT"),
    DATE("DATE","Date","java.util.Date","DATE");


//    Decimal
    private String dbType;


    private String javaType;

    private String javaPackages;

    private String mybatisType;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaPackages() {
        return javaPackages;
    }

    public void setJavaPackages(String javaPackages) {
        this.javaPackages = javaPackages;
    }

    public String getMybatisType() {
        return mybatisType;
    }

    public void setMybatisType(String mybatisType) {
        this.mybatisType = mybatisType;
    }

    JdbcTypeEnums(String dbType, String javaType, String javaPackages, String mybatisType){

        this.dbType=dbType;

        this.javaType=javaType;

        this.mybatisType=mybatisType;
    }

    // 普通方法 
    public static JdbcTypeEnums jdbcTypeEnumsByDbType(String dbType) {
        if (StringUtils.isEmpty(dbType)) {
            return null;
        }
        for (JdbcTypeEnums order : JdbcTypeEnums.values())
            if (order.getDbType().equals(dbType.toUpperCase())) {
                return order;
            }
        return null;
    }

    public static JdbcTypeEnums jdbcTypeEnumsByJavaType(String javaType) {
        if (StringUtils.isEmpty(javaType)) {
            return null;
        }
        for (JdbcTypeEnums order : JdbcTypeEnums.values()) {
            if (order.getJavaType().equals(javaType)) {
                return order;
            }
        }
        return null;
    }


    public static JdbcTypeEnums jdbcTypeEnumsByMybatisType(String mybatisType) {
        if (StringUtils.isEmpty(mybatisType)) {
            return null;
        }
        for (JdbcTypeEnums order : JdbcTypeEnums.values()) {
            if (order.getMybatisType().equals(mybatisType)) {
                return order;
            }
        }
        return null;
    }
}
