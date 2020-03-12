package com.wdk.general.core.dao.impl;

import com.wdk.general.core.common.model.UserContext;
import com.wdk.general.core.dao.SchemaTablesDao;
import com.wdk.general.core.dao.template.JdbcTemplates;
import com.wdk.general.core.handle.impl.BeanHandler;
import com.wdk.general.core.model.ProjectMetadata;
import com.wdk.general.core.model.Tables;
import com.wdk.general.core.utils.StringConversionUtil;
import org.apache.commons.lang.StringUtils;
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
        String sql = "SELECT TABLE_SCHEMA as tablSchema, TABLE_NAME as tableName, TABLE_COMMENT as tableComment FROM TABLES where TABLE_SCHEMA='" + UserContext.current().getDbMessage().getDbName() + "'";
        List<Tables> query = JdbcTemplates.query(sql, new BeanHandler<>(Tables.class));
        conversion(query);
        return query;
    }

    /**
     * 根据数据库获取所有表
     *
     * @param tableSchema
     * @return
     */
    @Override
    public List<Tables> list(String tableSchema) {
        String sql = "SELECT TABLE_SCHEMA as tablSchema, TABLE_NAME as tableName, TABLE_COMMENT as tableComment FROM TABLES where TABLE_SCHEMA='" + tableSchema + "'";
        List<Tables> query = JdbcTemplates.query(sql, new BeanHandler<>(Tables.class));
        conversion(query);
        return query;
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
                .append(UserContext.current().getDbMessage().getDbName())
                .append("' and TABLE_NAME in (");
        for (int i = 0; i < tableNames.length; i++) {

            if (i == tableNames.length - 1) {
                sql.append("'").append(tableNames[i]).append("')");
            } else {

                sql.append("'").append(tableNames[i]).append("', ");
            }

        }

        List<Tables> query = JdbcTemplates.query(sql.toString(), new BeanHandler<>(Tables.class));
        conversion(query);
        return query;
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
                .append(UserContext.current().getDbMessage().getDbName())
                .append("' and TABLE_NAME in (");
        List<Tables> query = JdbcTemplates.query(sql.toString(), new BeanHandler<>(Tables.class));
        conversion(query);
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }


    /**
     * 类型转换
     *
     * @param columns
     */
    private void conversion(List<Tables> columns) {
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        if (null == projectMetadata || columns.size() == 0) {

            columns = new ArrayList<>();
        }
        //转换

        columns.forEach(obj -> {
            obj.setTableName(obj.getTableName().toLowerCase());
            obj.setModelName(StringConversionUtil.splitStitching(obj.getTableName(), "_"));
            obj.setModelObjName(obj.getModelName().substring(0, 1).toLowerCase() + obj.getModelName().substring(1));
            if (StringUtils.isEmpty(obj.getTableComment())) {
                obj.setTableComment(obj.getModelObjName());
            } else if (obj.getTableComment().length() > 12) {
                obj.setTableComment(obj.getTableComment().substring(0, 10) + "...");
            }
        });

    }
}
