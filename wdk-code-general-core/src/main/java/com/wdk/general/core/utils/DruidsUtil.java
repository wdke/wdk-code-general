package com.wdk.general.core.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.wdk.general.core.model.DbMessage;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * created by wdk on 2019/12/11
 */
public class DruidsUtil {
    //供全局操作且用于静态代码块加载资源。
    private  DataSource dataSource;

    private DruidsUtil(){}

    // 工具类，私有化无参构造函数
    public DruidsUtil(DbMessage dbMessage) {
        Properties properties = new Properties();
        properties.setProperty("url","jdbc:mysql://"+dbMessage.getHost()+":"+dbMessage.getDbport()+"/information_schema?useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull");
        properties.setProperty("username",dbMessage.getDbuser());
        properties.setProperty("password",dbMessage.getDbpassword());
        properties.setProperty("driver-class-name","com.mysql.jdbc.Driver");
        properties.setProperty("type","com.alibaba.druid.pool.DruidDataSource");
        properties.setProperty("connectionProperties","druid.stat.mergeSql=true");
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据库连接实例
     * @return 数据库连接实例 connection
     */
    public  Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取数据库连接异常");
    }

    /**
     * 释放数据库连接 connection 到数据库缓存池，并关闭 rSet 和 pStatement 资源
     * @param rSet 数据库处理结果集
     * @param pStatement 数据库操作语句
     * @param connection 数据库连接对象
     */
    public static void releaseSqlConnection(ResultSet rSet, PreparedStatement pStatement, Connection connection) {
        try {
            if (rSet != null) {
                rSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pStatement != null) {
                    pStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static void main(String[] args) {


    }

}