package com.wdk.general.core.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.common.constant.FilePathConstant;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.ParamGenerate;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.MvcControllerService;
import com.wdk.general.core.service.ParamCommonService;
import com.wdk.general.core.service.VerifyService;
import com.wdk.general.core.utils.StringConversionUtil;
import com.wdk.general.core.utils.DateUtils;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author wdke
 * @date 2019/6/25
 */
@Service
public class MvcControllerServiceImpl implements MvcControllerService {


    @Autowired
    private ParamCommonService paramCommonService;

    /**
     * 创建生成文件
     *
     * @param baseParam
     * @param packages
     */
    @Override
    public void createFile(BaseParam baseParam, String packages) {


        try {
            FileUtil.write(FilePathConstant.pagesPath(baseParam.getModelName()), init(baseParam, packages));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成controller
     *
     * @param baseParam
     * @param packages
     */
    @Override
    public String init(BaseParam baseParam, String packages) {
        StringBuilder sb = new StringBuilder();

        //生成包名文件
        sb.append("package ")
                .append(packages).append(".web.pages;\n\n");

        //导入依赖包
        imports(sb, baseParam.getModelName(), packages);

        //定义class名称
        sb.append("//").append(baseParam.getTableComment()).append("页面控制类\n").append("@Controller\n")
                .append("@RequestMapping(\"pages/")
                .append(baseParam.getTableName().replaceAll("_", "/"))
                .append("\")\n")
                .append("public class ")
                .append(baseParam.getModelName())
                .append("Pages {\n\n")
                .append("\t//定义生成logger对象\n")
                .append("\tprivate static Logger logger=LoggerFactory.getLogger(")
                .append(baseParam.getModelName())
                .append("Pages.class);\n\n")
                .append("\t//").append(baseParam.getTableComment()).append("逻辑处理对象\n")
                .append("\t@Autowired\n")
                .append("\tprivate ")
                .append(baseParam.getModelName())
                .append("Service ")
                .append(baseParam.getModelObjName())
                .append("Service;\n\n");

        //生成首页
        index(sb, baseParam.getModelName(), baseParam.getModelObjName());

        //新增页面路由
        insertPage(sb, baseParam.getModelObjName());


        //新增保存页面
        insert(sb, baseParam.getModelName(), baseParam.getModelObjName(), baseParam.getTableName().replaceAll("_", "/"));

        //更新页面路由
        updatePage(sb, baseParam.getModelName(), baseParam.getModelObjName(), baseParam.getKeys());

        //更新页面路由
        detailPage(sb, baseParam.getModelName(), baseParam.getModelObjName(), baseParam.getKeys());

        //更新页面
        update(sb, baseParam.getModelName(), baseParam.getModelObjName(), baseParam.getTableName().replaceAll("_", "/"));


        //删除方法
        remove(sb, baseParam.getModelName(), baseParam.getModelObjName(), baseParam.getKeys());


        //生成结束标记
        sb.append("\n}\n");

        return sb.toString();
    }

    /**
     * 导入包
     *
     * @param sb
     * @param modelName
     * @param packages
     */
    @Override
    public void imports(StringBuilder sb, String modelName, String packages) {
        sb.append("import ").append(packages).append(".service.").append(modelName).append("Service;\n")
                .append("import com.alibaba.druid.util.StringUtils;\n")
                .append("import javax.servlet.http.HttpServletRequest;\n")
                .append("import ").append(packages).append(".common.enums.ResponseStatusEnum;\n")
                .append("import ").append(packages).append(".common.model.ResponseVo;\n")
                .append("import com.alibaba.fastjson.JSON;\n")
                .append("import com.github.pagehelper.PageInfo;\n")
                .append("import ").append(packages).append(".common.model.PageParam;\n")
                .append("import ").append(packages).append(".storage.entity.").append(modelName).append(";\n")
                .append("import ").append(packages).append(".web.args.").append(modelName).append("Args;\n")
                .append("import ").append(packages).append(".web.vo.").append(modelName).append("Vo;\n")
                .append("import ").append(packages).append(".utils.TimeUtils;\n")
                .append("import org.slf4j.Logger;\n")
                .append("import org.slf4j.LoggerFactory;\n")
                .append("import org.springframework.stereotype.Controller;\n")
                .append("import org.springframework.ui.Model;\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import org.springframework.web.bind.annotation.*;\n")
                .append("\n\n\n");
    }

    /**
     * 进入主页方法
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     */
    @Override
    public void index(StringBuilder sb, String modelName, String modelObjName) {

        //生成service，实现类
        sb.append("\t/**\n")
                .append("\t* 首页接口\n")
                .append("\t*\n")
                .append("\t* @param ").append(modelObjName).append("Args\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@RequestMapping(value = \"\",name = \"首页接口\")\n")
                .append("\tpublic String index(")
                .append(modelName)
                .append("Args ").append(modelObjName).append("Args, PageParam pageParam, Model model){\n\n");

        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//调用业务逻辑\n")
                .append("\t\tPageInfo<").append(modelName).append("Vo> result = ")
                .append(modelObjName)
                .append("Service.pageInfo(").append(modelObjName).append("Args, pageParam);\n\n")
                .append("\t\t//数据返回jsp\n")
                .append("\t\tmodel.addAttribute(\"datas\", result);\n\n");

        sb.append("\t\treturn \"/")
                .append(modelObjName)
                .append("/index\";\n")
                .append("\t}\n\n");

    }

    /**
     * 新增方法
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param redirectPath
     */
    @Override
    public void insert(StringBuilder sb, String modelName, String modelObjName, String redirectPath) {

        //生成service，实现类
        sb.append("\t/**\n")
                .append("\t* 新增接口\n")
                .append("\t*\n")
                .append("\t* @param ").append(modelObjName).append("Args\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@PostMapping(value = \"insert\",name = \"新增接口\")\n")
                .append("\tpublic String insert(")
                .append(modelName)
                .append("Args ").append(modelObjName).append("Args){\n\n");

        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger").append(" result = ")
                .append(modelObjName)
                .append("Service.insertSelective(").append(modelObjName).append("Args);\n\n");

        sb.append("\t\treturn \"redirect:/")
                .append(redirectPath)
                .append("\";\n")
                .append("\t}\n\n");
    }

    /**
     * 批量新增方法
     *
     * @param controller
     * @param param
     * @param packages
     */
    @Override
    public void batchInsert(StringBuilder controller, BaseParam param, String packages) {

    }

    /**
     * 进入新增页面
     *
     * @param sb
     * @param modelObjName
     */
    @Override
    public void insertPage(StringBuilder sb, String modelObjName) {


        page(sb, "insert", modelObjName, "新增");
    }

    /**
     * 进入更新页面
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param keys
     */
    @Override
    public void updatePage(StringBuilder sb, String modelName, String modelObjName, List<SchemaColumns> keys) {
        //没有key就不生成更新方法
        if (keys == null || keys.size() == 0) {
            return;
        }

        ParamGenerate paramGenerate = paramCommonService.keyPathParam(keys);
        //生成service，实现类
        sb.append("\t/**\n")
                .append("\t* 进入更新页面\n")
                .append("\t*\n");
        for (SchemaColumns col : keys) {
            sb.append("\t* @param ").append(col.getModelObjName()).append("\n");
        }
        sb.append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@GetMapping(value = \"update/pages/").append(paramGenerate.getPath()).append("\",name = \"进入更新页面\")\n")
                .append("\tpublic String updatePage(")
                .append(paramGenerate.getPathArgs())
                .append(", Model model){\n\n");

        sb.append("\t\t//根据主键查询数据\n")
                .append("\t\t").append(modelName).append("Vo result = ").append(modelObjName)
                .append("Service.selectByPrimaryKey(")
                .append(paramGenerate.getValues())
                .append(");\n\n")
                .append("\t\t//数据返回html\n")
                .append("\t\tmodel.addAttribute(\"data\", result);\n\n");

        sb.append("\t\t//返回页面路径\n")
                .append("\t\treturn \"/")
                .append(modelObjName)
                .append("/update\";\n")
                .append("\t}\n\n");
//        page(sb, "update", param, "更新");
    }
    /**
     * 进入详情页面
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param keys
     */
    @Override
    public void detailPage(StringBuilder sb, String modelName, String modelObjName, List<SchemaColumns> keys) {
        //没有key就不生成详情方法
        if (keys == null || keys.size() == 0) {
            return;
        }

        ParamGenerate paramGenerate = paramCommonService.keyPathParam(keys);
        //生成service，实现类
        sb.append("\t/**\n")
                .append("\t* 进入详情页面\n")
                .append("\t*\n");
        for (SchemaColumns col : keys) {
            sb.append("\t* @param ").append(col.getModelObjName()).append("\n");
        }
        sb.append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@GetMapping(value = \"detail/pages/").append(paramGenerate.getPath()).append("\",name = \"进入更新页面\")\n")
                .append("\tpublic String detailPage(")
                .append(paramGenerate.getPathArgs())
                .append(", Model model){\n\n");

        sb.append("\t\t//根据主键查询数据\n")
                .append("\t\t").append(modelName).append("Vo result = ").append(modelObjName)
                .append("Service.selectByPrimaryKey(")
                .append(paramGenerate.getValues())
                .append(");\n\n")
                .append("\t\t//数据返回html\n")
                .append("\t\tmodel.addAttribute(\"data\", result);\n\n");

        sb.append("\t\t//返回页面路径\n")
                .append("\t\treturn \"/")
                .append(modelObjName)
                .append("/detail\";\n")
                .append("\t}\n\n");
//        page(sb, "update", param, "更新");
    }
    /**
     * 更新数据
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param redirectPath
     */
    @Override
    public void update(StringBuilder sb, String modelName, String modelObjName, String redirectPath) {

        //生成service，实现类
        sb.append("\t/**\n")
                .append("\t* 更新接口\n")
                .append("\t*\n")
                .append("\t* @param ").append(modelObjName).append("Args\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@PostMapping(value = \"update\",name = \"更新接口\")\n")
                .append("\tpublic String update(")
                .append(modelName)
                .append("Args ").append(modelObjName).append("Args){\n\n");

        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger").append(" result = ")
                .append(modelObjName)
                .append("Service.updateSelectiveByPrimaryKey(").append(modelObjName).append("Args);\n\n");

        sb.append("\t\treturn \"redirect:/")
                .append(redirectPath)
                .append("\";\n")
                .append("\t}\n\n");
    }

    /**
     * 删除方法
     *
     * @param sb
     * @param modelName
     * @param modelObjName
     * @param keys
     */
    @Override
    public void remove(StringBuilder sb, String modelName, String modelObjName, List<SchemaColumns> keys) {
        if (null == keys || keys.size() == 0) {
            return;
        }
        ParamGenerate paramGenerate = paramCommonService.keyPathParam(keys);
        sb.append("\t/**\n")
                .append("\t * 移除方法\n")
                .append("\t *\n");
        for (SchemaColumns col : keys) {
            sb.append("\t * @param ").append(col.getModelObjName()).append("\n");
        }
        sb.append("\t * @return\n")
                .append("\t */\n")
                .append("\t@PostMapping(value = \"remove/").append(paramGenerate.getPath()).append("\", name = \"移除方法\")\n")

                .append("\t@ResponseBody\n")
                .append("\tpublic ResponseVo<Integer> remove(")
                .append(paramGenerate.getPathArgs())
                .append(") {\n");

        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger result = ")
                .append(modelObjName)
                .append("Service.deleteByPrimaryKey(").append(paramGenerate.getValues()).append(");\n\n");

        sb.append("\n")
                .append("\n")
                .append("\t\treturn new ResponseVo<>(ResponseStatusEnum.SUCCESS, result);\n")
                .append("\n")
                .append("\t}");
    }

    /**
     * 通用页面路由
     *
     * @param sb
     * @param root
     * @param modelObjName
     * @param title
     */
    @Override
    public void page(StringBuilder sb, String root, String modelObjName, String title) {

        sb.append("\t/**\n")
                .append("\t * ").append(title).append("页面路由\n")
                .append("\t *\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@GetMapping(value = \"").append(root).append("/pages\", name = \"").append(title).append("页面路由\")\n")
                .append("\tpublic String ").append(root).append("Page() {\n\n")
                .append("\t\treturn \"/").append(modelObjName).append("/").append(root).append("\";\n")
                .append("\t}\n\n");

    }


}
