package com.wdk.general.core.dao;

import com.wdk.general.core.model.Tables;

import java.util.List;

/**
 * created by wdk on 2019/12/11
 */
public interface SchemaTablesDao {

    /**
     * 所有表
     *
     * @return
     */
    List<Tables> list();

    /**
     * 所有表
     * @param tableSchema
     * @return
     */
    List<Tables> list(String tableSchema);

    /**
     * 部分tableName
     *
     * @param tableNames
     * @return
     */
    List<Tables> list(String[] tableNames);

    /**
     * 部分tableName
     *
     * @param tableName
     * @return
     */
    Tables one(String tableName);
}
