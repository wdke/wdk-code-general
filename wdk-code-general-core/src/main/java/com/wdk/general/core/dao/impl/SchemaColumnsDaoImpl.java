package com.wdk.general.core.dao.impl;

import com.wdk.general.core.common.model.UserContext;
import com.wdk.general.core.dao.SchemaColumnsDao;
import com.wdk.general.core.dao.template.JdbcTemplates;
import com.wdk.general.core.handle.impl.BeanHandler;
import com.wdk.general.core.model.ProjectMetadata;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.utils.StringConversionUtil;
import org.apache.commons.lang.StringUtils;
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

    private Logger logger = LoggerFactory.getLogger(SchemaColumnsDao.class);


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
                .append(UserContext.current().getDbMessage().getDbName()).append("'");
        List<SchemaColumns> columns = JdbcTemplates.query(sql.toString(), new BeanHandler<>(SchemaColumns.class));
        conversion(columns);
        return columns;
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
                .append(UserContext.current().getDbMessage().getDbName()).append("' and table_name in (");
        for (String table : tableName) {
            sql.append("'").append(table).append("',");
        }
        sql.replace(sql.length() - 1, sql.length(), ")");
        List<SchemaColumns> columns = JdbcTemplates.query(sql.toString(), new BeanHandler<>(SchemaColumns.class));

        conversion(columns);

        return columns;
    }

    /**
     * 类型转换
     *
     * @param columns
     */
    private void conversion(List<SchemaColumns> columns) {
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        if (null == projectMetadata || columns.size() == 0) {

            columns = new ArrayList<>();
        }
        //转换
        if (projectMetadata.getUseActualColumnNames()) {
            columns.forEach(obj -> {

                obj.setColumnName(obj.getColumnName().toLowerCase());
                obj.setModelName(obj.getColumnName().substring(0, 1).toUpperCase() + obj.getColumnName().substring(1));
                obj.setModelObjName(obj.getColumnName());

                if (StringUtils.isEmpty(obj.getColumnComment())) {
                    obj.setColumnComment(obj.getModelObjName());
                } else if (obj.getColumnComment().length() > 8) {
                    obj.setColumnComment(obj.getColumnComment().substring(0,4)+"...");
                }
            });
        } else {

            columns.forEach(obj -> {
                obj.setColumnName(obj.getColumnName().toLowerCase());
                obj.setModelName(StringConversionUtil.splitStitching(obj.getColumnName(), "_"));
                obj.setModelObjName(obj.getModelName().substring(0, 1).toLowerCase() + obj.getModelName().substring(1));

                if (StringUtils.isEmpty(obj.getColumnComment())) {
                    obj.setColumnComment(obj.getModelObjName());
                } else if (obj.getColumnComment().length() > 8) {
                    obj.setColumnComment(obj.getColumnComment().substring(0,4)+"...");
                }
            });
        }
    }
}
