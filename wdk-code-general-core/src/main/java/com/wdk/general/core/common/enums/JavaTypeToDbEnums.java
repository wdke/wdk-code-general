package com.wdk.general.core.common.enums;

/**
 * @author wdke
 * @date 2019/5/15
 */
public enum JavaTypeToDbEnums {

    INT1(int.class,"int",11,"DEFAULT NULL","AUTO_INCREMENT",11),
    INT(Integer.class,"int",11,"DEFAULT NULL","AUTO_INCREMENT",11),
    LONG(Long.class,"bigint",20,"DEFAULT NULL","AUTO_INCREMENT",20),
    LONG1(long.class,"bigint20",20,"DEFAULT NULL","AUTO_INCREMENT",20),
    BYTE(Byte.class,"tinyint",4,"DEFAULT NULL","AUTO_INCREMENT",4),
    BYTE1(byte.class,"tinyint",4,"DEFAULT NULL","AUTO_INCREMENT",4),
    STRING(String.class,"varchar",225,"DEFAULT NULL","",32),
    BOOLEAN(Boolean.class,"tinyint",1,"DEFAULT 0","",1),
    boolen(boolean.class,"tinyint",1,"DEFAULT 0","",1);

    private Class javaType;

    private String dbType;


    private Integer length;

    private Integer keyLength;

    private String commonDefaults;

    private String keyDefaults;

    public Class getJavaType() {
        return javaType;
    }

    public void setJavaType(Class javaType) {
        this.javaType = javaType;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getCommonDefaults() {
        return commonDefaults;
    }

    public void setCommonDefaults(String commonDefaults) {
        this.commonDefaults = commonDefaults;
    }


    public String getKeyDefaults() {
        return keyDefaults;
    }

    public void setKeyDefaults(String keyDefaults) {
        this.keyDefaults = keyDefaults;
    }

    public Integer getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(Integer keyLength) {
        this.keyLength = keyLength;
    }

    JavaTypeToDbEnums(Class javaType, String dbType, Integer length, String commonDefaults, String keyDefaults, Integer keyLength){
        this.javaType=javaType;
        this.dbType=dbType;
        this.length=length;
        this.commonDefaults=commonDefaults;
        this.keyDefaults=keyDefaults;
        this.keyLength=keyLength;
    }


    public static JavaTypeToDbEnums javaTypeToDbEnums(Class javaType) {
        if (null==javaType) {
            return null;
        }
        for (JavaTypeToDbEnums order : JavaTypeToDbEnums.values()) {
            if (order.getJavaType().equals(javaType)||order.getJavaType()==javaType) {
                return order;
            }
        }
        return null;
    }
    public String toKeyString(){
        return dbType+"("+keyLength+") "+keyDefaults;
    }

    public String toKeyString(Integer length){
        return dbType+"("+length+") "+keyDefaults;
    }

    public String toCommonString(){
        return dbType+"("+this.length+") "+commonDefaults;
    }


    public String toCommonString(Integer length){
        return dbType+"("+length+") "+commonDefaults;
    }
}
