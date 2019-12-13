package com.wdk.general.core.dao;

import com.wdk.general.core.model.SchemaColumns;

import java.util.List;

/**
 * created by wdk on 2019/12/12
 */
public interface SchemaColumnsDao {


    /**
     * 获取所有数据
     *
     * @return
     */
    List<SchemaColumns> list();


    /**
     * 获取指定数据库与表的数据
     *
     * @param tableName
     * @return
     */
    List<SchemaColumns> list(String[] tableName);


}
