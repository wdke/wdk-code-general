package com.wdk.general.core.service;

import com.wdk.general.core.common.model.ResponseVo;
import com.wdk.general.core.model.DbMessage;

/**
 * created by wdk on 2020/1/2
 */
public interface DbMessagesService {


    /**
     * 保存数据库
     *
     * @param db
     * @return
     */
    ResponseVo<Integer> save(DbMessage db);
}
