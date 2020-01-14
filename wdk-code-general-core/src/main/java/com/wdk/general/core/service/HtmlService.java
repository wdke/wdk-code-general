package com.wdk.general.core.service;

import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.model.Tables;

import java.util.List;
import java.util.Map;

/**
 * created by wdk on 2019/12/17
 */
public interface HtmlService {


    /**
     * 生成所有的文件
     *
     * @param baseParam
     */
    void all(BaseParam baseParam);


    /**
     * 菜单页面
     *
     * @param tables
     * @return
     */
    String menus(List<Tables> tables);


    /**
     * 生成主页
     *
     * @param baseParam
     * @return
     */
    String index(BaseParam baseParam);


    /**
     * 生成新增页面
     *
     * @param baseParam
     * @return
     */
    String insert(BaseParam baseParam);


    /**
     * 生成更新页面
     *
     * @param baseParam
     * @return
     */
    String update(BaseParam baseParam);


    /**
     * 生成详情页面
     *
     * @param baseParam
     * @return
     */
    String detail(BaseParam baseParam);


    /**
     * 生成操作页面
     *
     * @param baseParam
     * @param defaultValue
     * @param path
     * @param tags
     * @return
     */
    String operation(BaseParam baseParam, Boolean defaultValue, String path, String tags);


    /**
     * html head部分
     *
     * @param sb
     * @param title
     */
    void head(StringBuilder sb, String title);


    /**
     * 表单信息
     *
     * @param sb
     * @param baseParam
     * @param update
     * @param savePath
     */
    void formByTable(StringBuilder sb, BaseParam baseParam, Boolean update, String savePath);


    /**
     * 表单信息
     *
     * @param sb
     * @param baseParam
     * @param update
     * @param savePath
     */
    void formByDiv(StringBuilder sb, BaseParam baseParam, Boolean update, String savePath);


    /**
     * 列表
     *
     * @param sb
     * @param baseParam
     * @param operation
     */
    void table(StringBuilder sb, BaseParam baseParam, Boolean operation);


    /**
     * 详情信息
     *
     * @param sb
     * @param baseParam
     */
    void detailByDiv(StringBuilder sb, BaseParam baseParam);


    /**
     * 参数拼接
     *
     * @param keys
     */
    Map<String,String> args(List<SchemaColumns> keys);


}
