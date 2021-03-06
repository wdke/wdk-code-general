package com.wdk.general.core.service;

import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;

import java.util.List;

/**
 * @author wdke
 * @date 2019/5/8
 */
public interface ModelService {


    /**
     * 生成model
     *
     * @param param
     * @param packages
     */
    void init(BaseParam param, String packages);

    /**
     * 生成lombok 对应的类
     *
     * @return
     */
    String lombokModel(BaseParam param, String packages);


    /**
     * 生成lombok 对应的Vo类
     *
     * @param param
     * @return
     */
    String lombokModelVo(BaseParam param, String packages);


    /**
     * 生成lombok 对应的Param类
     *
     * @param param
     * @return
     */
    String lombokModelArgs(BaseParam param, String packages);




    /**
     * 普通的model,包含getset方法
     *
     * @param param
     * @return
     */
    String commonModel(BaseParam param);

    /**
     * 普通的model,包含getset方法Vo
     *
     * @param param
     * @return
     */
    String commonModelVo(BaseParam param);

    /**
     * 普通的model,包含getset方法Param
     *
     * @param param
     * @return
     */
    String commonModelParam(BaseParam param);


    /**
     * 生成属性值
     *
     * @param sb
     * @param columns
     * @return
     */
    void property(StringBuilder sb, List<SchemaColumns> columns, Boolean annotation);


}
