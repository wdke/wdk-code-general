package com.wdk.general.core.service;

import com.wdk.general.core.model.SchemaColumns;

import java.util.List;

/**
 * created by wdk on 2019/12/18
 */
public interface VerifyService {

    /**
     * 数据列表校验
     *
     * @param sb
     * @param keys
     */
    void verify(StringBuilder sb, List<SchemaColumns> keys);


    /**
     * 单条校验
     *
     * @param sb
     * @param keys
     */
    void verify(StringBuilder sb, SchemaColumns keys);
}
