package com.wdk.general.core.service;

/**
 * created by wdk on 2019/12/13
 */
public interface ConfigService {


    /**
     * 数据源配置
     *
     * @param sb
     */
    void datasource(StringBuilder sb);


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
     * @param branch
     * @param path
     */
    void logBack(String branch, String path);


}
