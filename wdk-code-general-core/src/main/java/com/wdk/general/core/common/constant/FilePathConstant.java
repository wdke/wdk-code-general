package com.wdk.general.core.common.constant;

import com.wdk.general.core.common.model.UserContext;

/**
 * created by wdk on 2019/12/30
 */
public class FilePathConstant {

    /**
     * 页面路径
     *
     * @param modelName
     * @return
     */
    public static String pagesPath(String modelName) {
        return UserContext.current().getProjectServerRoot() + "/src/main/java/" + UserContext.current().getProjectMetadata().getPackages().replaceAll("\\.", "/") + "/web/pages/" + modelName + "Pages.java";
    }


    /**
     * API路径
     *
     * @param modelName
     * @return
     */
    public static String controllerPath(String modelName) {
        return UserContext.current().getProjectServerRoot() + "/src/main/java/" + UserContext.current().getProjectMetadata().getPackages().replaceAll("\\.", "/") + "/web/controller/" + modelName + "Controller.java";
    }


    /**
     * service路径
     *
     * @param modelName
     * @return
     */
    public static String servicePath(String modelName) {
        return UserContext.current().getProjectServerRoot() + "/src/main/java/" + UserContext.current().getProjectMetadata().getPackages().replaceAll("\\.", "/") + "/service/" + modelName + "Service.java";
    }


    /**
     * serviceImpl路径
     *
     * @param modelName
     * @return
     */
    public static String serviceImplPath(String modelName) {
        return UserContext.current().getProjectServerRoot() + "/src/main/java/" + UserContext.current().getProjectMetadata().getPackages().replaceAll("\\.", "/") + "/service/impl/" + modelName + "ServiceImpl.java";
    }

    /**
     * 页面路径
     *
     * @param modelName
     * @return
     */
    public static String model(String modelName) {
        return UserContext.current().getProjectServerRoot() + "/src/main/java/" + UserContext.current().getProjectMetadata().getPackages().replaceAll("\\.", "/") + "/storage/entity/" + modelName + ".java";
    }


    /**
     * 页面路径
     *
     * @param modelName
     * @return
     */
    public static String dao(String modelName) {
        return UserContext.current().getProjectServerRoot() + "/src/main/java/" + UserContext.current().getProjectMetadata().getPackages().replaceAll("\\.", "/") + "/storage/dao/" + modelName + "Mapper.java";
    }
    /**
     * 页面路径
     *
     * @param modelName
     * @return
     */
    public static String args(String modelName) {
        return UserContext.current().getProjectServerRoot() + "/src/main/java/" + UserContext.current().getProjectMetadata().getPackages().replaceAll("\\.", "/") + "/web/args/" + modelName + "Args.java";
    }


    /**
     * 页面路径
     *
     * @param modelName
     * @return
     */
    public static String vo(String modelName) {
        return UserContext.current().getProjectServerRoot() + "/src/main/java/" + UserContext.current().getProjectMetadata().getPackages().replaceAll("\\.", "/") + "/web/vo/" + modelName + "Vo.java";
    }

    /**
     * mybatis 工具配置文件路径
     *
     * @return
     */
    public static String generatorConfig() {
        return UserContext.current().getProjectServerRoot() + "/src/main/generator/generatorConfig.xml";
    }
}
