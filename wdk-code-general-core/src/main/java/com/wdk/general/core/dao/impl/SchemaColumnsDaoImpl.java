package com.wdk.general.core.dao.impl;

import com.wdk.general.core.dao.SchemaColumnsDao;
import com.wdk.general.core.dao.template.JdbcTemplates;
import com.wdk.general.core.handle.impl.BeanHandler;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * created by wdk on 2019/12/12
 */
@Service
public class SchemaColumnsDaoImpl implements SchemaColumnsDao {

    private Logger logger= LoggerFactory.getLogger(SchemaColumnsDao.class);


    /**
     * 获取所有的数据库表字段
     *
     * @return
     */
    @Override
    public List<SchemaColumns> list() {


        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append("table_catalog as tableCatalog,")
                .append("table_schema as tableSchema,")
                .append("table_name as tableName,")
                .append("column_name as columnName,")
                .append("ordinal_position as ordinalPosition,")
                .append("is_nullable as isNullable,")
                .append("data_type as dataType,")
                .append("character_maximum_length as characterMaximumLength,")
                .append("character_octet_length as characterOctetLength,")
                .append("numeric_precision as numericPrecision,")
                .append("numeric_scale as numericScale,")
                .append("datetime_precision as datetimePrecision,")
                .append("character_set_name as characterSetName,")
                .append("collation_name as collationName,")
                .append("column_key as columnKey,")
                .append("extra as extra,")
                .append("privileges as privileges,")
                .append("column_comment as columnComment")
                .append(" from FROM COLUMNS where TABLE_SCHEMA='")
                .append(UserContext.current().getDbMessage().getDbname()).append("'");
        return JdbcTemplates.query(sql.toString(), new BeanHandler<>(SchemaColumns.class));
    }

    /**
     * 获取指定数据库表的数据
     *
     * @param tableName
     * @return
     */
    @Override
    public List<SchemaColumns> list(String[] tableName) {


        if (null == tableName || tableName.length == 0) {
            logger.info("查询表字段时指定数据库表为空，返回空值！");
            return new ArrayList<>();
        }


        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append("table_catalog as tableCatalog,")
                .append("table_schema as tableSchema,")
                .append("table_name as tableName,")
                .append("column_name as columnName,")
                .append("ordinal_position as ordinalPosition,")
                .append("is_nullable as isNullable,")
                .append("data_type as dataType,")
                .append("character_maximum_length as characterMaximumLength,")
                .append("character_octet_length as characterOctetLength,")
                .append("numeric_precision as numericPrecision,")
                .append("numeric_scale as numericScale,")
                .append("datetime_precision as datetimePrecision,")
                .append("character_set_name as characterSetName,")
                .append("collation_name as collationName,")
                .append("column_key as columnKey,")
                .append("extra as extra,")
                .append("privileges as privileges,")
                .append("column_comment as columnComment")
                .append(" from COLUMNS \n where  TABLE_SCHEMA='")
                .append(UserContext.current().getDbMessage().getDbname()).append("' and table_name in (");
        for(String table:tableName){
            sql.append("'").append(table).append("',");
        }
        sql.replace(sql.length()-1,sql.length(),")");

        System.out.println(sql.toString());

        return JdbcTemplates.query(sql.toString(), new BeanHandler<>(SchemaColumns.class));
    }
}
