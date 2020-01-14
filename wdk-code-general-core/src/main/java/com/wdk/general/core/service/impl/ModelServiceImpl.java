package com.wdk.general.core.service.impl;

import com.wdk.general.core.common.constant.FilePathConstant;
import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.ModelService;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.utils.StringConversionUtil;
import com.wdk.general.core.utils.TableColumnsHandlerUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.springframework.stereotype.Service;

import javax.jnlp.FileContents;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wdke
 * @date 2019/5/8
 */
@Service
public class ModelServiceImpl implements ModelService {


    @Override
    public void init(BaseParam param, String packages) {

        //生成model
        if (!param.isMybatisCore()) {

            try {
                FileUtil.write(FilePathConstant.model(param.getModelName()), lombokModel(param, packages));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //生成参数
        try {
            FileUtil.write(FilePathConstant.args(param.getModelName()), lombokModelArgs(param, packages));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成输出
        try {
            FileUtil.write(FilePathConstant.vo(param.getModelName()), lombokModelVo(param, packages));
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
    public String lombokModel(BaseParam param, String packages) {
        StringBuilder model = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        model.append("package " + packages + ".storage.entity;\n\n")
                .append("import lombok.Data;\n")
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


        model.append("\n\t//排序字段\n")
                .append("\tprivate String orderBy;\n\n");

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
    public String lombokModelVo(BaseParam param, String packages) {

        StringBuilder model = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        model.append("package " + packages + ".web.vo;\n\n")
                .append("import lombok.Data;\n")
                .append("import com.fasterxml.jackson.annotation.JsonInclude;\n")
                .append("import java.util.Date;\n")
                .append("import java.io.Serializable;\n\n");
        model.append("/**\n")
                .append(" * ").append(param.getTableComment()).append("\n")
                .append(" * @db ").append(param.getTableSchema()).append("\n")
                .append(" * @table ").append(param.getTableName()).append("\n")
                .append(" * @author wdke\n")
                .append(" * @date ").append(sdf.format(new Date())).append("\n")
                .append(" */\n");

        model.append("@JsonInclude(JsonInclude.Include.NON_NULL)\n")
                .append("@Data\n")
                .append("public class ").append(param.getModelName()).append("Vo implements Serializable {\n\n");

        property(model, param.getColumns(), false);

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
    public String lombokModelArgs(BaseParam param, String packages) {

        StringBuilder model = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH");
        model.append("package " + packages + ".web.args;\n\n")
                .append("import lombok.Data;\n")
                .append("import java.util.Date;\n")
                .append("import org.springframework.format.annotation.DateTimeFormat;\n")
                .append("import org.hibernate.validator.constraints.Length;\n")
                .append("import com.fasterxml.jackson.annotation.JsonInclude;\n")
                .append("import javax.validation.constraints.*;\n")
                .append("import java.io.Serializable;\n\n");

        model.append("/**\n")
                .append(" * ").append(param.getTableComment()).append("\n")
                .append(" * @db ").append(param.getTableSchema()).append("\n")
                .append(" * @table ").append(param.getTableName()).append("\n")
                .append(" * @author wdke\n")
                .append(" * @date ").append(sdf.format(new Date())).append("\n")
                .append(" */\n");

        model.append("@Data\n")
                .append("@JsonInclude(JsonInclude.Include.NON_NULL)\n")
                .append("public class ").append(param.getModelName()).append("Args implements Serializable {\n\n");

        property(model, param.getColumns(), true);

        model.append("\n\n");

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

    /**
     * 生成属性值
     *
     * @param sb
     * @param columns
     * @return
     */
    @Override
    public void property(StringBuilder sb, List<SchemaColumns> columns, Boolean annotation) {


        columns.forEach(obj -> {

            JdbcTypeEnums dataType = JdbcTypeEnums.jdbcTypeEnumsByDbType(obj.getDataType());
            if (null == dataType || null == dataType.getJavaType() || StringConversionUtil.isEmpty(obj.getModelObjName())) {
                return;
            }
            sb.append("\t//").append(obj.getColumnComment()).append("\n");

            if (annotation) {
                if (dataType.getJavaType().equals("Date")) {
                    sb.append("\t@DateTimeFormat(pattern = \"yyyy-MM-dd HH:mm\")\n");
                }
                if (!"NO".equals(obj.getIsNullable()) && !"String".equals(dataType.getJavaType())) {
                    sb.append("\t@NotNull(message = \"cannot be null\")\n");
                }
                if (!"NO".equals(obj.getIsNullable()) && "String".equals(dataType.getJavaType())) {
                    sb.append("\t@NotBlank(message = \"cannot be empty\")\n");
                }
                if ("String".equals(dataType.getJavaType()) && null != obj.getCharacterMaximumLength() && obj.getCharacterMaximumLength() > 0 && 2094967295 > obj.getCharacterMaximumLength()) {
                    sb.append("\t@Length(max = ").append(obj.getCharacterMaximumLength()).append(",message = \"最大长度为").append(obj.getCharacterMaximumLength()).append("\")\n");
                }
            }
            sb.append("\tprivate ");
            sb.append(dataType.getJavaType());
            sb.append(" ");
            sb.append(obj.getModelObjName()).append(";\n\n");
        });

    }
}
