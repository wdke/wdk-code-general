package com.wdk.general.core.common.enums;

/**
 * created by wdk on 2020/1/2
 */
public enum DbTypeEnum {
    MYSQL("mysql");

    String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    DbTypeEnum(String code){
        this.code=code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
