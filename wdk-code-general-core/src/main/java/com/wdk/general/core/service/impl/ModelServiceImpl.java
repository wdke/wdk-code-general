package com.wdk.general.core.service.impl;

import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.service.ModelService;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.utils.TableColumnsHandlerUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wdke
 * @date 2019/5/8
 */
@Service
public class ModelServiceImpl implements ModelService {


    @Override
    public void init(BaseParam param, String packages) {

        String file = UserContext.current().getProjectServerRoot() + "/src/main/java/" + packages.replaceAll("\\.", "/") + "/model/" + param.getModelName() + ".java";

        String model = "package " + packages + ".model;\n\n" + lombokModel(param);

        try {
            FileUtil.write(file, model);
        } catch (IOException e) {
            e.printStackTrace();
        }


        file = UserContext.current().getProjectServerRoot() + "/src/main/java/" + packages.replaceAll("\\.", "/") + "/web/args/" + param.getModelName() + "Args.java";

        model = "package " + packages + ".web.args;\n\n" + lombokModelParam(param);

        try {
            FileUtil.write(file, model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成lombok 对应的类
     *
     * @param param
     * @return
     */
    @Override
    public String lombokModel(BaseParam param) {
        StringBuilder model = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        model.append("import lombok.Data;\n")
                .append("import java.util.Date;\n")
                .append("import com.fasterxml.jackson.annotation.JsonInclude;\n")
                .append("import org.springframework.format.annotation.DateTimeFormat;")
                .append("import java.io.Serializable;\n\n")
                .append("/**\n")
                .append(" * ").append(param.getTableComment()).append("\n")
                .append(" * @db ").append(param.getTableSchema()).append("\n");
        model.append(" * @table ").append(param.getTableName()).append("\n");
        model.append(" * @author wdke\n");
        model.append(" * @date ").append(sdf.format(new Date())).append("\n");
        model.append(" */\n");
        model.append("@Data\n");
        model.append("@JsonInclude(JsonInclude.Include.NON_NULL)\n");
        model.append("public class ").append(param.getModelName()).append(" implements Serializable {\n\n");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 1, null);
        model.append(colimns);

        model.append("\n\n}\n\n");


        return model.toString();
    }

    /**
     * 生成lombok 对应的Vo类
     *
     * @param param
     * @return
     */
    @Override
    public String lombokModelVo(BaseParam param) {

        StringBuilder model = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        model.append("import lombok.Data;\n");
        model.append("import com.fasterxml.jackson.annotation.JsonInclude;\n");
        model.append("import java.util.Date;\n");
        model.append("import java.io.Serializable;\n\n");
        model.append("/**\n");
        model.append(" * ").append(param.getTableComment()).append("\n");
        model.append(" * @db ").append(param.getTableSchema()).append("\n");
        model.append(" * @table ").append(param.getTableName()).append("\n");
        model.append(" * @author wdke\n");
        model.append(" * @date ").append(sdf.format(new Date())).append("\n");
        model.append(" */\n");
        model.append("@JsonInclude(JsonInclude.Include.NON_NULL)\n");
        model.append("@Data\n");
        model.append("public class ").append(param.getModelName()).append("Vo implements Serializable {\n\n");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 1, null);
        model.append(colimns);
        model.append("\n\n");
        String getter = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 4, null);
        model.append(getter);
        model.append("\n\n}\n\n");

        return model.toString();
    }

    /**
     * 获取param
     *
     * @param param
     * @return
     */
    @Override
    public String lombokModelParam(BaseParam param) {

        StringBuilder model = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        model.append("import lombok.Data;\n");
        model.append("import java.util.Date;\n");
        model.append("import org.springframework.format.annotation.DateTimeFormat;\n");
        model.append("import com.fasterxml.jackson.annotation.JsonInclude;\n");
        model.append("import java.io.Serializable;\n\n");
        model.append("/**\n");
        model.append(" * ").append(param.getTableComment()).append("\n");
        model.append(" * @db ").append(param.getTableSchema()).append("\n");
        model.append(" * @table ").append(param.getTableName()).append("\n");
        model.append(" * @author wdke\n");
        model.append(" * @date ").append(sdf.format(new Date())).append("\n");
        model.append(" */\n");
        model.append("@Data\n");
        model.append("@JsonInclude(JsonInclude.Include.NON_NULL)\n");
        model.append("public class ").append(param.getModelName()).append("Args implements Serializable {\n\n");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 1, null);
        model.append(colimns);
        model.append("\n\n");
//        String getter = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 5, null);
//        model.append(getter);
        model.append("\n\n}\n\n");

        return model.toString();
    }

    /**
     * 普通的
     *
     * @param param
     * @return
     */
    @Override
    public String commonModel(BaseParam param) {

        StringBuilder model = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        model.append("import lombok.Data;\n")
                .append("import java.util.Date;\n")
                .append("import com.fasterxml.jackson.annotation.JsonInclude;\n")
                .append("import java.io.Serializable;\n\n")
                .append("/**\n")
                .append(" * ").append(param.getTableComment()).append("\n")
                .append(" * @db ").append(param.getTableSchema()).append("\n")
                .append(" * @table ").append(param.getTableName()).append("\n")
                .append(" * @author wdke\n")
                .append(" * @date ").append(sdf.format(new Date())).append("\n")
                .append(" */\n")
                .append("@Data\n")
                .append("@JsonInclude(JsonInclude.Include.NON_NULL)\n")
                .append("public class ").append(param.getModelName()).append(" implements Serializable {\n\n");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 1, null);
        model.append(colimns).append("\n\n");
        String getter = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 3, null);

        model.append(getter);
        model.append("\n\n}\n\n");


        return model.toString();
    }

    /**
     * 普通的model,包含getset方法Vo
     *
     * @param param
     * @return
     */
    @Override
    public String commonModelVo(BaseParam param) {
        StringBuilder model = new StringBuilder();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        model.append("import lombok.Data;\n");
        model.append("import com.fasterxml.jackson.annotation.JsonInclude;\n");
        model.append("import java.util.Date;\n");
        model.append("import java.io.Serializable;\n\n");
        model.append("/**\n");
        model.append(" * ").append(param.getTableComment()).append("\n");
        model.append(" * @db ").append(param.getTableSchema()).append("\n");
        model.append(" * @table ").append(param.getTableName()).append("\n");
        model.append(" * @author wdke\n");
        model.append(" * @date ").append(sdf.format(new Date())).append("\n");
        model.append(" */\n");
        model.append("@Data\n");
        model.append("@JsonInclude(JsonInclude.Include.NON_NULL)\n");
        model.append("public class ").append(param.getModelName()).append("Vo implements Serializable {\n\n");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 1, null);
        model.append(colimns);
        model.append("\n\n");
        String getter = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 6, null);

        model.append(getter);
        model.append("\n\n}\n\n");


        return model.toString();
    }

    /**
     * 普通的model,包含getset方法Param
     *
     * @param param
     * @return
     */
    @Override
    public String commonModelParam(BaseParam param) {

        StringBuilder model = new StringBuilder();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        model.append("import lombok.Data;\n");
        model.append("import java.util.Date;\n");
        model.append("import com.fasterxml.jackson.annotation.JsonInclude;\n");
        model.append("import java.io.Serializable;\n\n");
        model.append("/**\n");
        model.append(" * ").append(param.getTableComment()).append("\n");
        model.append(" * @db ").append(param.getTableSchema()).append("\n");
        model.append(" * @table ").append(param.getTableName()).append("\n");
        model.append(" * @author wdke\n");
        model.append(" * @date ").append(sdf.format(new Date())).append("\n");
        model.append(" */\n");
        model.append("@Data\n");
        model.append("@JsonInclude(JsonInclude.Include.NON_NULL)\n");
        model.append("public class ").append(param.getModelName()).append("Param implements Serializable {\n\n");
        String colimns = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 1, null);
        model.append(colimns);
        model.append("\n\n");
        String getter = TableColumnsHandlerUtil.typeColumns(param.getColumns(), 7, null);

        model.append(getter);
        model.append("\n\n}\n\n");

        return model.toString();
    }
}
