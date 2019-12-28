package com.wdk.general.core.utils;


import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.model.SchemaColumns;

import java.util.List;

/**
 * @author wdke
 * @date 2019/4/30
 */
public class TableColumnsHandlerUtil {

    /**
     * if条件判断
     *
     * @param columns
     * @param type    and ,or ,`,`
     * @return
     */
    public static String typeIf(List<SchemaColumns> columns, String type) {

        StringBuilder builder = new StringBuilder();

        switch (type) {
            case "and": {

                columns.forEach(obj -> {

                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if ("String".equals(dataType.getJavaType())) {
                        builder.append("\t\t<if test=\"").append(column).append(" != null and ").append(column).append(" != ''\">\n");
                        builder.append("\t\t\tand ").append(obj.getColumnName()).append("=#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("} ");
                        builder.append("\t\t</if>\n");
                    } else {
                        builder.append("\t\t<if test=\"").append(column).append(" != null\">\n");
                        builder.append("\t\t\tand ").append(obj.getColumnName()).append("=#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("} ");
                        builder.append("\t\t</if>\n");

                    }
                });
                break;
            }
            case "or": {
                columns.forEach(obj -> {
                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if ("String".equals(dataType.getJavaType())) {
                        builder.append("\t\t<if test=\"").append(column).append(" != null and ").append(column).append(" != ''\">\n");
                        builder.append("\t\t\tor ").append(obj.getColumnName()).append("=#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("} ");
                        builder.append("\t\t</if>\n");
                    } else {
                        builder.append("\t\t<if test=\"").append(column).append(" != null\">\n");
                        builder.append("\t\t\tor ").append(obj.getColumnName()).append("=#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("} ");
                        builder.append("\t\t</if>\n");
                    }
                });
                break;
            }
            case ",": {
                columns.forEach(obj -> {
                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                    if ("String".equals(dataType.getJavaType())) {
                        builder.append("\t\t<if test=\"").append(column).append(" != null and ").append(column).append(" != ''\">\n");
                        builder.append("\t\t\t").append(obj.getColumnName()).append("=#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("},\n");
                        builder.append("\t\t</if>\n");
                    } else {
                        builder.append("\t\t<if test=\"").append(column).append(" != null\">\n");
                        builder.append("\t\t\t").append(obj.getColumnName()).append("=#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("},");
                        builder.append("\t\t</if>\n");
                    }
                });
                break;
            }
            case "column": {
                columns.forEach(obj -> {

                    if (!"auto_increment".equals(obj.getExtra())) {
                        String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                        JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                        if ("String".equals(dataType.getJavaType())) {
                            builder.append("\t\t<if test=\"").append(column).append(" != null and ").append(column).append(" != ''\">\n");
                            builder.append("\t\t\t").append(obj.getColumnName()).append(",\n");
                            builder.append("\t\t</if>\n");
                        } else {
                            builder.append("\t\t<if test=\"").append(column).append(" != null\">\n");
                            builder.append("\t\t\t").append(obj.getColumnName()).append(",");
                            builder.append("\t\t</if>\n");
                        }
                    }
                });
                break;
            }
            case "value": {
                columns.forEach(obj -> {
                    if (!"auto_increment".equals(obj.getExtra())) {
                        String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                        JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                        if ("String".equals(dataType.getJavaType())) {
                            builder.append("\t\t<if test=\"").append(column).append(" != null and ").append(column).append(" != ''\">\n");
                            builder.append("\t\t\t#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("},\n");
                            builder.append("\t\t</if>\n");
                        } else {
                            builder.append("\t\t<if test=\"").append(column).append(" != null\">\n");
                            builder.append("\t\t\t#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("},");
                            builder.append("\t\t</if>\n");
                        }
                    }
                });
                break;
            }
        }
//        builder.append("\t</sql>\n\n");

        return builder.toString();
    }

    /**
     * 获取字段描述
     *
     * @param columns 字段属性列表
     * @param type    类型0 查询字段column，
     *                1 #{column},
     *                默认 mybatis全更新
     * @param last    字段后缀名字
     * @return
     */
    public static String typeColumns(List<SchemaColumns> columns, Integer type, String last, Integer nums) {

        StringBuilder builder = new StringBuilder();
        switch (type) {
            case 0: {
                for (int i = 0, j = 0; i < columns.size(); i++) {
                    SchemaColumns obj = columns.get(i);

                    if (builder.length() > 0) {
                        builder.append(",");
                    }

                    if ((j++ + 1) % nums == 0) {
                        builder.append("\n").append(last);

                    }

                    builder.append(obj.getColumnName());
                }
                break;
            }
            case 1: {
                for (int i = 0, j = 0; i < columns.size(); i++) {
                    SchemaColumns obj = columns.get(i);

                    if ("auto_increment".equals(obj.getExtra())) {
                        continue;
                    }
                    if (builder.length() > 0) {
                        builder.append(",");
                    }
                    if (j % nums == 0 && j != 0) {
                        builder.append("\n").append(last);
                    }
                    j++;

                    builder.append(obj.getColumnName());

                }
                break;
            }
            case 2: {
                for (int i = 0, j = 0; i < columns.size(); i++) {
                    SchemaColumns obj = columns.get(i);

                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if (builder.length() > 0) {
                        builder.append(",");
                    }
                    if (j != 0 && j++ % nums == 0) {
                        builder.append("\n").append(last);
                    }

                    builder.append("#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("}");
                }
                break;
            }
            case 3: {
                for (int i = 0, j = 0; i < columns.size(); i++) {
                    SchemaColumns obj = columns.get(i);

                    if ("auto_increment".equals(obj.getExtra())) {
                        continue;
                    }
                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if (builder.length() > 0) {
                        builder.append(",");
                    }
                    if (j++ % nums == 0 && j != 0) {
                        builder.append("\n").append(last);
                    }
                    builder.append("#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("}");


                }

                break;
            }

            case 4: {
                for (int i = 0, j = 0; i < columns.size(); i++) {
                    SchemaColumns obj = columns.get(i);

                    if ("auto_increment".equals(obj.getExtra())) {
                        continue;
                    }
                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if (builder.length() > 0) {
                        builder.append(",");
                    }
                    if (j++ % nums == 0 && j != 0) {
                        builder.append("\n").append(last);
                    }

                    builder.append("#{item.").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("}");

                }

                break;
            }


            case 5: {
                for (int i = 0, j = 0; i < columns.size(); i++) {
                    SchemaColumns obj = columns.get(i);
                    if (!"PRI".equals(obj.getColumnKey())) {
                        continue;
                    }
                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if (builder.length() > 0) {
                        builder.append("and ");
                    }

                    builder.append(obj.getColumnName()).append("=#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("}");

                    if ((j++ + 1) % nums == 0) {
                        builder.append("\n").append(last);

                    }
                }

                break;
            }
            case 6: {
                for (int i = 0, j = 0; i < columns.size(); i++) {
                    SchemaColumns obj = columns.get(i);
                    if ("PRI".equals(obj.getColumnKey())) {
                        continue;
                    }
                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if (builder.length() > 0) {
                        builder.append(",\n");
                    }
                    builder.append(last).append(obj.getColumnName()).append("=");
                    builder.append("#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("}");
                }
                ;
                break;
            }
        }
        return builder.toString();
    }

    /**
     * 获取字段描述
     *
     * @param columns 字段属性列表
     * @param type    类型0 查询字段column，
     *                1 实体属性 private type column，
     *                2 #{column},
     *                3 getter,
     *                4 lombox vo,
     *                5 lombox param,
     *                6 common vo,
     *                7 common param,
     *                默认 mybatis全更新
     * @param last    字段后缀名字
     * @return
     */
    public static String typeColumns(List<SchemaColumns> columns, Integer type, String last) {

        StringBuilder builder = new StringBuilder();
        switch (type) {
            case 0: {
                for (int i = 0, j = 1; i < columns.size(); i++) {
                    SchemaColumns obj = columns.get(i);

                    if (builder.length() > 0) {
                        builder.append(",");
                    }

                    if (builder.length() / 100 == j) {
                        builder.append(last);
                        j++;
                    }

                    builder.append(obj.getColumnName());
                }
                break;
            }
            case 1: {
                columns.forEach(obj -> {

                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                    builder.append("\t//").append(obj.getColumnComment()).append("\n");
                    if (null == dataType || null == dataType.getJavaType()) {

                        System.out.println(column);
                    }
                    if (dataType.getJavaType().equals("Date")) {
                        builder.append("\t@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm\")\n");
                    }
                    builder.append("\tprivate ");
                    builder.append(dataType.getJavaType());
                    builder.append(" ");
                    builder.append(column).append(";\n\n");
                });
                break;
            }
            case 2: {
                columns.forEach(obj -> {
                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if (builder.length() > 0) {
                        builder.append(",");
                    }
//                    builder.append(obj.getColumnName());
//                    builder.append("=");
                    builder.append("#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("}");
                });

                break;
            }
            case 3: {
                columns.forEach(obj -> {

                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    String getter = ColumnsUtil.columns(obj.getColumnName(), "getter");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                    builder.append("\t//").append(obj.getColumnComment());
                    builder.append("\tpublic ").append(dataType.getJavaType()).append(" get").append(getter).append("(){\n");
                    builder.append("\t\treturn this.").append(column).append("\n");
                    builder.append("\t}\n");


                    builder.append("\tpublic void set").append(getter).append("(").append(dataType.getJavaType()).append(" ").append(column).append("){\n");
                    builder.append("\t\tthis.").append(column).append("=").append(column).append("\n");
                    builder.append("\t}\n");
                });

                break;
            }
            case 4: {
                columns.forEach(obj -> {

                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    String getter = ColumnsUtil.columns(obj.getColumnName(), "getter");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                    if (dataType.getJavaType().equals("Date")) {

                        builder.append("\t//").append(obj.getColumnComment());
                        builder.append("\tpublic Long get").append(getter).append("(){\n");
                        builder.append("\t\tif(null==this.").append(column).append("){\n");
                        builder.append("\t\t\treturn null;\n");
                        builder.append("\t\t}\n");
                        builder.append("\t\treturn this.").append(column).append(".getTime()\n");
                        builder.append("\t}\n");
                    }
                });

                break;
            }

            case 5: {
                columns.forEach(obj -> {

                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    String getter = ColumnsUtil.columns(obj.getColumnName(), "getter");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                    if (dataType.getJavaType().equals("Date")) {
                        builder.append("\t//").append(obj.getColumnComment()).append("\n");
                        builder.append("\tpublic void set").append(getter).append("(Long ").append(column).append("){\n");
                        builder.append("\t\tif(null!=").append(column).append(")\n");
                        builder.append("\t\t\tthis.").append(column).append("=new Date(").append(column).append(")\n");
                        builder.append("\t\t}\n");
                        builder.append("\t}\n");
                    }
                });

                break;
            }
            case 6: {
                columns.forEach(obj -> {

                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    String getter = ColumnsUtil.columns(obj.getColumnName(), "getter");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                    if (dataType.getJavaType().equals("Date")) {

                        builder.append("\t//").append(obj.getColumnComment());

                        builder.append("\t//").append(obj.getColumnComment());
                        builder.append("\tpublic Long get").append(getter).append("(){\n");
                        builder.append("\t\tif(null==this.").append(column).append("){\n");
                        builder.append("\t\t\treturn null;\n");
                        builder.append("\t\t}\n");
                        builder.append("\t\treturn this.").append(column).append(".getTime()\n");
                        builder.append("\t}\n");


                        builder.append("\tpublic void set").append(getter).append("(").append(dataType.getJavaType()).append(" ").append(column).append("){\n");
                        builder.append("\t\tthis.").append(column).append("=").append(column).append("\n");
                        builder.append("\t}\n");

                    } else {
                        builder.append("\t//").append(obj.getColumnComment());
                        builder.append("\tpublic ").append(dataType.getJavaType()).append(" get").append(getter).append("(){\n");
                        builder.append("\t\treturn this.").append(column).append("\n");
                        builder.append("\t}\n");

                        builder.append("\tpublic void set").append(getter).append("(").append(dataType.getJavaType()).append(" ").append(column).append("){\n");
                        builder.append("\t\tthis.").append(column).append("=").append(column).append("\n");
                        builder.append("\t}\n");
                    }
                });

                break;
            }

            case 7: {
                columns.forEach(obj -> {

                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    String getter = ColumnsUtil.columns(obj.getColumnName(), "getter");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
                    if (dataType.getJavaType().equals("Date")) {

                        builder.append("\t//").append(obj.getColumnComment());

                        builder.append("\t//").append(obj.getColumnComment());
                        builder.append("\tpublic ").append(dataType.getJavaType()).append(" get").append(getter).append("(){\n");
                        builder.append("\t\treturn this.").append(column).append("\n");
                        builder.append("\t}\n");


                        builder.append("\t//").append(obj.getColumnComment());
                        builder.append("\tpublic void set").append(getter).append("(Long ").append(column).append("){\n");
                        builder.append("\t\tif(null!=").append(column).append(")\n");
                        builder.append("\t\t\tthis.").append(column).append("=new Date(").append(column).append(")\n");
                        builder.append("\t\t}\n");
                        builder.append("\t}\n");

                    } else {
                        builder.append("\t//").append(obj.getColumnComment());
                        builder.append("\tpublic ").append(dataType.getJavaType()).append(" get").append(getter).append("(){\n");
                        builder.append("\t\treturn this.").append(column).append("\n");
                        builder.append("\t}\n");


                        builder.append("\tpublic void set").append(getter).append("(").append(dataType.getJavaType()).append(" ").append(column).append("){\n");
                        builder.append("\t\tthis.").append(column).append("=").append(column).append("\n");
                        builder.append("\t}\n");
                    }
                });

                break;
            }
            default: {
                columns.forEach(obj -> {
                    String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                    JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                    if (builder.length() > 0) {
                        builder.append(",\n");
                    }
                    builder.append(obj.getColumnName());
                    builder.append("=");
                    builder.append("#{").append(column).append(",jdbcType=").append(dataType.getMybatisType()).append("}");
                });
                builder.append("\n\n");

                break;
            }
        }
        return builder.toString();
    }


    /**
     * <result></result>
     *
     * @param columns
     * @param last
     * @return
     */
    public static String result(List<SchemaColumns> columns, String last) {

        StringBuilder builder = new StringBuilder();
        if (StringUtils.isEmpty(last)) {
            columns.forEach(obj -> {
                String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                if ("PRI".equals(obj.getColumnKey())) {
                    builder.append("\t\t<id column=\"").append(obj.getColumnName()).append("\" jdbcType=\"").append(dataType.getMybatisType()).append("\" property=\"").append(column).append("\" />\n");
                } else {
                    builder.append("\t\t<result column=\"").append(obj.getColumnName()).append("\" jdbcType=\"").append(dataType.getMybatisType()).append("\" property=\"").append(column).append("\" />\n");
                }
            });
        } else {

            columns.forEach(obj -> {
                String column = ColumnsUtil.columns(obj.getColumnName(), "java");
                JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());

                if ("PRI".equals(obj.getColumnKey())) {
                    builder.append("\t\t<id column=\"").append(obj.getColumnName()).append("_").append(last).append("\" jdbcType=\"").append(dataType.getMybatisType()).append("\" property=\"").append(column).append("\" />\n");
                } else {
                    builder.append("\t\t<result column=\"").append(obj.getColumnName()).append("_").append(last).append("\" jdbcType=\"").append(dataType.getMybatisType()).append("\" property=\"").append(column).append("\" />\n");
                }
            });
        }

        return builder.toString();
    }

}
