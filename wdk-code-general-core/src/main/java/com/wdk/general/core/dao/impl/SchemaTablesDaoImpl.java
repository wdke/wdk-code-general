package com.wdk.general.core.dao.impl;

import com.wdk.general.core.dao.SchemaTablesDao;
import com.wdk.general.core.dao.template.JdbcTemplates;
import com.wdk.general.core.handle.impl.BeanHandler;
import com.wdk.general.core.model.Tables;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by wdk on 2019/12/11
 */
@Service
public class SchemaTablesDaoImpl implements SchemaTablesDao {

    //获取所有表信息
    @Override
    public List<Tables> list() {
        String sql = "SELECT TABLE_SCHEMA as tablSchema, TABLE_NAME as tableName, TABLE_COMMENT as tableComment FROM TABLES where TABLE_SCHEMA='" + UserContext.current().getDbMessage().getDbname() + "'";
        return JdbcTemplates.query(sql, new BeanHandler<>(Tables.class));
    }
}
