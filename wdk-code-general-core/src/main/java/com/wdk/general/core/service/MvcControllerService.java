package com.wdk.general.core.service;


import com.wdk.general.core.model.BaseParam;

public interface MvcControllerService {


    /**
     * 初始化生成
     *
     * @param param
     * @param packages
     */
    void init(BaseParam param, String packages);


    /**
     * 导入的包
     *
     * @param sb
     * @param param
     * @param packages
     */
    void imports(StringBuilder sb, BaseParam param, String packages);


    /**
     * 返回列表页
     *
     * @param sb
     * @param param
     */
    void index(StringBuilder sb, BaseParam param);


    /**
     * 新增一条纪录
     *
     * @param sb
     * @param param
     */
    void insert(StringBuilder sb, BaseParam param);


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
     * @param param
     */
    void insertPage(StringBuilder sb, BaseParam param);


    /**
     * 进入更新页面
     *
     * @param sb
     * @param param
     */
    void updatePage(StringBuilder sb, BaseParam param);

    /**
     * 更新一条纪录
     *
     * @param sb
     * @param param
     */
    void update(StringBuilder sb, BaseParam param);

    /**
     * 删除方法
     *
     * @param baseParam
     * @return
     */
    void remove(StringBuilder sb, BaseParam baseParam);


    /**
     * 进入通用页面
     *
     * @param sb
     * @param root
     * @param baseParam
     * @param title
     */
    void page(StringBuilder sb, String root, BaseParam baseParam, String title);

}
