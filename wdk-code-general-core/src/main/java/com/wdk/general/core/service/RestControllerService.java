package com.wdk.general.core.service;


import com.wdk.general.core.model.BaseParam;

public interface RestControllerService {


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
     * 生成统计方法
     *
     * @param sb
     * @param param
     */
    void count(StringBuilder sb, BaseParam param);


    /**
     * 查询分页数据
     *
     * @param sb
     * @param param
     */
    void index(StringBuilder sb, BaseParam param);


    /**
     * 查询列表
     *
     * @param sb
     * @param param
     */
    void list(StringBuilder sb, BaseParam param);

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
     * 详情
     *
     * @param baseParam
     * @return
     */
    void detail(StringBuilder sb, BaseParam baseParam);

}
