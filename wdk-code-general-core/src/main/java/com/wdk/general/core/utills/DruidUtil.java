package com.wdk.general.core.utills;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.wdk.general.core.model.DbMessage;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * created by wdk on 2019/12/11
 */
public class DruidUtil {

    // 静态数据源变量，供全局操作且用于静态代码块加载资源。
    private static DataSource dataSource;

    private static ConcurrentHashMap<Integer, DataSource> map = new ConcurrentHashMap<Integer, DataSource>();


    // 工具类，私有化无参构造函数
    private DruidUtil() {

        InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(inStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            map.put(0, dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 工具类，私有化无参构造函数
    private DruidUtil(DbMessage dbMessage) {
        if (null == map.get(dbMessage.getUserId())) {

            Properties properties = new Properties();
            properties.setProperty("url", "jdbc:mysql://" + dbMessage.getHost() + ":" + dbMessage.getDbport() + "/information_schema?useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull");
            properties.setProperty("username", dbMessage.getDbuser());
            properties.setProperty("password", dbMessage.getDbpassword());
            properties.setProperty("driver-class-name", "com.mysql.jdbc.Driver");
            properties.setProperty("type", "com.alibaba.druid.pool.DruidDataSource");
            properties.setProperty("connectionProperties", "druid.stat.mergeSql=true");
            try {
                map.put(dbMessage.getUserId(), DruidDataSourceFactory.createDataSource(properties));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // 静态代码块，加载配置文件。

    /**
     * 创建数据库连接实例
     *
     * @return 数据库连接实例 connection
     */
    public static Connection getConnection() {
        if (null == map.get(0) || null == dataSource) {
            new DruidUtil();
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取数据库连接异常");
    }

    /**
     * 创建数据库连接实例
     *
     * @return 数据库连接实例 connection
     */
    public static Connection getConnection(DbMessage dbMessage) {
        if (null == map.get(dbMessage.getUserId())) {
            new DruidUtil(dbMessage);
        }
        try {
            return map.get(dbMessage.getUserId()).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取数据库连接异常");
    }

    /**
     * 释放数据库连接 connection 到数据库缓存池，并关闭 rSet 和 pStatement 资源
     *
     * @param rSet       数据库处理结果集
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

}