package com.wdk.general.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.ParamCommonService;
import com.wdk.general.core.service.ServiceService;
import com.wdk.general.core.utils.ColumnsUtil;
import com.wdk.general.core.utils.DateUtils;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wdke
 * @date 2019/5/23
 */
@Service
public class ServiceServiceImpl implements ServiceService {

    private static Logger logger = LoggerFactory.getLogger(ServiceServiceImpl.class);

    @Autowired
    private ParamCommonService paramCommonService;

    @Override
    public void init(BaseParam param, String packages) {
        logger.info("init->start: param:【 param->{},packages->{} 】", JSON.toJSONString(param), packages);
        String file = UserContext.current().getProjectServerRoot() + "/src/main/java/" + packages.replaceAll("\\.", "/") + "/service/";//+param.getModelName()+"Service.java"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        StringBuffer service = new StringBuffer();
        StringBuffer serviceImpl = new StringBuffer();

        //初始化生成impirt
        service.append("package ").append(packages).append(".service;\n");
        imports(service, param, packages);
        serviceImpl.append("package ").append(packages).append(".service.impl;\n");
        importImpls(serviceImpl, param, packages);


        //定义接口service
        service.append("/**\n")
                .append(" *").append(param.getTableComment()).append("\n")
                .append(" * @author wdke\n")
                .append(" * @date ").append(sdf.format(new Date())).append("\n")
                .append(" */\n");
        service.append("public interface ").append(param.getModelName()).append("Service{\n\n");

        //定义接口实现类
        serviceImpl.append("/**\n")
                .append(" *").append(param.getTableComment()).append("\n")
                .append(" * @author wdke\n")
                .append(" * @date ").append(sdf.format(new Date())).append("\n")
                .append(" */\n");
        serviceImpl.append("@Service\n");
        serviceImpl.append("public class ").append(param.getModelName()).append("ServiceImpl implements ").append(param.getModelName()).append("Service{\n\n");

        //logger对象
        serviceImpl.append("\tprivate static Logger logger=LoggerFactory.getLogger(")
                .append(param.getModelName())
                .append("ServiceImpl.class);\n\n");


        //mapper对象
        serviceImpl.append("\t@Autowired\n")
                .append("\tprivate ")
                .append(param.getModelName())
                .append("Mapper ")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Mapper;\n\n\n\n");

        //根据map获取查询Map列表
        selectListByMapReturnMap(service, serviceImpl, param, packages);

        //根据map获取查询列表
        selectListByMap(service, serviceImpl, param, packages);

        //获取查询列表
        selectList(service, serviceImpl, param, packages);

        //统计接口
        count(service, serviceImpl, param, packages);

        //根据主键查询数据列表
        selectByPrimaryKey(service, serviceImpl, param, packages);


        //获取查询列表
        selectPageInfo(service, serviceImpl, param, packages);

        //新增
        insert(service, serviceImpl, param, packages);

        //不为空的新增
        insertSelective(service, serviceImpl, param, packages);

        //批量新增
        batchInsert(service, serviceImpl, param, packages);

        //更新全量
        updateByPrimaryKey(service, serviceImpl, param, packages);

        //更新不为空
        updateSelectiveByPrimaryKey(service, serviceImpl, param, packages);

        //批量存在就更新，不存在就新增
        batchInsertUpdate(service, serviceImpl, param, packages);

        //批量更新
        batchUpdate(service, serviceImpl, param, packages);

        //根据主键删除
        deleteByPrimaryKey(service, serviceImpl, param, packages);

        //根据查询条件删除
        deleteBySelect(service, serviceImpl, param, packages);

        service.append("\n\n}");
        serviceImpl.append("\n\n}");


        try {
            FileUtil.write(file + param.getModelName() + "Service.java", service.toString());
            FileUtil.write(file + "impl/" + param.getModelName() + "ServiceImpl.java", serviceImpl.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化生成
     *
     * @param param
     * @param packages
     */
    @Override
    public void initReturnData(BaseParam param, String packages) {
        logger.info("init->start: param:【 param->{},packages->{} 】", JSON.toJSONString(param), packages);


        String file = UserContext.current().getProjectRoot() + "/java/" + packages.replaceAll("\\.", "/") + "/service/" + param.getModelName() + "Service.java";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        StringBuffer service = new StringBuffer();
        service.append("package ").append(packages).append(".service;\n");
        //导入数据
        imports(service, param, packages);

        service.append("/**\n")
                .append(param.getTableComment()).append("\n")
                .append(" * @author wdke\n")
                .append(" * @date ").append(sdf.format(new Date())).append("\n")
                .append(" */\n");
        service.append("public interface ").append(param.getModelName()).append("Service{\n\n");


        service.append("\t/**\n")
                .append("\t* 根据map获取查询Map列表\n")
                .append("\t* @param param\n")
                .append("\t* @return\n")
                .append("\t*/\n");
        service.append("\tList<Map<String,Object>> selectListByMapReturnMap(Map<String,Object> param);\n\n");


        service.append("\t/**\n")
                .append("\t* 根据map获取查询列表\n")
                .append("\t* @param param\n")
                .append("\t* @return\n")
                .append("\t*/\n");
        service.append("\tList<").append(param.getModelName()).append("> selectListByMap(Map<String,Object> param);\n\n");

        service.append("\t/**\n")
                .append("\t* 获取查询列表\n")
                .append("\t* @param param\n")
                .append("\t* @return\n")
                .append("\t*/\n");
        service.append("\tList<").append(param.getModelName()).append("> list(").append(param.getModelName()).append(" param);\n\n");

        service.append("\t/**\n")
                .append("\t* 全量新增\n")
                .append("\t* @param param\n")
                .append("\t* @return\n")
                .append("\t*/\n");
        service.append("\tint insert(").append(param.getModelName()).append(" param);\n\n");

        service.append("\t/**\n")
                .append("\t* 不为空新增\n")
                .append("\t* @param param\n")
                .append("\t* @return\n")
                .append("\t*/\n");
        service.append("\tint insertSelective(").append(param.getModelName()).append(" param);\n\n");

        service.append("\t/**\n")
                .append("\t* 批量新增\n")
                .append("\t* @param param\n")
                .append("\t* @return\n")
                .append("\t*/\n");
        service.append("\tint batchInsert(List<").append(param.getModelName()).append("> param);\n\n");

        if (!param.getKeys().isEmpty()) {
            StringBuffer params = new StringBuffer();
            for (int i = 0; i < param.getKeys().size(); i++) {
                SchemaColumns obj = param.getKeys().get(i);
                String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                if (i == 0) {
                    params.append("@Param(\"").append(column).append("\") ").append(dataType.getJavaType()).append(" param").append(i);
                } else {
                    params.append(",@Param(\"").append(column).append("\") ").append(dataType.getJavaType()).append(" param").append(i);
                }

            }
            service.append("\t/**\n")
                    .append("\t* 更新全量\n")
                    .append("\t* @param param\n")
                    .append("\t* @return\n")
                    .append("\t*/\n");
            service.append("\tint updateByPrimaryKey(").append(params).append(");\n\n");

            service.append("\t/**\n")
                    .append("\t* 更新不为空\n")
                    .append("\t* @param param\n")
                    .append("\t* @return\n")
                    .append("\t*/\n");
            service.append("\tint updateSelectiveByPrimaryKey(").append(params).append(");\n\n");

            service.append("\t/**\n")
                    .append("\t* 批量存在就更新，不存在就新增\n")
                    .append("\t* @param param\n")
                    .append("\t* @return\n")
                    .append("\t*/\n");
            service.append("\tint batchInsertUpdate(").append(params).append(");\n\n");


            service.append("\t/**\n")
                    .append("\t* 批量更新\n")
                    .append("\t* @param param\n")
                    .append("\t* @return\n")
                    .append("\t*/\n");
            service.append("\tint batchUpdate(").append(params).append(");\n\n");


            service.append("\t/**\n")
                    .append("\t* 根据主键删除\n")
                    .append("\t* @param param\n")
                    .append("\t* @return\n")
                    .append("\t*/\n");
            service.append("\tint deleteByPrimaryKey(").append(params).append(");\n\n");
        }

        service.append("\t/**\n")
                .append("\t* 根据条件更新全量\n")
                .append("\t* @param param\n")
                .append("\t* @return\n")
                .append("\t*/\n");
        service.append("\tint updateBySelect(").append(param.getModelName()).append(" param);\n\n");

        service.append("\t/**\n")
                .append("\t* 根据条件更新不为空\n")
                .append("\t* @param param\n")
                .append("\t* @return\n")
                .append("\t*/\n");
        service.append("\tint updateSelectiveBySelct(").append(param.getModelName()).append(" param);\n\n");


        service.append("\t/**\n")
                .append("\t* 根据条件删除\n")
                .append("\t* @param param\n")
                .append("\t* @return\n")
                .append("\t*/\n");
        service.append("\tint deleteBySelect(").append(param.getModelName()).append(" param);\n\n}");
        try {
            FileUtil.write(file, service.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * service 的import
     *
     * @param service
     * @param param
     * @param packages
     */
    @Override
    public void imports(StringBuffer service, BaseParam param, String packages) {

        service.append("\n")
                .append("import ").append(packages).append(".model.").append(param.getModelName()).append(";\n")
                .append("import ").append(packages).append(".web.args.").append(param.getModelName()).append("Args;\n")
                .append("\n")
                .append("import com.github.pagehelper.PageInfo;\n")
                .append("import ").append(packages).append(".common.model.PageParam;\n")
                .append("import java.util.List;\n")
                .append("import java.util.Map;\n\n");
    }

    /**
     * service impl的import
     *
     * @param service
     * @param param
     * @param packages
     */
    @Override
    public void importImpls(StringBuffer service, BaseParam param, String packages) {

        service.append("\n")
                .append("import com.github.pagehelper.PageHelper;\n")
                .append("import com.github.pagehelper.PageInfo;\n")
                .append("import ").append(packages).append(".common.model.PageParam;\n")
                .append("import ").append(packages).append(".model.").append(param.getModelName()).append(";\n")
                .append("import ").append(packages).append(".web.args.").append(param.getModelName()).append("Args;\n")
                .append("import ").append(packages).append(".service.").append(param.getModelName()).append("Service;\n")
                .append("import ").append(packages).append(".dao.").append(param.getModelName()).append("Mapper;\n")
                .append("import ").append(packages).append(".utils.").append("TimeUtils;\n")
                .append("import com.alibaba.fastjson.JSON;\n")
                .append("import org.springframework.beans.BeanUtils;\n")
                .append("import org.springframework.stereotype.Service;\n")
                .append("import org.slf4j.Logger;\n")
                .append("import org.slf4j.LoggerFactory;\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import java.util.List;\n")
                .append("import java.util.Map;\n\n");
    }

    /**
     * 根据map获取查询Map列表
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     * @return
     */
    @Override
    public void selectListByMapReturnMap(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        service.append("\t/**\n")
                .append("\t* 查询返回key为数据库字段的map\n")
                .append("\t* @param param ：查询条件Map,key为数据库字段，value为值\n")
                .append("\t* @return ：List<Map<String,Object>>\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        service.append("\tList<Map<String,Object>> selectListByMapReturnMap(Map<String,Object> param);\n\n");


        serviceImpl.append("\t/**\n")
                .append("\t* 查询返回key为数据库字段的map\n")
                .append("\t* @param param ：查询条件Map,key为数据库字段，value为值\n")
                .append("\t* @return ：List<Map<String,Object>>\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");

        //生成头部
        serviceImpl.append("\t@Override\n");
        //生成方法体
        serviceImpl.append("\tpublic List<Map<String,Object>> selectListByMapReturnMap(Map<String,Object> param){\n\n");

        //生成查询数据库方法
        serviceImpl.append("\t\tList<Map<String,Object>> result=").append(ColumnsUtil.columns(param.getModelName(), "objName")).append("Mapper.selectListByMapReturnMap(param);\n");


        serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");

    }


    /**
     * 根据map获取查询列表
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void selectListByMap(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        service.append("\t/**\n")
                .append("\t* 根据map获取查询列表\n")
                .append("\t* @param param ：查询条件Map,key为java对应字段，value为值\n")
                .append("\t* @return ：List<").append(param.getModelName()).append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        service.append("\tList<").append(param.getModelName()).append("> selectListByMap(Map<String,Object> param);\n\n");


        //生成service，实现类
        serviceImpl.append("\t/**\n")
                .append("\t* 根据map获取查询列表\n")
                .append("\t* @param param ：查询条件Map,key为java对应字段，value为值\n")
                .append("\t* @return ：List<").append(param.getModelName()).append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        //生成头部
        serviceImpl.append("\t@Override\n");
        //生成方法体
        serviceImpl.append("\tpublic List<").append(param.getModelName()).append("> selectListByMap(Map<String,Object> param){\n\n");

        //生成查询数据库方法
        serviceImpl.append("\t\tList<").append(param.getModelName()).append("> result=").append(ColumnsUtil.columns(param.getModelName(), "objName")).append("Mapper.selectListByMap(param);\n");


        serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");

    }

    /**
     * 获取查询列表
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void selectList(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        service.append("\t/**\n")
                .append("\t* 获取查询列表\n")
                .append("\t* @param param\n")
                .append("\t* @return ：List<").append(param.getModelName()).append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        service.append("\tList<").append(param.getModelName()).append("> list(").append(param.getModelName()).append("Args param);\n\n");


        //生成service，实现类
        serviceImpl.append("\t/**\n")
                .append("\t* 获取查询列表\n")
                .append("\t* @param param\n")
                .append("\t* @return ：List<").append(param.getModelName()).append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        //生成头部
        serviceImpl.append("\t@Override\n");
        //生成方法体
        serviceImpl.append("\tpublic List<").append(param.getModelName()).append("> list(").append(param.getModelName()).append("Args param){\n\n");


        serviceImpl.append("\t\t//参数类型转化\n")
                .append("\t\t").append(param.getModelName())
                .append(" ").append(param.objName())
                .append(" = new ").append(param.getModelName()).append("();\n\n")
                .append("\t\tBeanUtils.copyProperties(param,").append(param.objName()).append(");\n\n");

        //生成查询数据库方法
        serviceImpl.append("\t\tList<").append(param.getModelName()).append("> result=").append(ColumnsUtil.columns(param.getModelName(), "objName")).append("Mapper.list(").append(param.objName()).append(");\n\n");


        serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
    }

    /**
     * 统计接口
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void count(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        service.append("\t/**\n")
                .append("\t* 统计接口\n\n")
                .append("\t* @param param\n")
                .append("\t* @return Integer\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        service.append("\tInteger count(").append(param.getModelName()).append("Args param);\n\n");


        //生成service，实现类
        serviceImpl.append("\t/**\n")
                .append("\t* 统计接口\n\n")
                .append("\t* @param param\n")
                .append("\t* @return Integer\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        //生成头部
        serviceImpl.append("\t@Override\n");
        //生成方法体
        serviceImpl.append("\tpublic Integer count(").append(param.getModelName()).append("Args param){\n\n");

        serviceImpl.append("\t\t//参数类型转化\n")
                .append("\t\t").append(param.getModelName())
                .append(" ").append(param.objName())
                .append(" = new ").append(param.getModelName()).append("();\n\n")
                .append("\t\tBeanUtils.copyProperties(param,").append(param.objName()).append(");\n\n");

        //生成查询数据库方法
        serviceImpl.append("\t\tInteger result=").append(ColumnsUtil.columns(param.getModelName(), "objName")).append("Mapper.count(").append(param.objName()).append(");\n\n");


        serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
    }

    /**
     * 根据主键查询数据
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void selectByPrimaryKey(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        if (param.getKeys().size() > 0) {
            //生成service方法
            service.append("\t/**\n")
                    .append("\t* 根据主键查询数据\n")
                    .append("\t*\n")
                    .append("\t* @param ").append(paramCommonService.keyMapperParam(param)).append("\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\t").append(param.getModelName()).append(" selectByPrimaryKey(")
                    .append(paramCommonService.keyParam(param))
                    .append(");\n\n");


            //生成service，实现类
            serviceImpl.append("\t/**\n")
                    .append("\t* 根据主键查询数据\n")
                    .append("\t*\n")
                    .append("\t* @param ").append(paramCommonService.keyMapperParam(param)).append("\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\t@Override\n")
                    .append("\tpublic ").append(param.getModelName()).append(" selectByPrimaryKey(")
                    .append(paramCommonService.keyParam(param))
                    .append("){\n\n");

            serviceImpl.append("\t\t").append(param.getModelName()).append(" result=").append(ColumnsUtil.columns(param.getModelName(), "objName"))
                    .append("Mapper.selectByPrimaryKey(")
                    .append(paramCommonService.keyMapperParam(param))
                    .append(");\n");

            serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
        }
    }

    /**
     * 分页查询
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void selectPageInfo(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        service.append("\t/**\n")
                .append("\t* 分页查询\n")
                .append("\t* @param param：查询参数\n")
                .append("\t* @param pageParam ::分页参数\n")
                .append("\t* @return ：PageInfo<").append(param.getModelName()).append(":分页结果集\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        service.append("\tPageInfo<").append(param.getModelName()).append("> pageInfo(").append(param.getModelName()).append("Args param,PageParam pageParam);\n\n");


        //生成service，实现类
        serviceImpl.append("\t/**\n")
                .append("\t* 分页查询\n")
                .append("\t* @param param：查询参数\n")
                .append("\t* @param pageParam ::分页参数\n")
                .append("\t* @return ：PageInfo<").append(param.getModelName()).append(":分页结果集\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        //生成头部
        serviceImpl.append("\t@Override\n");
        //生成方法体
        serviceImpl.append("\tpublic PageInfo<").append(param.getModelName()).append("> pageInfo(").append(param.getModelName()).append("Args param,PageParam pageParam){\n\n");

        serviceImpl.append("\t\t//参数类型转化\n")
                .append("\t\t").append(param.getModelName())
                .append(" ").append(param.objName())
                .append(" = new ").append(param.getModelName()).append("();\n")
                .append("\t\tBeanUtils.copyProperties(param,").append(param.objName()).append(");\n\n")
                .append("\t\tPageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());\n\n");


        //生成查询数据库方法
        serviceImpl.append("\t\tList<").append(param.getModelName()).append("> result=").append(ColumnsUtil.columns(param.getModelName(), "objName")).append("Mapper.list(").append(param.objName()).append(");\n");

        serviceImpl.append("\t\treturn new PageInfo(result);\n").append("\t}\n\n");
    }

    /**
     * 全量新增
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void insert(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        service.append("\t/**\n")
                .append("\t* 获取查询列表\n")
                .append("\t* @param param\n")
                .append("\t* @return ：int 数量\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        service.append("\tint insert(").append(param.getModelName()).append(" param);\n\n");


        //生成service，实现类
        serviceImpl.append("\t/**\n")
                .append("\t* 获取查询列表\n")
                .append("\t* @param param\n")
                .append("\t* @return ：int 数量\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        //生成头部
        serviceImpl.append("\t@Override\n");
        //生成方法体
        serviceImpl.append("\tpublic int insert(").append(param.getModelName()).append(" param){\n\n");

        //生成查询数据库方法
        serviceImpl.append("\t\tint result=").append(ColumnsUtil.columns(param.getModelName(), "objName")).append("Mapper.insert(param);\n");

        serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
    }

    /**
     * 不为空新增
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void insertSelective(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        service.append("\t/**\n")
                .append("\t* 不为空新增\n")
                .append("\t* @param param\n")
                .append("\t* @return ：int 数量\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        service.append("\tint insertSelective(").append(param.getModelName()).append("Args param);\n\n");


        //生成service，实现类
        serviceImpl.append("\t/**\n")
                .append("\t* 不为空新增\n")
                .append("\t* @param param\n")
                .append("\t* @return ：int 数量\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        //生成头部
        serviceImpl.append("\t@Override\n");
        //生成方法体
        serviceImpl.append("\tpublic int insertSelective(").append(param.getModelName()).append("Args param){\n\n");

        serviceImpl.append("\t\t//参数类型转化\n")
                .append("\t\t").append(param.getModelName())
                .append(" ").append(param.objName())
                .append(" = new ").append(param.getModelName()).append("();\n")
                .append("\t\tBeanUtils.copyProperties(param,").append(param.objName()).append(");\n\n");
        //生成查询数据库方法
        serviceImpl.append("\t\tint result=").append(ColumnsUtil.columns(param.getModelName(), "objName")).append("Mapper.insertSelective(").append(param.objName()).append(");\n\n");

        serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
    }

    /**
     * 批量新增
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void batchInsert(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        service.append("\t/**\n")
                .append("\t* 批量新增\n")
                .append("\t* @param param\n")
                .append("\t* @return ：int 数量\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        service.append("\tint batchInsert(List<").append(param.getModelName()).append("> param);\n\n");


        //生成service，实现类
        serviceImpl.append("\t/**\n")
                .append("\t* 批量新增\n")
                .append("\t* @param param\n")
                .append("\t* @return ：int 数量\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n");
        //生成头部
        serviceImpl.append("\t@Override\n");
        //生成方法体
        serviceImpl.append("\tpublic int batchInsert(List<").append(param.getModelName()).append("> param){\n\n");

        //生成查询数据库方法
        serviceImpl.append("\t\tint result=").append(ColumnsUtil.columns(param.getModelName(), "objName")).append("Mapper.batchInsert(param);\n");

        serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
    }

    /**
     * 根据主键更新全量
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void updateByPrimaryKey(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        if (null != param.getKeys() && param.getKeys().size() > 0) {
            //生成service方法
            service.append("\t/**\n")
                    .append("\t* 根据主键更新全量\n")
                    .append("\t* @param param\n")
                    .append("\t* @return ：int 数量\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\tint updateByPrimaryKey(")
                    .append(param.getModelName())
                    .append(" param);\n\n");


            //生成service，实现类
            serviceImpl.append("\t/**\n")
                    .append("\t* 根据主键更新全量\n")
                    .append("\t* @param param\n")
                    .append("\t* @return ：int 数量\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\t@Override\n")
                    .append("\tpublic int updateByPrimaryKey(")
                    .append(param.getModelName())
                    .append(" param){\n\n");

            //纪录开始时间,作为计算方法用时
            serviceImpl.append("\t\tint result=")
                    .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                    .append("Mapper.updateByPrimaryKey(")
                    .append("param);\n");

            serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
        }
    }

    /**
     * 根据主键更新不为空
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void updateSelectiveByPrimaryKey(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        if (null != param.getKeys() && param.getKeys().size() > 0) {
            //生成service方法
            service.append("\t/**\n")
                    .append("\t* 根据主键更新全量\n")
                    .append("\t* @param param\n")
                    .append("\t* @return ：int 数量\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\tint updateSelectiveByPrimaryKey(")
                    .append(param.getModelName())
                    .append("Args param);\n\n");

            //生成service，实现类
            serviceImpl.append("\t/**\n")
                    .append("\t* 根据主键更新全量\n\n")
                    .append("\t* @param param\n")
                    .append("\t* @return ：int 数量\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\t@Override\n")
                    .append("\tpublic int updateSelectiveByPrimaryKey(")
                    .append(param.getModelName())
                    .append("Args param){\n\n");

            serviceImpl.append("\t\t//参数类型转化\n")
                    .append("\t\t").append(param.getModelName())
                    .append(" ").append(param.objName())
                    .append(" = new ").append(param.getModelName()).append("();\n")
                    .append("\t\tBeanUtils.copyProperties(param,").append(param.objName()).append(");\n\n")
                    .append("\t\tint result=")
                    .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                    .append("Mapper.updateSelectiveByPrimaryKey(")
                    .append(" ").append(param.objName()).append(");\n\n");

            serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
        }
    }

    /**
     * 存在即更新，不存在就插入
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void batchInsertUpdate(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        if (param.getKeys().size() > 0) {
            //生成service方法
            service.append("\t/**\n")
                    .append("\t* 存在即更新，不存在就插入\n")
                    .append("\t* @param param\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\tint batchInsertUpdate(")
                    .append("List<")
                    .append(param.getModelName())
                    .append("> ").append("param").append(");\n\n");


            //生成service，实现类
            serviceImpl.append("\t/**\n")
                    .append("\t* 存在即更新，不存在就插入\n")
                    .append("\t* @param param\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\t@Override\n")
                    .append("\tpublic int batchInsertUpdate(List<")
                    .append(param.getModelName())
                    .append("> param){\n\n");

            serviceImpl.append("\t\tint result=").append(ColumnsUtil.columns(param.getModelName(), "objName"))
                    .append("Mapper.batchInsertUpdate(")
                    .append("param);\n");

            serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
        }
    }

    /**
     * 批量更新
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void batchUpdate(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {
        //生成service方法
        if (param.getKeys().size() > 0) {
            //生成service方法
            service.append("\t/**\n")
                    .append("\t* 批量更新\n")
                    .append("\t* @param param\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\tint batchUpdate(")
                    .append("List<")
                    .append(param.getModelName())
                    .append("> ").append("param").append(");\n\n");


            //生成service，实现类
            serviceImpl.append("\t/**\n")
                    .append("\t* 批量更新入\n")
                    .append("\t* @param param\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\t@Override\n")
                    .append("\tpublic int batchUpdate(List<")
                    .append(param.getModelName())
                    .append("> param){\n\n");

            //纪录开始时间,作为计算方法用时
            serviceImpl.append("\t\tint result=").append(ColumnsUtil.columns(param.getModelName(), "objName"))
                    .append("Mapper.batchUpdate(")
                    .append("param);\n");

            serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
        }
    }

    /**
     * 根据主键删除数据
     *
     * @param service
     * @param serviceImpl
     * @param param
     * @param packages
     */
    @Override
    public void deleteByPrimaryKey(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        if (param.getKeys().size() > 0) {
            //生成service方法
            service.append("\t/**\n")
                    .append("\t* 根据主键删除数据\n")
                    .append("\t* @param ").append(paramCommonService.keyMapperParam(param)).append("\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\tint deleteByPrimaryKey(")
                    .append(paramCommonService.keyParam(param))
                    .append(");\n\n");


            //生成service，实现类
            serviceImpl.append("\t/**\n")
                    .append("\t* 根据主键删除数据\n")
                    .append("\t* @param ").append(paramCommonService.keyMapperParam(param)).append("\n")
                    .append("\t* @author ").append(param.getAuthor()).append("\n")
                    .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                    .append("\t*/\n")
                    .append("\t@Override\n")
                    .append("\tpublic int deleteByPrimaryKey(")
                    .append(paramCommonService.keyParam(param))
                    .append("){\n\n");

            //纪录开始时间,作为计算方法用时
            serviceImpl.append("\t\tint result=").append(ColumnsUtil.columns(param.getModelName(), "objName"))
                    .append("Mapper.deleteByPrimaryKey(")
                    .append(paramCommonService.keyMapperParam(param))
                    .append(");\n");

            serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
        }
    }

    @Override
    public void deleteBySelect(StringBuffer service, StringBuffer serviceImpl, BaseParam param, String packages) {

        //生成service方法
        service.append("\t/**\n")
                .append("\t* 根据查询条件删除数据\n")
                .append("\t* @param param").append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\tint deleteBySelect(")
                .append(param.getModelName())
                .append(" param);\n\n");


        //生成service，实现类
        serviceImpl.append("\t/**\n")
                .append("\t* 根据查询删除数据\n")
                .append("\t* @param param").append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@Override\n")
                .append("\tpublic int deleteBySelect(")
                .append(param.getModelName())
                .append(" param){\n\n");

        serviceImpl.append("\t\tint result=").append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Mapper.deleteBySelect(param);\n");

        serviceImpl.append("\t\treturn result;\n").append("\t}\n\n");
    }


}
