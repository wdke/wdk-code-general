package com.wdk.general.core.utils;

import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import org.dom4j.Element;

/**
 * @author wdke
 * @date 2019/5/14
 */
public class MapperXmlUtils {

    public static void columsElement(BaseParam param, Element root, String before, String types) {

        switch (types) {
            case "if": {

                for (SchemaColumns obj : param.getColumns()) {

                    if ("PRI".equals(obj.getColumnKey())) {
                        continue;
                    }

                    String column = obj.getModelObjName();
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if ("String".equals(dataType.getJavaType())) {
                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", column + " != null and " + column + " != ''")
                                .addText(before + "\t" + obj.getColumnName() + "=" + "#{" + column + ",jdbcType=" + dataType.getMybatisType() + "}," + before);
                    } else {

                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", column + " != null")
                                .addText(before + "\t" + obj.getColumnName() + "=" + "#{" + column + ",jdbcType=" + dataType.getMybatisType() + "}," + before);
                    }
                }
                ;
                break;
            }
            case "ifAnd": {

                for (SchemaColumns obj : param.getColumns()) {

//                    if("PRI".equals(obj.getColumnKey())){
//                        continue;
//                    }

                    String column = obj.getModelObjName();
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if ("String".equals(dataType.getJavaType())) {
                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", column + " != null and " + column + " != ''")
                                .addText(before + "\tand " + obj.getColumnName() + "=" + "#{" + column + ",jdbcType=" + dataType.getMybatisType() + "}" + before);
                    } else {

                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", column + " != null")
                                .addText(before + "\tand " + obj.getColumnName() + "=" + "#{" + column + ",jdbcType=" + dataType.getMybatisType() + "}" + before);
                    }
                }
                ;
                break;
            }
            case "ifAndDb": {

                for (SchemaColumns obj : param.getColumns()) {

//                    if("PRI".equals(obj.getColumnKey())){
//                        continue;
//                    }

                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if ("String".equals(dataType.getJavaType())) {
                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", obj.getColumnName() + " != null and " + obj.getColumnName() + " != ''")
                                .addText(before + "\tand " + obj.getColumnName() + "=" + "#{" + obj.getColumnName() + ",jdbcType=" + dataType.getMybatisType() + "}" + before);
                    } else {

                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", obj.getColumnName() + " != null")
                                .addText(before + "\tand " + obj.getColumnName() + "=" + "#{" + obj.getColumnName() + ",jdbcType=" + dataType.getMybatisType() + "}" + before);
                    }
                }
                ;
                break;
            }

            case "colimnsIf": {

                for (SchemaColumns obj : param.getColumns()) {

                    if ("auto_increment".equals(obj.getExtra())) {
                        continue;
                    }

                    String column = obj.getModelObjName();
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if ("String".equals(dataType.getJavaType())) {
                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", column + " != null and " + column + " != ''")
                                .addText(before + "\t" + obj.getColumnName() + "," + before);
                    } else {

                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", column + " != null")
                                .addText(before + "\t" + obj.getColumnName() + "," + before);
                    }
                }
                ;
                break;
            }

            case "valuesIf": {

                for (SchemaColumns obj : param.getColumns()) {

                    if ("auto_increment".equals(obj.getExtra())) {
                        continue;
                    }

                    String column = obj.getModelObjName();
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if ("String".equals(dataType.getJavaType())) {
                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", column + " != null and " + column + " != ''")
                                .addText(before + "\t" + "#{" + column + ",jdbcType=" + dataType.getMybatisType() + "}," + before);
                    } else {

                        root.addComment(obj.getColumnComment())
                                .addElement("if")
                                .addAttribute("test", column + " != null")
                                .addText(before + "\t#{" + column + ",jdbcType=" + dataType.getMybatisType() + "}," + before);
                    }
                }
                ;
                break;
            }

            case "result": {
                if (null != param.getKeys() && param.getKeys().size() > 0) {

                    for (SchemaColumns obj : param.getKeys()) {
                        String column = obj.getModelObjName();
                        JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                        root.addElement("id")
                                .addAttribute("column", obj.getColumnName())
                                .addAttribute("jdbcType", dataType.getMybatisType())
                                .addAttribute("property", column);
                    }
                }

                for (SchemaColumns obj : param.getColumns()) {

                    String column = obj.getModelObjName();
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if ("PRI".equals(obj.getColumnKey())) {
                        continue;

                    }
                    root.addElement("result")
                            .addAttribute("column", obj.getColumnName())
                            .addAttribute("jdbcType", dataType.getMybatisType())
                            .addAttribute("property", column);

                }
                break;
            }
            case "trim": {
                for (SchemaColumns obj : param.getColumns()) {

                    if ("PRI".equals(obj.getColumnKey())) {
                        continue;
                    }

                    String column = obj.getModelObjName();
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    Element trims = root.addComment(obj.getColumnComment())
                            .addElement("trim")
                            .addAttribute("prefix", " " + column + " = case ")
                            .addAttribute("suffix", "end,");
                    Element foreachs = trims.addElement("foreach")
                            .addAttribute("collection", "list")
                            .addAttribute("item", "item")
                            .addAttribute("index", "index");

                    Element ifs = foreachs.addElement("if")
                            .addAttribute("test", "item." + column + "!=null");
                    if (param.getKeys().size() > 0) {
                        ifs.addText(before + "\t\t\twhen ");
                        for (int i = 0; i < param.getKeys().size(); i++) {
                            SchemaColumns schemaColumns = param.getKeys().get(i);
                            String cols = obj.getModelObjName();
                            JdbcTypeEnums dataTypes = JdbcTypeEnums.jdbcTypeEnumsByDbType(schemaColumns.getDataType());

                            if (i == 0) {
                                ifs.addText(schemaColumns.getColumnName() + "=#{item." + schemaColumns.getModelObjName() + ",jdbcType=" + dataTypes.getMybatisType() + "}");
                            } else {

                                ifs.addText(before + "\t\t\tand " + schemaColumns.getModelObjName() + "=#{item." + cols + ",jdbcType=" + dataTypes.getMybatisType() + "}");
                            }
                        }
                    }

                    ifs.addText(before + "\t\t\tthen #{item." + column + ",jdbcType=" + dataType.getMybatisType() + "}" + before + "\t\t");
                }

                break;
            }
        }
    }
}
