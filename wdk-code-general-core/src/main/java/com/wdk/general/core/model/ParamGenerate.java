package com.wdk.general.core.model;

import lombok.Data;

/**
 * created by wdk on 2019/12/30
 */

@Data
public class ParamGenerate {

    //路径参数
    private String path;
    //方法参数
    private String args;
    //方法传值参数
    private String values;
    //方法路径参数
    private String pathArgs;
}
