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
    String mainApplication(String name, String packages);


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
    String loginController(String packages);


    /**
     * 生成登陆Service
     */
    String loginService(String packages);


    /**
     * 生成登陆ServiceImpl
     */
    String loginServiceImpl(String packages);


    /**
     * 生成RedisConstant
     *
     * @param packages
     * @return
     */
    String redisConstant(String packages);


    /**
     * 生成 indexPages
     *
     * @param packages
     * @return
     */
    String indexPages(String packages);

}
