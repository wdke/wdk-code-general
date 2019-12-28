package com.wdk.general.core.service;


import com.wdk.general.core.model.BaseParam;

public interface MapperDaoService {


    void initDao(BaseParam param, String packages);


    /**
     * 生成imports
     * @param mapper
     */
    void imports(StringBuffer mapper, BaseParam param, String packages);
}
