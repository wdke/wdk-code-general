package com.wdk.general.core.dao.template;

import com.wdk.general.core.handle.IResultSetHandler;
import com.wdk.general.core.utils.DruidUtil;
import com.wdk.general.core.web.Interceptor.UserContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplates {

    // 工具类，私有化无参构造函数
    private JdbcTemplates() {
    }

    /**
     * DML 操作模板方法
     * @param sql 执行操作的 SQL 语句
     * @param arguments SQL 语句参数
     */
    public static void update(String sql, Object... arguments) {
        // 获取数据库连接 connection
        Connection connection = DruidUtil.getConnection(UserContext.current().getDbMessage());
        // 创建数据库语句载体
        PreparedStatement pStatement = null;
        try {
            pStatement = connection.prepareStatement(sql);
            // 给预编译好的 sql 语句中的占位符进行赋值
            if (arguments != null && arguments.length > 0) {
                for (int i = 0; i < arguments.length; i++) {
                    pStatement.setObject(i + 1, arguments[i]);
                }
            }
            // 执行 SQL 语句
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放数据库连接
            DruidUtil.releaseSqlConnection(null, pStatement, connection);
        }
    }

    /**
     * DQL 操作模板
     * @param sql 执行操作的 SQL 语句
     * @param handler 对数据库返回结果集进行装箱的操作类
     * @param arguments SQL 语句参数
     * @return 返回数据库查询结果集
     */
    public static <T> T query(String sql, IResultSetHandler<T> handler, Object... arguments) {
        Connection connection = DruidUtil.getConnection(UserContext.current().getDbMessage());
        PreparedStatement pStatement = null;
        ResultSet rSet = null;
        try {
            pStatement = connection.prepareStatement(sql);
            if (arguments != null && arguments.length > 0) {
                for (int i = 0; i < arguments.length; i++) {
                    pStatement.setObject(i + 1, arguments[i]);
                }
            }
            rSet = pStatement.executeQuery();
            // 调用处理结果集类对数据库查询结果集进行装箱
            return handler.handler(rSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DruidUtil.releaseSqlConnection(rSet, pStatement, connection);
        }
        return null;
    }

}
