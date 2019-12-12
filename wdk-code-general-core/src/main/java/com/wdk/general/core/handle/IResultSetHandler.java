package com.wdk.general.core.handle;

import java.sql.ResultSet;

/**
 * created by wdk on 2019/12/11
 */
public interface IResultSetHandler<T> {
    T handler(ResultSet rSet);
}
