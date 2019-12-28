package com.wdk.general.core.service.impl;

import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.VerifyService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by wdk on 2019/12/18
 */

@Service
public class VerifyServiceImpl implements VerifyService {


    /**
     * 数据列表校验
     *
     * @param sb
     * @param keys
     */
    @Override
    public void verify(StringBuilder sb, List<SchemaColumns> keys) {
        if (null == keys || keys.size() == 0) {
            return;
        }

        for (SchemaColumns key : keys) {
            verify(sb, key);
        }

    }

    /**
     * 单挑校验
     *
     * @param sb
     * @param key
     */
    @Override
    public void verify(StringBuilder sb, SchemaColumns key) {
        if (null == key) {
            return;
        }
        String column = key.objName();

        JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(key.getDataType());
        if (dataType.getJavaType().equals("String")) {

            sb.append("\t\t//校验方法\n").append("\t\tif (StringUtils.isEmpty(").append(column).append(")) {\n")
                    .append("\t\t\tif(StringUtils.isEmpty(request.getParameter(\"").append(column).append("\"))){\n")
                    .append("\t\t\t\treturn new ResponseVo<>(ResponseStatusEnum.PARRAM_ERROR.getCode(),\"").append(column).append("不能为空！\");\n")
                    .append("\t\t\t}else {\n")
                    .append("\t\t\t\t").append(column).append(" = request.getParameter(\"").append(column).append("\");\n")
                    .append("\t\t\t}\n")
                    .append("\t\t}\n");

        } else if ("Integer".equals(dataType.getJavaType())) {


            //校验方法
            sb.append("\t\t//校验方法\n").append("\t\tif (").append(column).append(" == null) {\n")
                    .append("\t\t\tif(StringUtils.isEmpty(request.getParameter(\"").append(column).append("\"))){\n")
                    .append("\t\t\t\treturn new ResponseVo<>(ResponseStatusEnum.PARRAM_ERROR.getCode(),\"").append(column).append("不能为空！\");\n")
                    .append("\t\t\t}else {\n")
                    .append("\t\t\t\t").append(column).append(" = Integer.parseInt(request.getParameter(\"").append(column).append("\"));\n")
                    .append("\t\t\t}\n")
                    .append("\t\t}\n");
        } else {


            //校验方法
            sb.append("\t\t//校验方法\n").append("\t\tif (").append(column).append(" == null) {\n")
                    .append("\t\t\tif(StringUtils.isEmpty(request.getParameter(\"").append(column).append("\"))){\n")
                    .append("\t\t\t\treturn new ResponseVo<>(ResponseStatusEnum.PARRAM_ERROR.getCode(),\"").append(column).append("不能为空！\");\n")
                    .append("\t\t\t}else {\n")
                    .append("\t\t\t\t").append(column).append(" = ").append(dataType.getJavaType()).append(".parse").append(dataType.getJavaType()).append("(request.getParameter(\"").append(column).append("\"));\n")
                    .append("\t\t\t}\n")
                    .append("\t\t}\n");
        }

    }
}
