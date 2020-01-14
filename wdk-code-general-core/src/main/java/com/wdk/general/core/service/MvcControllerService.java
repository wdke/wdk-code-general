package com.wdk.general.core.service;


import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;

import java.util.List;

public interface MvcControllerService {

    /**
     * 初始化生成
     *
     * @param baseParam
     * @param packages
     */
    void createFile(BaseParam baseParam, String packages);


    /**
     * 初始化生成
     *
     * @param baseParam
     * @param packages
     */
    String init(BaseParam baseParam, String packages);


    /**
     * 导入的包
     *
     * @param sb
     * @param modelName
     * @param packages
     */
    void imports(StringBuilder sb, String modelName, String packages);


    /**
     * 返回列表页
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     */
    public void index(StringBuilder sb, String modelName, String modelObjName);


    /**
     * 新增一条纪录
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param redirectPath
     */
    void insert(StringBuilder sb, String modelName, String modelObjName, String redirectPath);


    /**
     * 新增多条条纪录
     *
     * @param sb
     * @param param
     * @param packages
     */
    void batchInsert(StringBuilder sb, BaseParam param, String packages);


    /**
     * 进入新增页面
     *
     * @param sb
     * @param modelObjName
     */
    void insertPage(StringBuilder sb, String modelObjName);


    /**
     * 进入更新页面
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param keys
     */
    void updatePage(StringBuilder sb, String modelName, String modelObjName, List<SchemaColumns> keys);


    /**
     * 进入详情页面
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param keys
     */
    void detailPage(StringBuilder sb, String modelName, String modelObjName, List<SchemaColumns> keys);

    /**
     * 更新一条纪录
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param redirectPath
     */
    void update(StringBuilder sb, String modelName, String modelObjName, String redirectPath);

    /**
     * 删除方法
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param keys
     */
    void remove(StringBuilder sb, String modelName, String modelObjName, List<SchemaColumns> keys);


    /**
     * 进入通用页面
     *
     * @param sb
     * @param root
     * @param modelObjName
     * @param title
     */
    void page(StringBuilder sb, String root, String modelObjName, String title);

}
