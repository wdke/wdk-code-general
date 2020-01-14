package com.wdk.general.core.service.impl;

import com.wdk.general.core.common.constant.FilePathConstant;
import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.MapperDaoService;
import com.wdk.general.core.utils.StringConversionUtil;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wdke
 * @date 2019/5/15
 */
@Service
public class MapperDaoServiceImpl implements MapperDaoService {

    /**
     * 初始化生成dao
     *
     * @param param
     * @param packages
     */
    @Override
    public void initDao(BaseParam param, String packages) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        StringBuffer mapper = new StringBuffer();
        mapper.append("package ").append(packages).append(".storage.dao;\n");
        imports(mapper, param, packages);

        mapper.append("/**\n")
                .append(param.getTableComment()).append("\n")
                .append(" * @author wdke\n")
                .append(" * @date ").append(sdf.format(new Date())).append("\n")
                .append(" */\n")
                .append("@Repository\n");
        mapper.append("public interface ").append(param.getModelName()).append("Mapper{\n\n");


        mapper.append("\t/**\n")
                .append("\t * 根据map获取查询Map列表\n")
                .append("\t * @param param ：数据库对应key\n")
                .append("\t * @return：数据库对应key的map数组\n")
                .append("\t */\n");
        mapper.append("\tList<Map<String,Object>> selectListByMapReturnMap(Map<String,Object> param);\n\n");


        mapper.append("\t/**\n")
                .append("\t * 根据map获取查询列表\n")
                .append("\t * @param param ： key为java对应字段的map\n")
                .append("\t * @return\n")
                .append("\t */\n");
        mapper.append("\tList<").append(param.getModelName()).append("> selectListByMap(Map<String,Object> param);\n\n");

        mapper.append("\t/**\n")
                .append("\t * 获取查询列表\n")
                .append("\t * @param param ：不为空的属性\n")
                .append("\t * @return\n")
                .append("\t */\n");
        mapper.append("\tList<").append(param.getModelName()).append("> list(").append(param.getModelName()).append(" param);\n\n");

        mapper.append("\t/**\n")
                .append("\t * 统计接口\n")
                .append("\t * @param param ：不为空的属性\n")
                .append("\t * @return\n")
                .append("\t */\n");
        mapper.append("\tInteger count(").append(param.getModelName()).append(" param);\n\n");

        mapper.append("\t/**\n")
                .append("\t * 全量新增\n")
                .append("\t * @param param\n")
                .append("\t * @return\n")
                .append("\t */\n");
        mapper.append("\tint insert(").append(param.getModelName()).append(" param);\n\n");

        mapper.append("\t/**\n")
                .append("\t * 不为空新增\n")
                .append("\t * @param param\n")
                .append("\t * @return\n")
                .append("\t */\n");
        mapper.append("\tint insertSelective(").append(param.getModelName()).append(" param);\n\n");

        mapper.append("\t/**\n")
                .append("\t * 批量新增\n")
                .append("\t * @param param\n")
                .append("\t * @return\n")
                .append("\t */\n");
        mapper.append("\tint batchInsert(List<").append(param.getModelName()).append("> param);\n\n");

        if (null != param.getKeys() && !param.getKeys().isEmpty()) {
            StringBuffer params = new StringBuffer();
            for (int i = 0; i < param.getKeys().size(); i++) {
                SchemaColumns obj = param.getKeys().get(i);
                String column = obj.getModelObjName();
                JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                if (i == 0) {
                    params.append("@Param(\"").append(column).append("\") ").append(dataType.getJavaType()).append(" param");
                } else {
                    params.append(",@Param(\"").append(column).append("\") ").append(dataType.getJavaType()).append(" param").append(i);
                }

            }

            mapper.append("\t/**\n")
                    .append("\t * 获取查询列表\n")
                    .append("\t * @param param ：不为空的属性\n")
                    .append("\t * @return\n")
                    .append("\t */\n");
            mapper.append("\t").append(param.getModelName()).append(" selectByPrimaryKey(").append(params).append(");\n\n");

            mapper.append("\t/**\n")
                    .append("\t * 更新全量\n")
                    .append("\t * @param param\n")
                    .append("\t * @return\n")
                    .append("\t */\n");
            mapper.append("\tint updateByPrimaryKey(").append(param.getModelName()).append(" param);\n\n");

            mapper.append("\t/**\n")
                    .append("\t * 更新不为空\n")
                    .append("\t * @param param\n")
                    .append("\t * @return\n")
                    .append("\t */\n");
            mapper.append("\tint updateSelectiveByPrimaryKey(").append(param.getModelName()).append(" param);\n\n");

            mapper.append("\t/**\n")
                    .append("\t * 批量存在就更新，不存在就新增\n")
                    .append("\t * @param param：需要新增或根据key值更新的对象数组\n")
                    .append("\t * @return\n")
                    .append("\t */\n");
            mapper.append("\tint batchInsertUpdate(List<").append(param.getModelName()).append("> param);\n\n");


            mapper.append("\t/**\n")
                    .append("\t * 批量更新\n")
                    .append("\t * @param param：需要根据key值更新的对象数组\n")
                    .append("\t * @return\n")
                    .append("\t */\n");
            mapper.append("\tint batchUpdate(List<").append(param.getModelName()).append("> param);\n\n");


            mapper.append("\t/**\n")
                    .append("\t * 根据主键删除\n")
                    .append("\t * @param param：对应数据库唯一健\n")
                    .append("\t * @return\n")
                    .append("\t */\n");
            mapper.append("\tint deleteByPrimaryKey(").append(params).append(");\n\n");
        }

        mapper.append("\t/**\n")
                .append("\t * 根据条件删除\n")
                .append("\t * @param param\n")
                .append("\t * @return\n")
                .append("\t */\n");
        mapper.append("\tint deleteBySelect(").append(param.getModelName()).append(" param);\n\n}");
        try {
            FileUtil.write(FilePathConstant.dao(param.getModelName()), mapper.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成 import
     *
     * @param mapper
     */
    @Override
    public void imports(StringBuffer mapper, BaseParam param, String packages) {


        mapper.append("\n")
                .append("import ").append(packages).append(".storage.entity.").append(param.getModelName()).append(";\n")
                .append("import java.util.List;\n")
                .append("import java.util.Map;\n")
                .append("import org.apache.ibatis.annotations.Param;\n")
                .append("import org.springframework.stereotype.Repository;\n\n");
    }
}
