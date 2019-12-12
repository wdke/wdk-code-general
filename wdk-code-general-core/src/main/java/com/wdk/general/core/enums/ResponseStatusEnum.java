package com.wdk.general.core.enums;

public enum ResponseStatusEnum {
    SUCCESS(200,"成功"),
    LOGOUT(300,"未登录"),
    NO_PERMISSIONS(300,"用户没有权限操作此接口"),
    USER_ERROR(301,"用户名不存在"),
    PASSWORD_ERROR(301,"密码错误"),
    USER_ISLIVE(301,"用户名已存在"),
    ERROR(201,"请求错误"),
    PARRAM_ERROR(202,"参数错误"),
    DATA_REPETITION(203,"参数错误"),
    DATA_EMPTY(200,"数据为空"),
    TABLES_EMPTY(204,"数据表不存在或已经被删除"),
    COLUMNS_EMPTY(204,"数据表字段不存在");


    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResponseStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
