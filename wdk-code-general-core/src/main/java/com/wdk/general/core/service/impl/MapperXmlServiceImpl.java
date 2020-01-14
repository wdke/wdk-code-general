package com.wdk.general.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.model.SelectAuto;
import com.wdk.general.core.service.MapperXmlService;
import com.wdk.general.core.utils.*;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * @author wdke
 * @date 2019/5/8
 */
@Service
public class MapperXmlServiceImpl implements MapperXmlService {

    private static Logger logger = LoggerFactory.getLogger(MapperXmlServiceImpl.class);


    /**
     * 生成xml文件
     *
     * @param param
     * @param packages
     */
    @Override
    public void mapperXml(BaseParam param, String packages) {

        logger.info("mapperXml->start ::param->[param1->{},param2->{}]", JSON.toJSONString(param), packages);

        String fileName = UserContext.current().getProjectServerRoot() + "/src/main/resources/mapper/" + param.getModelName() + "Mapper.xml";

        Document document = DocumentHelper.createDocument();//创建xml文档
        document.addDocType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
        document.addComment(param.getTableComment() == null ? "" : param.getTableComment());
        Element root = document.addElement("mapper")
                .addAttribute("namespace", packages + ".storage.dao." + param.getModelName() + "Mapper");//创建根元素

        /**
         * 开始生成xml内容
         */
        baseResultMap(param, packages + ".storage.entity", root);
        BaseTablesSql(param, root);
        BaseColumnsSql(param, root);
        BaseWhereSql(param, root);
        BaseWhereDbSql(param, root);
        selectListByMapReturnMap(param, "java.util.Map", root);
        selectListByMap(param, "java.util.Map", root);
        selectList(param, packages + ".storage.entity", root);
        count(param, packages + ".storage.entity", root);
        insert(param, packages + ".storage.entity", root);
        insertSelective(param, packages + ".storage.entity", root);
        batchInsert(param, packages + ".storage.entity", root);
        if (!param.getKeys().isEmpty()) {
            selectByPrimaryKey(param, packages + ".storage.entity", root);
            updateByPrimaryKey(param, packages + ".storage.entity", root);
            updateSelectiveByPrimaryKey(param, packages + ".storage.entity", root);
            batchInsertUpdate(param, "java.util.Map", root);
            batchUpdate(param, "java.util.Map", root);
            deleteByPrimaryKey(param, "java.util.Map", root);
        }
        deleteBySelect(param, packages + ".storage.entity", root);
        /**
         * 结束生成xml内容
         */
        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1  
        outputFormat.setEncoding("UTF-8");
        //是否生产xml头
//        outputFormat.setSuppressDeclaration(true);
        //第二行不空行
//        outputFormat.setNewLineAfterDeclaration(false);
        //设置是否缩进
        outputFormat.setIndent(true);
        //以四个空格方式实现缩进
        outputFormat.setIndent("\t");
        //设置是否换行
        outputFormat.setNewlines(true);
//        outputFormat.setNewLineAfterDeclaration(true);
//        outputFormat.setNewLineAfterNTags(1);
        //设置文本换行
        outputFormat.setTrimText(false);
        // stringWriter字符串是用来保存XML文档的  
//        StringWriter stringWriter = new StringWriter();
        try {
            // xmlWriter是用来把XML文档写入字符串的(工具)  
//            String fileName="/Users/ounoboruka/person/java-code/parents_project/code-general-core/xml/"+param.getModelName()+"Mapper.xml";
            FileUtil.creatFile(fileName);
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), outputFormat);
            xmlWriter.setEscapeText(true);
            document.setName("测试数据");
            // 把创建好的XML文档写入字符串  
            xmlWriter.write(document);
            // 打印字符串,即是XML文档

            xmlWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("mapperXml->end ::result->[result->{}]");

    }

    /**
     * 生成id为baseResultMap的result
     *
     * @param param
     * @param packages
     * @return
     */
    @Override
    public String baseResultMap(BaseParam param, String packages, Element root) {

        root.addComment("返回值映射");
        Element resultMap = root.addElement("resultMap")
                .addAttribute("id", "BaseResultMap")
                .addAttribute("type", packages + "." + param.getModelName());
        MapperXmlUtils.columsElement(param, resultMap, null, "result");
        return resultMap.asXML();
    }

    /**
     * 查询字段生成
     *
     * @param param
     * @return
     */
    @Override
    public String BaseColumnsSql(BaseParam param, Element root) {

        root.addComment("查询字段开始");
        Element sqls = root.addElement("sql").addAttribute("id", "Base_Column_Sql");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 0, "\n\t\t");
//        String colimns=TableColumnsHandlerUtil.typeColumns(param.getColumns(),1,"\t\t\t",3);
        sqls.addText("\n\t\t" + colimns + "\n\t");

        root.addComment("查询字段结束");
        return sqls.asXML();
    }

    /**
     * 生成where查询条件
     *
     * @param param
     * @param root
     * @return
     */
    @Override
    public String BaseWhereSql(BaseParam param, Element root) {

        root.addComment("查询条件开始");
        Element sqls = root.addElement("sql").addAttribute("id", "Base_Where_Sql");
        MapperXmlUtils.columsElement(param, sqls, "\n\t\t", "ifAnd");

        root.addComment("查询条件结束");
        return sqls.asXML();
    }

    /**
     * 生成数据库字段where查询条件
     *
     * @param param
     * @param root
     * @return
     */
    @Override
    public String BaseWhereDbSql(BaseParam param, Element root) {

        root.addComment("查询条件开始");
        Element sqls = root.addElement("sql").addAttribute("id", "Base_Where_Db_Sql");
        MapperXmlUtils.columsElement(param, sqls, "\n\t\t", "ifAndDb");

        root.addComment("查询条件结束");
        return sqls.asXML();
    }

    /**
     * 生成 tables sql
     *
     * @param param
     * @param root
     * @return
     */
    @Override
    public String BaseTablesSql(BaseParam param, Element root) {

        root.addComment("查询数据库表开始");
        Element sqls = root.addElement("sql")
                .addAttribute("id", "Base_Tables_Sql")
                .addText("\n\t\t" + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName() + "\n\t");

        root.addComment("查询数据库表结束");
        return sqls.asXML();
    }

    /**
     * 根据map获取查询Map列表
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    @Override
    public String selectListByMapReturnMap(BaseParam param, String packages, Element root) {

        root.addComment("根据条件查询列表开始");
        Element select = root.addElement("select")
                .addAttribute("id", "selectListByMapReturnMap")
                .addAttribute("parameterType", "java.util.Map")
                .addAttribute("resultType", "java.util.Map")
                .addText("\n\t\tselect");
        select.addElement("include")
                .addAttribute("refid", "Base_Column_Sql");
        select.addText("\n\t\tfrom ");
        select.addElement("include").addAttribute("refid", "Base_Tables_Sql");
        Element where = select.addElement("where");
        where.addElement("include")
                .addAttribute("refid", "Base_Where_Db_Sql");


        root.addComment("根据条件查询列表结束");
        return select.asXML();
    }

    /**
     * 根据map获取查询列表
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    @Override
    public String selectListByMap(BaseParam param, String packages, Element root) {


        root.addComment("根据条件查询列表开始");
        Element select = root.addElement("select")
                .addAttribute("id", "selectListByMap")
                .addAttribute("parameterType", "java.util.Map")
                .addAttribute("resultMap", "BaseResultMap")
                .addText("\n\t\tselect");
        select.addElement("include")
                .addAttribute("refid", "Base_Column_Sql");
        select.addText("\n\t\tfrom ");
        select.addElement("include").addAttribute("refid", "Base_Tables_Sql");
        Element where = select.addElement("where");
        where.addElement("include")
                .addAttribute("refid", "Base_Where_Sql");


        root.addComment("根据条件查询列表结束");
        return select.asXML();
    }

    /**
     * 获取查询列表
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    @Override
    public String selectList(BaseParam param, String packages, Element root) {

        root.addComment("根据条件查询列表开始");
        Element select = root.addElement("select")
                .addAttribute("id", "list")
                .addAttribute("parameterType", packages + "." + param.getModelName())
                .addAttribute("resultMap", "BaseResultMap")
                .addText("\n\t\tselect");
        select.addElement("include")
                .addAttribute("refid", "Base_Column_Sql");
        select.addText("\n\t\tfrom ");
        select.addElement("include").addAttribute("refid", "Base_Tables_Sql");
        Element where = select.addElement("where");
        where.addElement("include")
                .addAttribute("refid", "Base_Where_Sql");

        //生成排序
        select.addElement("if").addAttribute("test", "orderBy != null").addText("order by ${orderBy}\n");


        root.addComment("根据条件查询列表结束");
        return select.asXML();
    }

    /**
     * 统计接口
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    @Override
    public String count(BaseParam param, String packages, Element root) {

        root.addComment("根据条件查询列表开始");
        Element select = root.addElement("select")
                .addAttribute("id", "count")
                .addAttribute("parameterType", packages + "." + param.getModelName())
                .addAttribute("resultType", "java.lang.Integer")
                .addText("\n\t\tselect count(*)\n");
        select.addText("\n\t\tfrom ");
        select.addElement("include").addAttribute("refid", "Base_Tables_Sql");
        Element where = select.addElement("where");
        where.addElement("include")
                .addAttribute("refid", "Base_Where_Sql");


        root.addComment("根据条件查询列表结束");
        return select.asXML();
    }

    /**
     * 根据主键查询一个数据
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    @Override
    public String selectByPrimaryKey(BaseParam param, String packages, Element root) {

        root.addComment("根据条件查询列表开始");
        Element select = root.addElement("select")
                .addAttribute("id", "selectByPrimaryKey")
                .addAttribute("parameterType", packages + "." + param.getModelName())
                .addAttribute("resultMap", "BaseResultMap")
                .addText("\n\t\tselect");
        select.addElement("include")
                .addAttribute("refid", "Base_Column_Sql");
        select.addText("\n\t\tfrom ");
        select.addElement("include").addAttribute("refid", "Base_Tables_Sql");
        String wheres = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 5, "\t\t\t", 5);
        select.addElement("where").addText(wheres);
        root.addComment("根据条件查询列表结束");
        return select.asXML();
    }

    /**
     * 全量新增
     *
     * @param param
     * @param packages
     * @return
     */
    @Override
    public String insert(BaseParam param, String packages, Element root) {

        root.addComment("新增表数据开始");
        Element inserts = root.addElement("insert")
                .addAttribute("id", "insert")
                .addAttribute("parameterType", packages + "." + param.getModelName());
        if (null != param.getKeys()
                && param.getKeys().size() == 1
                && "auto_increment".equals(param.getKeys().get(0).getExtra())) {

            SchemaColumns obj = param.getKeys().get(0);

            String column = obj.getModelObjName();
            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
            inserts.addElement("selectKey")
                    .addAttribute("keyProperty", column)
                    .addAttribute("order", "AFTER")
                    .addAttribute("resultType", dataType.getJavaPackages())
                    .addText("SELECT LAST_INSERT_ID()");
        }
        inserts.addText("\n\t\tinsert into " + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName() + " (");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 1, "\t\t\t", 3);
        inserts.addText("\n\t\t\t" + colimns);
        inserts.addText("\n\t\t)");
        inserts.addText("\n\t\tvalues (");
        String values = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 3, "\t\t\t", 3);
        inserts.addText("\n\t\t\t" + values);
        inserts.addText("\n\t\t)\n\t");

        root.addComment("新增表数据结束");
        return inserts.asXML();
    }

    /**
     * 部分新增
     *
     * @param param
     * @param packages
     * @return
     */
    @Override
    public String insertSelective(BaseParam param, String packages, Element root) {

        root.addComment("新增表不为空数据开始");
        Element inserts = root.addElement("insert")
                .addAttribute("id", "insertSelective")
                .addAttribute("parameterType", packages + "." + param.getModelName());

        if (null != param.getKeys()
                && param.getKeys().size() == 1
                && "auto_increment".equals(param.getKeys().get(0).getExtra())) {
            SchemaColumns obj = param.getKeys().get(0);
            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
            String column = obj.getModelObjName();
            //返回主键
            inserts.addElement("selectKey")
                    .addAttribute("keyProperty", column)
                    .addAttribute("order", "AFTER")
                    .addAttribute("resultType", dataType.getJavaPackages())
                    .addText("SELECT LAST_INSERT_ID()");
        }
        inserts.addText("\n\t\tinsert into " + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName() + " (");
        MapperXmlUtils.columsElement(param, inserts, "\n\t\t", "colimnsIf");
        inserts.addText("\n\t\t)\n");
        inserts.addText("\t\tvalues (");

        MapperXmlUtils.columsElement(param, inserts, "\n\t\t", "valuesIf");

        inserts.addText("\n\t\t)\n\t");
        root.addComment("新增表不为空数据结束");
        return inserts.asXML();
    }

    /**
     * 批量新增
     *
     * @param param
     * @param packages
     * @return
     */
    @Override
    public String batchInsert(BaseParam param, String packages, Element root) {

        root.addComment("批量新增表数据开始");
        Element inserts = root.addElement("insert")
                .addAttribute("id", "batchInsert")
                .addAttribute("parameterType", "java.util.List");

        if (null != param.getKeys()
                && param.getKeys().size() == 1
                && "auto_increment".equals(param.getKeys().get(0).getExtra())) {
            SchemaColumns obj = param.getKeys().get(0);
            String column = obj.getModelObjName();
            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
            //返回主键
            inserts.addElement("selectKey")
                    .addAttribute("keyProperty", column)
                    .addAttribute("order", "AFTER")
                    .addAttribute("resultType", dataType.getJavaPackages())
                    .addText("SELECT LAST_INSERT_ID()");
        }
        Element ifs = inserts.addElement("if")
                .addAttribute("test", "list != null and list.size != 0");
        ifs.addText("\n\t\t\tinsert into " + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName() + " (\n");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 1, "\t\t\t\t", 3);
        ifs.addText("\t\t\t\t" + colimns + "\n");
        ifs.addText("\t\t\t)\n");
        ifs.addText("\t\t\tvalues");
        String values = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 4, "\t\t\t\t", 3);
        ifs.addElement("foreach")
                .addAttribute("collection", "list")
                .addAttribute("item", "item")
                .addAttribute("index", "index")
                .addAttribute("separator", ",")
                .addText("\n\t\t\t\t(" + values + "\n\t\t\t\t)\n\t\t\t");

        root.addComment("批量新增表数据结束");
        return inserts.asXML();
    }

    /**
     * 更新全量
     *
     * @param param
     * @param packages
     * @return
     */
    @Override
    public String updateByPrimaryKey(BaseParam param, String packages, Element root) {

        if (param.getKeys().isEmpty()) {
            return null;
        }
        root.addComment("根据主键更新数据开始");
        Element updates = root.addElement("update")
                .addAttribute("id", "updateByPrimaryKey")
                .addAttribute("parameterType", packages + "." + param.getModelName())
                .addText("\n\t\tupdate " + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName())
                .addText("\n\t\tset  \n");

        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 6, "\t\t\t", 6);
        updates.addText(colimns);
        String wheres = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 5, "\t\t\t", 5);
        updates.addElement("where").addText(wheres);

        root.addComment("根据主键更新数据结束");
        return updates.asXML();
    }


    /**
     * 更新不为空
     *
     * @param param
     * @param packages
     * @return
     */
    @Override
    public String updateSelectiveByPrimaryKey(BaseParam param, String packages, Element root) {

        if (param.getKeys().isEmpty()) {
            return null;
        }
        root.addComment("根据主键更新不为空数据开始");
        Element update = root.addComment(param.getTableComment() + "更新不为空").addElement("update")
                .addAttribute("id", "updateSelectiveByPrimaryKey")
                .addAttribute("parameterType", packages + "." + param.getModelName())
//                .addComment("")
                .addText("\n\t\t")
                .addText("update " + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName());
        Element set = update.addElement("set");
        MapperXmlUtils.columsElement(param, set, "\n\t\t\t", "if");

        String wheres = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 5, "", 5);
        update.addComment("主键查询").addElement("where").addText(wheres);

        root.addComment("根据主键更新不为空数据结束");
        return update.asXML();
    }

    /**
     * 批量更新，存在就插入，不存在就更新
     *
     * @param param
     * @param packages
     * @return
     */
    @Override
    public String batchInsertUpdate(BaseParam param, String packages, Element root) {

        root.addComment("根据主键存在就更新，不存在就新增开始");
        Element insert = root.addComment(param.getTableComment() + "批量新增，存在就更新，不存在就插入")
                .addElement("insert")
                .addAttribute("id", "batchInsertUpdate")
                .addAttribute("parameterType", "java.util.List");
        if (null != param.getKeys()
                && param.getKeys().size() == 1
                && "auto_increment".equals(param.getKeys().get(0).getExtra())) {
            SchemaColumns obj = param.getKeys().get(0);
            String column = obj.getModelObjName();
            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
            //返回主键
            insert.addElement("selectKey")
                    .addAttribute("keyProperty", column)
                    .addAttribute("order", "AFTER")
                    .addAttribute("resultType", dataType.getJavaPackages()).addText("SELECT LAST_INSERT_ID()");
        }

        Element ifs = insert.addElement("if").addAttribute("test", "list !=null and list.size != 0");

        ifs.addText("\n\t\t\t")
                .addText("insert into  " + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName() + "(\n");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 1, "\t\t\t\t", 3);
        ifs.addText("\t\t\t\t" + colimns + "\n");
        ifs.addText("\t\t\t)\n");
        ifs.addText("\t\t\tvalues \n");
        String values = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 4, "\t\t\t\t", 3);
        ifs.addElement("foreach")
                .addAttribute("collection", "list")
                .addAttribute("item", "item")
                .addAttribute("index", "index")
                .addAttribute("separator", ",")
                .addText("\n\t\t\t\t(")
                .addText(values)
                .addText(")\n\t\t\t");
        ifs.addText("\n\t\t\t\tON DUPLICATE KEY UPDATE\n");
        String updates = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 6, "\t\t\t\t", 6);
        ifs.addText(updates);
        root.addComment("根据主键存在就更新，不存在就新增结束");
        return insert.asXML();
    }

    /**
     * 批量更新
     *
     * @param param
     * @param packages
     * @return
     */
    @Override
    public String batchUpdate(BaseParam param, String packages, Element root) {

        root.addComment("批量更新开始");
        Element update = root.addElement("update")
                .addAttribute("id", "batchUpdate");
        Element ifs = update.addElement("if").addAttribute("test", "list != null and list.size != 0");
        Element tirms = ifs.addText("\n\t\t\tupdate " + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName())
                .addElement("trim")
                .addAttribute("prefix", "set")
                .addAttribute("suffixOverrides", ",");
        MapperXmlUtils.columsElement(param, tirms, "\n\t\t\t\t", "trim");
        Element foreachs = ifs.addElement("where")
                .addElement("foreach")
                .addAttribute("collection", "list")
                .addAttribute("separator", "or")
                .addAttribute("item", "item")
                .addAttribute("index", "index");
        if (param.getKeys().size() > 1) {
            for (int i = 0; i < param.getColumns().size(); i++) {
                SchemaColumns obj = param.getColumns().get(i);
                String column = obj.getModelObjName();
                JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                if (i == 0) {
                    foreachs.addText("\n\t\t\t\t\t(" + obj.getColumnName() + "=#{item." + column + ",jdbcType=" + dataType.getMybatisType() + "}");
                } else {

                    foreachs.addText("\n\t\t\t\t\tand " + obj.getColumnName() + "=#{item." + column + ",jdbcType=" + dataType.getMybatisType() + "}");
                }
            }
            foreachs.addText(")\n\t\t\t\t");
        }
        if (param.getKeys().size() == 1) {
            param.getKeys().forEach(obj -> {
                String column = obj.getModelObjName();
                JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                foreachs.addText("\n\t\t\t\t\t" + obj.getColumnName() + "=#{item." + column + ",jdbcType=" + dataType.getMybatisType() + "}");
            });
            foreachs.addText("\n\t\t\t\t");
        }
        root.addComment("批量更新结束");
        return update.asXML();
    }

    /**
     * 删除方法
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    @Override
    public String deleteByPrimaryKey(BaseParam param, String packages, Element root) {

        if (param.getKeys().isEmpty()) {
            return null;
        }

        root.addComment("删除表数据开始");
        Element delete = root.addElement("delete")
                .addAttribute("id", "deleteByPrimaryKey")
                .addAttribute("parameterType", packages)
                .addText("\n\t\tdelete from " + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName());

        String wheres = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 5, "", 5);
        delete.addComment("主键查询").addElement("where").addText(wheres);


        root.addComment("删除表数据结束");
        return null;
    }

    /**
     * 根据查询条件删除
     *
     * @param param
     * @param packages
     * @param root
     * @return
     */
    @Override
    public String deleteBySelect(BaseParam param, String packages, Element root) {

        root.addComment("删除表数据开始");
        Element delete = root.addElement("delete")
                .addAttribute("id", "deleteBySelect")
                .addAttribute("parameterType", packages + "." + param.getModelName())
                .addText("\n\t\tdelete from " + (param.getDbTables() ? param.getTableSchema() + "." : "") + param.getTableName());
        Element where = delete.addElement("where");
        where.addElement("include")
                .addAttribute("refid", "Base_Where_Sql");


        root.addComment("删除表数据结束");
        return null;
    }

    @Override
    public String selectByEntity(BaseParam param, String packages) {

        Document document = DocumentHelper.createDocument();//创建xml文档
        Element root = document.addElement("mapper")
                .addAttribute("namespace", packages + "." + param.getModelName() + "Mapper");//创建根元素


        SelectAuto selectAuto = new SelectAuto();
        selectAuto.setId("selectAll");
        Element selects = root.addElement("select");
        XmlUtil.getDocument(selects, selectAuto);

        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1  
        outputFormat.setEncoding("UTF-8");
        //是否生产xml头
//        outputFormat.setSuppressDeclaration(true);
        //第二行不空行
        outputFormat.setNewLineAfterDeclaration(false);
        //设置是否缩进
        outputFormat.setIndent(true);
        //以四个空格方式实现缩进
        outputFormat.setIndent("\t");
        //设置是否换行
        outputFormat.setNewlines(true);
//        outputFormat.setNewLineAfterDeclaration(true);
//        outputFormat.setNewLineAfterNTags(1);
        //设置文本换行
        outputFormat.setTrimText(false);
        // stringWriter字符串是用来保存XML文档的  
        StringWriter stringWriter = new StringWriter();
        try {
            // xmlWriter是用来把XML文档写入字符串的(工具)  
            String fileName = "/Users/ounoboruka/person/java-code/parents_project/code-general-core/xml/test.xml";
            FileUtil.creatFile(fileName);
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), outputFormat);
            xmlWriter.setEscapeText(true);
            // 把创建好的XML文档写入字符串  
            xmlWriter.write(document);
            // 打印字符串,即是XML文档
            System.out.println("**************");
            System.out.println(stringWriter.toString());
            System.out.println("**************");

            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        root.addEntity()
        return null;
    }
}
