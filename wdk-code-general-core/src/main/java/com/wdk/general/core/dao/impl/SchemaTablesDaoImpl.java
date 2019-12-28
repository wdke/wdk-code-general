package com.wdk.general.core.dao.impl;

import com.wdk.general.core.dao.SchemaTablesDao;
import com.wdk.general.core.dao.template.JdbcTemplates;
import com.wdk.general.core.handle.impl.BeanHandler;
import com.wdk.general.core.model.Tables;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * created by wdk on 2019/12/11
 */
@Service
public class SchemaTablesDaoImpl implements SchemaTablesDao {

    /**
     * 获取所有表信息
     *
     * @return
     */
    @Override
    public List<Tables> list() {
        String sql = "SELECT TABLE_SCHEMA as tablSchema, TABLE_NAME as tableName, TABLE_COMMENT as tableComment FROM TABLES where TABLE_SCHEMA='" + UserContext.current().getDbMessage().getDbname() + "'";
        return JdbcTemplates.query(sql, new BeanHandler<>(Tables.class));
    }


    /**
     * 部分数据表
     *
     * @param tableNames
     * @return
     */
    @Override
    public List<Tables> list(String[] tableNames) {
        //部分表
        if (null == tableNames || tableNames.length == 0) {
            return new ArrayList<>();
        }
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT TABLE_SCHEMA as tablSchema, TABLE_NAME as tableName, TABLE_COMMENT as tableComment ")
                .append("FROM TABLES ")
                .append("where TABLE_SCHEMA='")
                .append(UserContext.current().getDbMessage().getDbname())
                .append("' and TABLE_NAME in (");
        for (int i = 0; i < tableNames.length; i++) {

            if (i == tableNames.length - 1) {
                sql.append("'").append(tableNames[i]).append("')");
            } else {

                sql.append("'").append(tableNames[i]).append("', ");
            }

        }


        return JdbcTemplates.query(sql.toString(), new BeanHandler<>(Tables.class));
    }

    /**
     * 获取单个表
     *
     * @param tableName
     * @return
     */
    @Override
    public Tables one(String tableName) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT TABLE_SCHEMA as tablSchema, TABLE_NAME as tableName, TABLE_COMMENT as tableComment ")
                .append("FROM TABLES ")
                .append("where TABLE_SCHEMA='")
                .append(UserContext.current().getDbMessage().getDbname())
                .append("' and TABLE_NAME in (");
        List<Tables> query = JdbcTemplates.query(sql.toString(), new BeanHandler<>(Tables.class));
        if(query.size()>0){
            return query.get(0);
        }
        return null;
    }
}
