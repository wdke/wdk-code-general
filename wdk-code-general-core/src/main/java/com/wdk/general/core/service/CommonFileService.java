package com.wdk.general.core.service;

/**
 * created by wdk on 2019/12/13
 */
public interface CommonFileService {

    /**
     * 生成所有文件
     *
     * @param name
     * @param packages
     */
    void all(String name, String packages);

    /**
     * 生成运行文件
     */
    void run();

    /**
     * 启动函数配置
     *
     * @param name
     * @param packages
     */
    void mainApplication(String name, String packages);


    /**
     * 生成dockerfile文件
     */
    void dockerfile();


    /**
     * 生成docker-manager.sh
     */
    void dockerManager();


    /**
     * 生成docker-compose.yml文件
     */
    void dockerCompose();


    /**
     * 创建shell执行脚本
     */
    void shellFile();


    /**
     * 生成登陆API
     */
    void loginController();


    /**
     * 生成登陆Service
     */
    void loginService();


    /**
     * 生成登陆ServiceImpl
     */
    void loginServiceImpl();


    /**
     * 生成RedisConstant
     */
    void redisConstant();

}
