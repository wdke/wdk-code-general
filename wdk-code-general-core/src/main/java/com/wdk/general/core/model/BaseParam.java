package com.wdk.general.core.model;

import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.utills.ColumnsUtil;
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

    private List<SchemaColumns> columns;

    private List<SchemaColumns> keys;

    private Boolean dbTables=false;

    //拥有者
    private String author;
    //是否打印logger
    private boolean logger;

    public List<SchemaColumns> getKeys() {
        if( null==keys||keys.isEmpty()){
            if(null!=columns&&!columns.isEmpty()){
                keys= columns.stream().filter(obj->"PRI".equals(obj.getColumnKey())).collect(Collectors.toList());
                return keys;
            }
            return new ArrayList<>();
        }
        return keys;
    }

    public String getModelName() {
        if(StringUtils.isEmpty(modelName)
                &&!StringUtils.isEmpty(tableName)){

            String column = ColumnsUtil.columns(tableName, "getter");
            return column;

        }
        return modelName;
    }

    public BaseParam() {
        this.author = "jack";
        this.logger=true;
    }
    public BaseParam(String author) {
        this.author = author;
        this.logger=true;
    }

    public BaseParam(String author, boolean logger) {
        this.author = author;
        this.logger = logger;
    }
}
