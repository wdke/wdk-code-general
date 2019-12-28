package com.wdk.general.core.service.impl;

import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.ParamCommonService;
import com.wdk.general.core.utils.ColumnsUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * created by wdk on 2019/12/18
 */
@Service
public class ParamCommonServiceImpl implements ParamCommonService {


    /**
     * 获取key值参数定义
     *
     * @param param
     */
    @Override
    public String keyParam(BaseParam param) {
        if (param.getKeys().isEmpty()) {
            return "";
        }
        StringBuffer params = new StringBuffer();
        for (int i = 0; i < param.getKeys().size(); i++) {
            SchemaColumns obj = param.getKeys().get(i);
            String column = ColumnsUtil.columns(obj.getColumnName(), "java");
            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

            if (i == 0) {
                params.append(dataType.getJavaType()).append(" ").append(column);
            } else {
                params.append(",").append(dataType.getJavaType()).append(" ").append(column);
            }

        }
        return params.toString();

    }


    /**
     * 获取key值参数
     *
     * @param param
     * @return
     */
    @Override
    public String keyMapperParam(BaseParam param) {
        if (param.getKeys().isEmpty()) {
            return "";
        }
        StringBuffer params = new StringBuffer();
        for (int i = 0; i < param.getKeys().size(); i++) {
            SchemaColumns obj = param.getKeys().get(i);
            String column = ColumnsUtil.columns(obj.getColumnName(), "java");
            if (i == 0) {
                params.append(column);
            } else {
                params.append(",").append(column);
            }

        }
        return params.toString();

    }

    /**
     * 主键作为路径的参数
     *
     * @param param
     * @return
     */
    @Override
    public Map<String, String> keyPathParam(BaseParam param) {

        if (param.getKeys().isEmpty()) {
            return new HashMap<>();
        }

        StringBuffer paths = new StringBuffer();
        StringBuffer args = new StringBuffer();
        StringBuffer values = new StringBuffer();

        for (int i = 0; i < param.getKeys().size(); i++) {
            SchemaColumns obj = param.getKeys().get(i);
            String column = ColumnsUtil.columns(obj.getColumnName(), "java");
            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
            paths.append("/{").append(column).append("}");
            if (i == 0) {
                args.append(dataType.getJavaType()).append(" ").append(column);
                values.append(column);
            } else {
                args.append(",").append(dataType.getJavaType()).append(" ").append(column);
                values.append(",").append(column);
            }

        }
        Map<String, String> map = new HashMap<>();
        map.put("path",paths.toString());
        map.put("args",args.toString());
        map.put("value",values.toString());
        return map;
    }
}
