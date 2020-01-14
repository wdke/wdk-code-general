package com.wdk.general.core.model;

import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.common.enums.JdbcTypeEnums;
import com.wdk.general.core.utils.StringConversionUtil;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wdke
 * @date 2019/5/8
 */
@Data
public class BaseParam {


    @NotBlank(message = "cannot be empoty")
    private String tableSchema;

    @NotBlank(message = "cannot be empoty")
    private String tableName;


    private String tableComment;

    //    @NotBlank(message = "cannot be empoty")
    private String modelName;

    private String modelObjName;

    private List<SchemaColumns> columns;

    private List<SchemaColumns> keys;

    private Boolean dbTables = false;

    //拥有者
    private String author;

    //是否打印logger
    private boolean logger;


    private boolean mybatisCore = true;


    public BaseParam() {
        this.author = "jack";
        this.logger = true;
    }

    public BaseParam(String author) {
        this.author = author;
        this.logger = true;
    }

    public BaseParam(String author, boolean logger) {
        this.author = author;
        this.logger = logger;
    }


}
