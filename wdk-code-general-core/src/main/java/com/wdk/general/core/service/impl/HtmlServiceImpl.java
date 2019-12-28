package com.wdk.general.core.service.impl;

import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.model.Tables;
import com.wdk.general.core.service.HtmlService;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * created by wdk on 2019/12/17
 */

@Service
public class HtmlServiceImpl implements HtmlService {

    private static Logger logger = LoggerFactory.getLogger(HtmlService.class);


    /**
     * 生成所有文件
     *
     * @param baseParam
     */
    @Override
    public void all(BaseParam baseParam) {

        //生成主页
        String rootFile = UserContext.current().getProjectServerRoot() + "/src/main/resources/templates/" + baseParam.objName() + "/index.html";
        String fileContent = index(baseParam);
        try {

            logger.info("生成index.html文件");
            FileUtil.write(rootFile, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成新增
        rootFile = UserContext.current().getProjectServerRoot() + "/src/main/resources/templates/" + baseParam.objName() + "/insert.html";
        fileContent = insert(baseParam);
        try {

            logger.info("生成insert.html文件");
            FileUtil.write(rootFile, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成新增
        rootFile = UserContext.current().getProjectServerRoot() + "/src/main/resources/templates/" + baseParam.objName() + "/update.html";
        fileContent = update(baseParam);
        try {

            logger.info("生成update.html文件");
            FileUtil.write(rootFile, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 菜单页面
     *
     * @param tables
     * @return
     */
    @Override
    public String menus(List<Tables> tables) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n")
                .append("<html xmlns:th=\"http://www.thymeleaf.org\" xmlns:c=\"http://www.springframework.org/schema/util\" lang=\"en\">\n");
        head(sb, "菜单");

        sb.append("<body>\n")
                .append("<div class=\"container\">\n")
                .append("\t<ul class=\"nav nav-tabs\">\n");
        for (int i = 0; i < tables.size(); i++) {
            sb.append("\t\t<li name=\"menus\"");
            if (i == 0) {
                sb.append(" class=\"active\" ");
            }
            sb.append("onclick=\"changePage(this,'/pages/")
                    .append(tables.get(i).getTableName().replaceAll("_", "/"))
                    .append("')\"><a href=\"#\">")
                    .append(tables.get(i).getTableComment())
                    .append("</a></li>\n");

        }
        sb.append("\t</ul>\n")
                .append("</div>\n\n")
                .append("<iframe id=\"iframe\" frameborder=\"0\" style=\"padding-top: 50px;\" width=\"100%\" height=\"800\" th:src=\"@{/pages/").append(tables.get(0).getTableName().replaceAll("_", "/")).append("}\"></iframe>\n")
                .append("\n\n")
                .append("<script type=\"text/javascript\">\n\n")
                .append("\t//页面跳转方法\n")
                .append("\tfunction changePage(obj,url) {\n\n")
                .append("\t\t$(\"li[name='menus']\").prop(\"class\", \"\");//全选\n")
                .append("\t\t$(obj).prop(\"class\",\"active\");\n")
                .append("\t\t$(\"#iframe\").prop(\"src\",url);\n")
                .append("\t}\n\n")
                .append("</script>\n\n")
                .append("</body>\n")
                .append("</html>");
        return sb.toString();
    }

    /**
     * 生成主页
     *
     * @param baseParam
     * @return
     */
    @Override
    public String index(BaseParam baseParam) {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html >\n")
                .append("<html xmlns=\"http://www.w3.org/1999/xhtml\"\n")
                .append("\t\txmlns:th=\"http://www.thymeleaf.org\"\n")
                .append("\t\txmlns:c=\"http://java.sun.com/jsp/jstl/core\">\n");
        //html heaad
        head(sb, baseParam.getTableComment() + "首页");

        sb.append("<body>\n")
                .append("\n")
                .append("<div class=\"container\">\n")
                .append("\t<table class=\"table table-striped\">\n")
                .append("\t\t<thead>\n")
                .append("\t\t\t<tr>\n")
                .append("\t\t\t\t<th>序号</th>\n");

        for (SchemaColumns columns : baseParam.getColumns()) {
            sb.append("\t\t\t\t<th>").append(columns.getColumnComment()).append("</th>\n");
        }
        sb.append("\t\t\t\t<th>操作</th>\n")
                .append("\t\t\t</tr>\n")
                .append("\t\t</thead>\n")
                .append("\t\t<tbody>\n")
                .append("\t\t\t<tr th:each=\"data,dataStat : ${datas.list}\">\n")
                .append("\t\t\t\t<td th:text=\"${dataStat.index+1}\"></td>\n");

        for (SchemaColumns columns : baseParam.getColumns()) {
            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(columns.getDataType());
            if (dataType.getJavaType().equals("Date")) {
                sb.append("\t\t\t\t<td th:text=\"${#dates.format(data.").append(columns.objName()).append(",'yyyy-MM-dd HH:mm')}\"></td>\n");
            } else {
                sb.append("\t\t\t\t<td th:text=\"${data.").append(columns.objName()).append("}\"></td>\n");
            }
        }
        if (baseParam.getKeys().size() > 0) {
            sb.append("\t\t\t\t<td>\n")
                    .append("\t\t\t\t\t<a href=\"#\" th:onclick=\"'javascript:remove(");
            for (int i = 0; i < baseParam.getKeys().size(); i++) {

                SchemaColumns schemaColumns = baseParam.getKeys().get(i);
                if (i == 0) {
                    sb.append("'+${data.").append(schemaColumns.objName()).append("}+'");
                } else {
                    sb.append(",'+${data.").append(schemaColumns.objName()).append("}+'");
                }
            }
            sb.append(")'\"><span>删除</span></a>\n")
                    .append("\t\t\t\t\t<a href=\"#\" th:href=\"@{/pages/").append(baseParam.getTableName().replaceAll("_", "/")).append("/insert/pages}\"><span>新增</span></a>\n")
                    .append("\t\t\t\t\t<a href=\"#\" th:href=\"@{/pages/").append(baseParam.getTableName().replaceAll("_", "/")).append("/update/pages(");
            for (int i = 0; i < baseParam.getKeys().size(); i++) {
                String colunm = baseParam.getKeys().get(i).objName();
                if (i == 0) {
                    sb.append(colunm).append("=").append("${data.").append(colunm).append("}");
                } else {
                    sb.append(",").append(colunm).append("=").append("${data.").append(colunm).append("}");
                }

            }
            sb.append(")}\"><span>修改</span></a>")
                    .append("</td>\n");

        }
        sb.append("\t\t\t</tr>\n")
                .append("\t\t</tbody>\n")
                .append("\t</table>\n")
                .append("\n")
                .append("\t<div style=\"text-align: right;\">\n")
                .append("\t\t<form id=\"form\" role=\"form\" action=\"#\" method=\"post\" th:action=\"@{/pages/").append(baseParam.getTableName().replaceAll("_", "/")).append("}\">\n")
                .append("\t\t\t<ul class=\"pagination\">\n")
                .append("\t\t\t\t<li><a href=\"#\" onclick=\"changeSubPages()\">&laquo;</a></li>\n")
                .append("\t\t\t\t<li th:if=\"${datas.prePage<datas.pageNum and datas.prePage>0}\" onclick=\"changeSubPages()\"><a href=\"#\" th:text=\"${datas.prePage}\"></a></li>\n")
                .append("\t\t\t\t<li class=\"active\"><a href=\"#\" th:text=\"${datas.pageNum}\"></a></li>\n")
                .append("\t\t\t\t<li th:if=\"${datas.nextPage>datas.pageNum}\" onclick=\"changeAddPages()\"><a href=\"#\" th:text=\"${datas.nextPage}\"></a></li>\n")
                .append("\t\t\t\t<li><a href=\"#\" onclick=\"changeAddPages()\">&raquo;</a></li>\n")
                .append("\n")
                .append("\n")
                .append("\t\t\t\t<li><span>总数量：<span th:text=\"${datas.total}\"></span>&nbsp;&nbsp;&nbsp;&nbsp;页面大小：\n")
                .append("\t\t\t\t\t<select style=\"height: 100%;\" onchange=\"sizeChange()\" name=\"pageSize\">\n")
                .append("\t\t\t\t\t\t<option th:selected=\"${datas.pageSize==5}\" value=\"5\">5</option>\n")
                .append("\t\t\t\t\t\t<option th:selected=\"${datas.pageSize==10}\" value=\"10\">10</option>\n")
                .append("\t\t\t\t\t\t<option th:selected=\"${datas.pageSize==15}\" value=\"15\">15</option>\n")
                .append("\t\t\t\t\t\t<option th:selected=\"${datas.pageSize==20}\" value=\"20\">20</option>\n")
                .append("\t\t\t\t\t</select>\n")
                .append("\t\t\t\t\t</span>\n")
                .append("\t\t\t\t</li>\n")
                .append("\t\t\t</ul>\n")
                .append("\t\t\t<input type=\"hidden\" id=\"prePage\" name=\"pageNum\" th:value=\"${datas.prePage}\"/>\n")
                .append("\t\t\t<input type=\"hidden\" id=\"pageNum\" name=\"pageNum\" th:value=\"${datas.pageNum}\"/>\n")
                .append("\t\t\t<input type=\"hidden\" id=\"nextPage\" name=\"pageNum\" th:value=\"${datas.nextPage}\"/>\n")
                .append("\t\t</form>\n")
                .append("\n")
                .append("\t</div>\n")
                .append("</div>\n")
                .append("<script type=\"text/javascript\">\n")
                .append("\tfunction changeSubPages() {\n")
                .append("\t\tif(parseInt($(\"#pageNum\").val())>0){\n")
                .append("\t\t\t$(\"#pageNum\").val(parseInt($(\"#pageNum\").val())-1);\n")
                .append("\t\t\t$(\"#form\").submit();\n")
                .append("\t\t}\n")
                .append("\t}\n")
                .append("\tfunction changeAddPages() {\n")
                .append("\t\tif(parseInt($(\"#nextPage\").val())>parseInt($(\"#pageNum\").val())){\n")
                .append("\n")
                .append("\t\t\t$(\"#pageNum\").val(parseInt($(\"#pageNum\").val())+1);\n")
                .append("\t\t\t$(\"#form\").submit();\n")
                .append("\t\t}\n")
                .append("\t}\n")
                .append("\n")
                .append("\tfunction sizeChange() {\n")
                .append("\t\t$(\"#form\").submit();\n")
                .append("\n")
                .append("\t}\n")
                .append("\t/**\n")
                .append("\t * 移除方法\n")
                .append("\t */\n")
                .append("\tfunction remove(");

        if (baseParam.getKeys().size() > 0) {
            for (int i = 0; i < baseParam.getKeys().size(); i++) {

                SchemaColumns schemaColumns = baseParam.getKeys().get(i);
                if (i == 0) {
                    sb.append(schemaColumns.objName());
                } else {
                    sb.append(",").append(schemaColumns.objName());
                }
            }
        }
        sb.append(") {\n")
                .append("\t\tif(!confirm(\"删除是不可恢复的，你确认要删除吗？\")){\n")
                .append("\t\t\treturn;\n")
                .append("\t\t}\n")
                .append("\t\tvar param={\n");

        if (baseParam.getKeys().size() > 0) {
            for (int i = 0; i < baseParam.getKeys().size(); i++) {

                SchemaColumns schemaColumns = baseParam.getKeys().get(i);
                if (i == 0) {
                    sb.append("\t\t\t").append(schemaColumns.objName()).append(":").append(schemaColumns.objName()).append("\n");
                } else {
                    sb.append(",\n\t\t\t").append(schemaColumns.objName()).append(":").append(schemaColumns.objName()).append("\n");
                }
            }
        }
        sb.append("\n\t\t}\n")
                .append("\t\t$.ajax({\n")
                .append("\t\t\ttype:\"POST\",\n")
                .append("\t\t\turl:\"/pages/").append(baseParam.getTableName().replaceAll("_", "/")).append("/remove\",\n")
                .append("\t\t\txhrFields: {\n")
                .append("\t\t\t\twithCredentials: true\n")
                .append("\t\t\t},\n")
                .append("\t\t\tdata:param,\n")
                .append("\t\t\tdataType:\"json\",\n")
                .append("\t\t\tsuccess:function(data){\n")
                .append("\t\t\t\talert(data.msg);\n")
                .append("\t\t\t\t$(\"#form\").submit();\n")
                .append("\t\t\t},\n")
                .append("\t\t\terror:function(jqXHR){\n")
                .append("\t\t\t\talert(JSON.stringify(jqXHR));\n")
                .append("\t\t\t\talert(\"发生错误\" + jqXHR.status);\n")
                .append("\t\t\t}\n")
                .append("\t\t});\n")
                .append("\t}\n")
                .append("</script>\n")
                .append("</body>\n")
                .append("\n")
                .append("\n")
                .append("</html>\n");
        return sb.toString();
    }

    /**
     * 新增页面
     *
     * @param baseParam
     * @return
     */
    @Override
    public String insert(BaseParam baseParam) {

        return operation(baseParam, false, "insert", "新增");
    }

    /**
     * 更新页面
     *
     * @param baseParam
     * @return
     */
    @Override
    public String update(BaseParam baseParam) {
        return operation(baseParam, true, "update", "更新");
    }

    /**
     * 生成操作页面
     *
     * @param baseParam
     * @param defaultValue
     * @param tags
     * @return
     */
    @Override
    public String operation(BaseParam baseParam, Boolean defaultValue, String path, String tags) {
        if (null == baseParam || null == baseParam.getColumns() || baseParam.getColumns().size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>\n")
                .append("<html xmlns=\"http://www.w3.org/1999/xhtml\"\n")
                .append("\txmlns:th=\"http://www.thymeleaf.org\">\n");
        //头文件信息
        head(sb, baseParam.getTableComment() + tags);

        //html body
        sb.append("<body>\n")
                .append("\n")
                .append("<div class=\"container\">\n");

        //html form表单
        formByDiv(sb, baseParam, defaultValue, path);

        //html 收尾
        sb.append("</div>\n\n")
                .append("</body>\n")
                .append("</html>");

        return sb.toString();
    }

    /**
     * html head 部分
     *
     * @param sb
     * @param title
     */
    @Override
    public void head(StringBuilder sb, String title) {

        sb.append("<head>\n")
                .append("\t<meta charset=\"UTF-8\">\n")
                .append("\t<title>").append(title).append("</title>\n")
                .append("\t<link rel=\"stylesheet\" th:href=\"@{/static/bootstrap/3.3.7/css/bootstrap.min.css}\">\n")
                .append("\t<script th:src=\"@{/static/bootstrap/3.3.7/js/bootstrap.min.js}\"></script>\n")
                .append("\t<script th:src=\"@{/static/jquery/js/jquery-3.4.1.min.js}\"></script>\n")
                .append("</head>\n");
    }

    /**
     * 生成表单
     *
     * @param sb
     * @param baseParam
     * @param update
     */
    @Override
    public void formByTable(StringBuilder sb, BaseParam baseParam, Boolean update, String savePath) {

        if (null == baseParam || null == baseParam.getColumns() || baseParam.getColumns().size() == 0) {
            return;
        }
        //表单信息
        sb.append("\t<form class=\"form-horizontal\" action=\"#\" role=\"form\" th:action=\"@{/pages/")
                .append(baseParam.getTableName().replaceAll("_", "/"))
                .append("/").append(savePath).append("}\" method=\"post\">\n")
                .append("\t\t<table class=\"table table-striped\">\n")
                .append("\t\t\t<tbody>\n");

        boolean width = true;

        //生成输入
        for (SchemaColumns col : baseParam.getColumns()) {
            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(col.getDataType());
            sb.append("\t\t\t<tr>\n");
            if (width) {
                sb.append("\t\t\t\t<th width=\"50\">\n");
                width = false;
            }
            sb.append("\t\t\t\t\t<label for=\"").append(col.objName()).append("\" class=\"col-sm-2 control-label\">").append(col.getColumnComment()).append(":</label>\n")
                    .append("\t\t\t\t</th>\n")
                    .append("\t\t\t\t<td>\n");
            if (dataType.getJavaType().equals("Date")) {

                sb.append("\t\t\t\t\t<input type=\"datetime\" class=\"form-control\" name=\"").append(col.objName()).append("\" id=\"").append(col.objName()).append("\"\n")
                        .append("\t\t\t\t\t\t\tplaceholder=\"请输入").append(col.getColumnComment()).append("\"");
                if (update) {
                    sb.append(" th:value=\"${#dates.format(data.").append(col.objName()).append(",'yyyy-MM-dd HH:mm:ss')}\"");
                }
//                ${#dates.format(data.").append(columns.objName()).append(",'yyyy-MM-dd HH:mm')}
                sb.append("/>\n");
            } else {
                sb.append("\t\t\t\t\t<input type=\"text\" class=\"form-control\" name=\"").append(col.objName()).append("\" id=\"").append(col.objName()).append("\"\n")
                        .append("\t\t\t\t\t\t\tplaceholder=\"请输入").append(col.getColumnComment()).append("\"");
                if (update) {
                    sb.append(" th:value=\"${data.").append(col.objName()).append("}\"");
                }

                sb.append("/>\n");
            }
            sb.append("\t\t\t\t</td>\n")
                    .append("\t\t\t</tr>\n");
        }
        sb.append("\t\t\t</tbody>\n")
                .append("\t\t</table>\n")
                .append("\t\t<div style=\"text-align: right;\">\n")
                .append("\t\t\t<button type=\"submit\" class=\"btn btn-default\">提交</button>\n")
                .append("\t\t</div>\n")
                .append("\t</form>\n");

    }

    /**
     * 表单信息
     *
     * @param sb
     * @param baseParam
     * @param update
     */
    @Override
    public void formByDiv(StringBuilder sb, BaseParam baseParam, Boolean update, String savePath) {

        if (null == baseParam || null == baseParam.getColumns() || baseParam.getColumns().size() == 0) {
            return;
        }
        //表单信息
        sb.append("\t<form class=\"form-horizontal\" action=\"#\" role=\"form\" th:action=\"@{/pages/")
                .append(baseParam.getTableName().replaceAll("_", "/"))
                .append("/").append(savePath).append("}\" method=\"post\">\n");

        //生成输入
        for (SchemaColumns col : baseParam.getColumns()) {
            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(col.getDataType());
            if ("Date".equals(dataType.getJavaType())) {

                sb.append("\t\t<div class=\"form-group\">\n")
                        .append("\t\t\t<label for=\"").append(col.objName()).append("\" class=\"col-sm-2 control-label\">").append(col.getColumnComment()).append(":</label>\n")
                        .append("\t\t\t<div class=\"col-sm-10\">\n")
                        .append("\t\t\t\t<input type=\"datetime\" class=\"form-control\" name=\"").append(col.objName()).append("\" id=\"").append(col.objName()).append("\"\n")
                        .append("\t\t\t\t\t\tplaceholder=\"yyyy-MM-dd HH:mm\"");
                if (update) {
                    sb.append(" th:value=\"${#dates.format(data.").append(col.objName()).append(",'yyyy-MM-dd HH:mm')}\"");
                }
                sb.append("/>\n")
                        .append("\t\t\t</div>\n")
                        .append("\t\t</div>\n");
            } else {
                sb.append("\t\t<div class=\"form-group\">\n")
                        .append("\t\t\t<label for=\"").append(col.objName()).append("\" class=\"col-sm-2 control-label\">").append(col.getColumnComment()).append(":</label>\n")
                        .append("\t\t\t<div class=\"col-sm-10\">\n")
                        .append("\t\t\t\t<input type=\"text\" class=\"form-control\" name=\"").append(col.objName()).append("\" id=\"").append(col.objName()).append("\"\n")
                        .append("\t\t\t\t\t\tplaceholder=\"请输入").append(col.getColumnComment()).append("\"");
                if (update) {
                    sb.append(" th:value=\"${data.").append(col.objName()).append("}\"");
                }

                sb.append("/>\n")
                        .append("\t\t\t</div>\n")
                        .append("\t\t</div>\n");
            }

        }
        sb.append("\t\t<div style=\"text-align: right;\">\n")
                .append("\t\t\t<button type=\"submit\" class=\"btn btn-default\">提交</button>\n")
                .append("\t\t</div>\n")
                .append("\t</form>\n");
    }

    /**
     * 列表信息
     *
     * @param sb
     * @param baseParam
     * @param operation
     */
    @Override
    public void table(StringBuilder sb, BaseParam baseParam, Boolean operation) {

    }


}
