package com.wdk.general.core.service;

/**
 * created by wdk on 2019/12/13
 */
public interface ConfigService {


    /**
     * eureka配置
     *
     * @param sb
     * @param addr
     */
    void eureka(StringBuilder sb, String addr);

    /**
     * redis配置
     *
     * @param sb
     * @param addr
     */
    void redis(StringBuilder sb, String addr);


    /**
     * 数据源配置
     *
     * @param sb
     */
    void thymeleafAndDatasource(StringBuilder sb);


    /**
     * 关于mybatis的配置
     *
     * @param sb
     * @param packages
     */
    void mybatis(StringBuilder sb, String packages);


    /**
     * 分页插件配置
     *
     * @param sb
     */
    void pagehelper(StringBuilder sb);

    /**
     * 生成application.yml
     *
     * @param name
     */
    void application(String name);


    /**
     * 生成application-xxx.yml
     *
     * @param branch
     * @param packages
     */
    void applicationBranch(String branch, String packages);


    /**
     * log文件
     *
     */
    void logBack();
}
