package com.wdk.general.core.service;

import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.ParamGenerate;
import com.wdk.general.core.model.SchemaColumns;

import java.util.List;
import java.util.Map;

/**
 * created by wdk on 2019/12/18
 */
public interface ParamCommonService {


    /**
     * 获取key值相关参数定义
     *
     * @param param
     */
    String keyParam(BaseParam param);


    /**
     * 获取key值相关参数
     *
     * @param param
     */
    String keyMapperParam(BaseParam param);


    /**
     * 主键作为路径的参数
     *
     * @param keys
     */
    ParamGenerate keyPathParam(List<SchemaColumns> keys);
}
