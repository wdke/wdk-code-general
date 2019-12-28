package com.wdk.general.core.service;

import com.wdk.general.core.model.BaseParam;
import org.dom4j.Element;

public interface MapperXmlService {

    void mapperXml(BaseParam param, String packages);

    /**
     * 生成id为baseResultMap的result
     *
     * @param param
     * @param packages
     * @return
     */
    String baseResultMap(BaseParam param, String packages, Element root);

    /**
     * 查询字段生成
     *
     * @param param
     * @return
     */
    String BaseColumnsSql(BaseParam param, Element root);


    /**
     * 生成查询条件
     *
     * @param param
     * @param root
     * @return
     */
    String BaseWhereSql(BaseParam param, Element root);


    /**
     * 生成数据库字段查询条件
     *
     * @param param
     * @param root
     * @return
     */
    String BaseWhereDbSql(BaseParam param, Element root);

    /**
     * 生成table sql
     *
     * @param param
     * @param root
     * @return
     */
    String BaseTablesSql(BaseParam param, Element root);

    /**
     * 根据map获取查询Map列表
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    String selectListByMapReturnMap(BaseParam param, String packages, Element root);

    /**
     * 根据map获取查询列表
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    String selectListByMap(BaseParam param, String packages, Element root);

    /**
     * 获取查询列表
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    String selectList(BaseParam param, String packages, Element root);

    /**
     * 根据主键查询一条记录
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    String selectByPrimaryKey(BaseParam param, String packages, Element root);

    /**
     * 新增
     *
     * @param param
     * @param packages
     * @return
     */
    String insert(BaseParam param, String packages, Element root);


    /**
     * 不为空的新增
     *
     * @param param
     * @param packages
     * @return
     */
    String insertSelective(BaseParam param, String packages, Element root);


    /**
     * 批量新增
     *
     * @param param
     * @param packages
     * @return
     */
    String batchInsert(BaseParam param, String packages, Element roots);


    /**
     * 更新全量
     *
     * @param param
     * @param packages
     * @return
     */
    String updateByPrimaryKey(BaseParam param, String packages, Element root);


    /**
     * 更新不为空
     *
     * @param param
     * @param packages
     * @return
     */
    String updateSelectiveByPrimaryKey(BaseParam param, String packages, Element root);

    /**
     * 批量存在就更新，不存在就新增
     *
     * @param param
     * @param packages
     * @return
     */
    String batchInsertUpdate(BaseParam param, String packages, Element root);


    /**
     * 批量更新
     *
     * @param param
     * @param packages
     * @return
     */
    String batchUpdate(BaseParam param, String packages, Element root);


    /**
     * 根据主键删除
     *
     * @param param
     * @param packages
     * @return
     */
    String deleteByPrimaryKey(BaseParam param, String packages, Element root);


    /**
     * 根据查询条件删除
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    String deleteBySelect(BaseParam param, String packages, Element root);


    /**
     * 生成一个select
     *
     * @param param
     * @param packages
     * @return
     */
    String selectByEntity(BaseParam param, String packages);


}
