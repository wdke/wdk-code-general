package com.wdk.general.core.service;


import com.wdk.general.core.model.BaseParam;

public interface ServiceService {


    /**
     * 初始化生成service和serviceImpl
     *
     * @param param
     * @param packages
     */
    void init(BaseParam param, String packages);


    /**
     * 初始化固定service接口
     *
     * @param param
     * @param packages
     */
    void initReturnData(BaseParam param, String packages);


    /**
     * 生成imports
     *
     * @param service
     * @param param
     * @param packages
     */
    void imports(StringBuffer service, BaseParam param, String packages);


    /**
     * 生成importImpls
     *
     * @param service
     * @param param
     * @param packages
     */
    void importImpls(StringBuffer service, BaseParam param, String packages);


    /**
     * 根据map获取查询Map列表
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     * @return
     */
    void selectListByMapReturnMap(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);

    /**
     * 根据map获取查询列表
     *
     * @param param
     * @param packages
     * @return
     */
    void selectListByMap(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);

    /**
     * 获取查询列表
     *
     * @param param
     * @param packages
     * @return
     */
    void selectList(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);

    /**
     * 统计接口
     *
     * @param param
     * @param packages
     * @return
     */
    void count(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);

    /**
     * 根据主键查询数据
     *
     * @param param
     * @param packages
     * @return
     */
    void selectByPrimaryKey(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);

    /**
     * 分页查询
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    void selectPageInfo(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);

    /**
     * 新增
     *
     * @param param
     * @param packages
     * @return
     */
    void insert(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);


    /**
     * 不为空的新增
     *
     * @param param
     * @param packages
     * @return
     */
    void insertSelective(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);


    /**
     * 批量新增
     *
     * @param param
     * @param packages
     * @return
     */
    void batchInsert(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);


    /**
     * 更新全量
     *
     * @param param
     * @param packages
     * @return
     */
    void updateByPrimaryKey(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);


    /**
     * 更新不为空
     *
     * @param param
     * @param packages
     * @return
     */
    void updateSelectiveByPrimaryKey(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);

    /**
     * 批量存在就更新，不存在就新增
     *
     * @param param
     * @param packages
     * @return
     */
    void batchInsertUpdate(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);


    /**
     * 批量更新
     *
     * @param param
     * @param packages
     * @return
     */
    void batchUpdate(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);


    /**
     * 根据主键删除
     *
     * @param param
     * @param packages
     * @return
     */
    void deleteByPrimaryKey(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);


    /**
     * 根据查询条件删除
     *
     * @param param
     * @param packages
     * @return
     */
    void deleteBySelect(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages);


}
