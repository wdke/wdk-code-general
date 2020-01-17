package com.wdk.general.core.service;

/**
 * created by wdk on 2019/12/12
 */
public interface GenerateService {


    /**
     * 初始化生成
     *
     * @param tableName
     */
    void init(String[] tableName);


    /**
     * 生成的固定代码部分
     *
     * @param tableName
     */
    void autoMybatis(String[] tableName);
}
