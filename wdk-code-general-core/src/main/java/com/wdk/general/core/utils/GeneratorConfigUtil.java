package com.wdk.general.core.utils;

import com.wdk.general.core.common.model.UserContext;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.model.ProjectMetadata;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.model.Tables;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by wdk on 2019/12/30
 */
public class GeneratorConfigUtil {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorConfigUtil.class);

    public static void init(List<Tables> tables, List<SchemaColumns> columns) {


        String fileName = UserContext.current().getProjectServerRoot() + "/src/main/resources/generator/generatorConfig.xml";


        Document document = DocumentHelper.createDocument();//创建xml文档
        document.addDocType("generatorConfiguration", "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN", "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd");
        Element context = document.addElement("generatorConfiguration").addElement("context")
                .addAttribute("id", "DB2Tables").addAttribute("targetRuntime", "MyBatis3");//创建根元素

        //生成插件
        plugins(context);

        //注解配置
        commentGenerator(context);

        //数据库
        jdbcConnection(context);

        javaTypeResolver(context);

        //生成模型的包名和位置
        javaModelGenerator(context);

        //生成映射文件的包名和位置
        sqlMapGenerator(context);

        //生成DAO的包名和位置
        javaClientGenerator(context);

        context.addComment("要生成的表 tableName是数据库中的表名或视图名 domainObjectName是实体类名");

        Map<String, List<SchemaColumns>> collect = columns.stream().filter(obj -> null != obj.getExtra() && "auto_increment".equals(obj.getExtra())).collect(Collectors.groupingBy(SchemaColumns::getTableName));
        if (null != tables && tables.size() > 0) {

            for (Tables tab : tables) {

                logger.info("init tableName={}", tab.getTableName());
                //生成相关表信息
                context.addComment(tab.getTableComment());
                if (null == collect.get(tab.getTableName()) || collect.get(tab.getTableName()).size() > 1) {
                    table(context, tab.getTableName(), tab.getModelName(), false, null);
                } else {
                    table(context, tab.getTableName(), tab.getModelName(), true, collect.get(tab.getTableName()).get(0).getColumnName());
                }
            }
        }


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

    }

    /**
     * 生成所需插件
     *
     * @param context
     */
    public static void plugins(Element context) {
        plugin(context, "org.mybatis.generator.plugins.SerializablePlugin");
        plugin(context, "org.mybatis.generator.plugins.RowBoundsPlugin");
        plugin(context, "com.github.oceanc.mybatis3.generator.plugin.SliceTablePlugin");
        plugin(context, "com.github.oceanc.mybatis3.generator.plugin.SumSelectivePlugin");
        plugin(context, "com.github.oceanc.mybatis3.generator.plugin.UpdateSqlTextOfUpdateSelectivePlugin");
        plugin(context, "com.github.oceanc.mybatis3.generator.plugin.WhereSqlTextPlugin");
        plugin(context, "com.github.oceanc.mybatis3.generator.plugin.OptimisticLockAutoIncreasePlugin");
        plugin(context, "com.github.oceanc.mybatis3.generator.plugin.BatchInsertPlugin");
        plugin(context, "com.github.oceanc.mybatis3.generator.plugin.PaginationPlugin");
    }


    /**
     * 每一个插件配置
     *
     * @param context
     * @param type
     */
    public static void plugin(Element context, String type) {

        context.addElement("plugin").addAttribute("type", type);

    }


    /**
     * 通用配置
     *
     * @param context
     */
    public static void commentGenerator(Element context) {

        Element commentGenerator = context.addElement("commentGenerator");
        commentGenerator.addElement("property")
                .addAttribute("name", "suppressDate")
                .addAttribute("value", "true");

        commentGenerator.addElement("property")
                .addAttribute("name", "suppressAllComments")
                .addAttribute("value", "true");
    }


    /**
     * 数据库信息
     *
     * @param context
     */
    public static void jdbcConnection(Element context) {
        DbMessage dbMessage = UserContext.current().getDbMessage();
        context.addElement("jdbcConnection")
                .addAttribute("driverClass", "com.mysql.jdbc.Driver")
                .addAttribute("connectionURL", "jdbc:mysql://" + dbMessage.getHost() + ":" + dbMessage.getDbPort() + "/" + dbMessage.getDbName() + "?useUnicode=true")
                .addAttribute("userId", dbMessage.getDbUsername())
                .addAttribute("password", dbMessage.getDbPassword());

    }


    public static void javaTypeResolver(Element context) {
        context.addElement("javaTypeResolver")
                .addElement("property")
                .addAttribute("name", "forceBigDecimals")
                .addAttribute("value", "false");
    }

    public static void javaModelGenerator(Element context) {

        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        context.addComment("生成模型的包名和位置");
        Element javaModelGenerator = context.addElement("javaModelGenerator")
                .addAttribute("targetProject", "src/main/java")
                .addAttribute("targetPackage", projectMetadata.getPackages() + ".storage.entity");

        javaModelGenerator.addElement("property")
                .addAttribute("name", "enableSubPackages")
                .addAttribute("value", "true");
        javaModelGenerator.addElement("property")
                .addAttribute("name", "trimStrings")
                .addAttribute("value", "true");
    }

    public static void sqlMapGenerator(Element context) {

        context.addComment("生成映射文件的包名和位置");
        context.addElement("sqlMapGenerator")
                .addAttribute("targetProject", "src/main/resources")
                .addAttribute("targetPackage", "mapper")
                .addElement("property")
                .addAttribute("name", "enableSubPackages")
                .addAttribute("value", "true");
    }

    public static void javaClientGenerator(Element context) {
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();

        context.addComment("生成DAO的包名和位置");
        context.addElement("javaClientGenerator")
                .addAttribute("type", "XMLMAPPER")
                .addAttribute("targetProject", "src/main/java")
                .addAttribute("targetPackage", projectMetadata.getPackages() + ".storage.dao")
                .addElement("property")
                .addAttribute("name", "enableSbuPackages")
                .addAttribute("value", "true");
    }

    /**
     * 表信息
     *
     * @param context
     * @param tableName
     * @param domainObjectName
     */
    public static void table(Element context, String tableName, String domainObjectName) {

        Element table = context.addElement("table");
        table.addAttribute("tableName", tableName)
                .addAttribute("domainObjectName", domainObjectName);
    }

    /**
     * 表信息
     *
     * @param context
     * @param tableName
     * @param domainObjectName
     * @param generatedKey
     */
    public static void table(Element context, String tableName, String domainObjectName, Boolean generatedKey, String column) {

        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        Element table = context.addElement("table");
        table.addAttribute("tableName", tableName)
                .addAttribute("domainObjectName", domainObjectName);
        table.addElement("property")
                .addAttribute("name", "useActualColumnNames").addAttribute("value", projectMetadata.getUseActualColumnNames() + "");
        if (generatedKey && !StringUtils.isEmpty(column)) {
            table.addElement("generatedKey").addAttribute("column", column).addAttribute("sqlStatement", "MySql").addAttribute("identity", "true");
        }
    }
}
