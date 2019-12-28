package com.wdk.general.core.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.MvcControllerService;
import com.wdk.general.core.service.ParamCommonService;
import com.wdk.general.core.service.VerifyService;
import com.wdk.general.core.utils.ColumnsUtil;
import com.wdk.general.core.utils.DateUtils;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author wdke
 * @date 2019/6/25
 */
@Service
public class MvcControllerServiceImpl implements MvcControllerService {

    private static Logger logger = LoggerFactory.getLogger(MvcControllerServiceImpl.class);

    @Autowired
    private VerifyService verifyService;


    @Autowired
    private ParamCommonService paramCommonService;

    /**
     * 生成controller
     *
     * @param param
     * @param packages
     */
    @Override
    public void init(BaseParam param, String packages) {
        StringBuilder controller = new StringBuilder();

        //生成包名文件
        controller.append("package ")
                .append(packages).append(".web.pages;\n\n");

        //导入依赖包
        imports(controller, param, packages);

        //定义class名称
        controller.append("//").append(param.getTableComment()).append("页面控制类\n").append("@Controller\n")
                .append("@RequestMapping(\"pages/")
                .append(param.getTableName().replaceAll("_", "/"))
                .append("\")\n")
                .append("public class ")
                .append(param.getModelName())
                .append("Pages {\n\n")
                .append("\t//定义生成logger对象\n")
                .append("\tprivate static Logger logger=LoggerFactory.getLogger(")
                .append(param.getModelName())
                .append("Pages.class);\n\n")
                .append("\t//").append(param.getTableComment()).append("逻辑处理对象\n")
                .append("\t@Autowired\n")
                .append("\tprivate ")
                .append(param.getModelName())
                .append("Service ")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Service;\n\n");

        //生成首页
        index(controller, param);

        //新增页面路由
        insertPage(controller, param);


        //新增保存页面
        insert(controller, param);

        //更新页面路由
        updatePage(controller, param);

        //更新页面
        update(controller, param);


        //删除方法
        remove(controller, param);


        //生成结束标记
        controller.append("\n}\n");


        String file = UserContext.current().getProjectServerRoot() + "/src/main/java/" + packages.replaceAll("\\.", "/") + "/web/pages/" + param.getModelName() + "Pages.java";

        try {
            FileUtil.write(file, controller.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 导入包
     *
     * @param controller
     * @param param
     * @param packages
     */
    @Override
    public void imports(StringBuilder controller, BaseParam param, String packages) {
        controller.append("import ").append(packages).append(".service.").append(param.getModelName()).append("Service;\n")
                .append("import com.alibaba.druid.util.StringUtils;\n")
                .append("import javax.servlet.http.HttpServletRequest;\n")
                .append("import ").append(packages).append(".common.enums.ResponseStatusEnum;\n")
                .append("import ").append(packages).append(".common.model.ResponseVo;\n")
                .append("import com.alibaba.fastjson.JSON;\n")
                .append("import com.github.pagehelper.PageInfo;\n")
                .append("import ").append(packages).append(".common.model.PageParam;\n")
                .append("import ").append(packages).append(".model.").append(param.getModelName()).append(";\n")
                .append("import ").append(packages).append(".web.args.").append(param.getModelName()).append("Args;\n")
                .append("import ").append(packages).append(".utils.TimeUtils;\n")
                .append("import org.slf4j.Logger;\n")
                .append("import org.slf4j.LoggerFactory;\n")
                .append("import org.springframework.stereotype.Controller;\n")
                .append("import org.springframework.ui.Model;\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import org.springframework.web.bind.annotation.PostMapping;\n")
                .append("import org.springframework.web.bind.annotation.RequestMapping;\n")
                .append("import org.springframework.web.bind.annotation.ResponseBody;\n")
                .append("import org.springframework.web.bind.annotation.GetMapping;\n")
                .append("\n\n\n");
    }

    /**
     * 进入主页方法
     *
     * @param controller
     * @param param
     */
    @Override
    public void index(StringBuilder controller, BaseParam param) {

        //生成service，实现类
        controller.append("\t/**\n")
                .append("\t* 首页接口\n")
                .append("\t*\n")
                .append("\t* @param ").append(param.objName()).append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@RequestMapping(value = \"\",name = \"首页接口\")\n")
                .append("\tpublic String index(")
                .append(param.getModelName())
                .append("Args ").append(param.objName()).append(", PageParam pageParam, Model model){\n\n");
        //生成参数日志
        if (param.isLogger()) {
            controller.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"index start。param:【").append(param.objName()).append("->{},pageParam->{}】").append("\", JSON.toJSONString(").append(param.objName()).append("), JSON.toJSONString(pageParam));\n\n");
        }
        //纪录开始时间,作为计算方法用时
        controller.append("\t\t//纪录开始时间\n")
                .append("\t\tlong startTimes = System.currentTimeMillis();\n\n")
                .append("\t\t//调用业务逻辑\n")
                .append("\t\tPageInfo<").append(param.getModelName()).append("> result = ")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Service.pageInfo(").append(param.objName()).append(", pageParam);\n\n")
                .append("\t\t//数据返回jsp\n")
                .append("\t\tmodel.addAttribute(\"datas\", result);\n\n");

        //生成返回值日志
        if (param.isLogger()) {
            controller.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"index end。result:【data->{},times->{}】\", JSON.toJSONString(result), TimeUtils.format(System.currentTimeMillis() - startTimes));\n");
        }
        controller.append("\t\treturn \"/")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("/index\";\n")
                .append("\t}\n\n");

    }

    /**
     * 新增方法
     *
     * @param sb
     * @param param
     */
    @Override
    public void insert(StringBuilder sb, BaseParam param) {

        //生成service，实现类
        sb.append("\t/**\n")
                .append("\t* 新增接口\n")
                .append("\t*\n")
                .append("\t* @param ").append(param.objName()).append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@PostMapping(value = \"insert\",name = \"新增接口\")\n")
                .append("\tpublic String insert(")
                .append(param.getModelName())
                .append("Args ").append(param.objName()).append("){\n\n");
        //生成参数日志
        if (param.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"insert start。param:【").append(param.objName()).append("->{}】").append("\", JSON.toJSONString(").append(param.objName()).append("));\n\n");
        }
        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//纪录开始时间\n")
                .append("\t\tlong startTimes = System.currentTimeMillis();\n\n")
                .append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger").append(" result = ")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Service.insertSelective(").append(param.objName()).append(");\n\n");

        //生成返回值日志
        if (param.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"insert end。result:【data->{},times->{}】\", JSON.toJSONString(result), TimeUtils.format(System.currentTimeMillis() - startTimes));\n");
        }
        sb.append("\t\treturn \"redirect:/")
                .append(param.getTableName().replaceAll("_", "/"))
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
     * @param param
     */
    @Override
    public void insertPage(StringBuilder sb, BaseParam param) {


        page(sb, "insert", param, "新增");
    }

    /**
     * 进入更新页面
     *
     * @param sb
     * @param param
     */
    @Override
    public void updatePage(StringBuilder sb, BaseParam param) {

        //生成service，实现类
        sb.append("\t/**\n")
                .append("\t* 进入更新页面\n")
                .append("\t*\n")
                .append("\t* @param ").append(paramCommonService.keyMapperParam(param)).append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@GetMapping(value = \"update/pages\",name = \"进入更新页面\")\n")
                .append("\tpublic String updatePage(")
                .append(paramCommonService.keyParam(param))
                .append(", Model model){\n\n");
        //生成参数日志
        if (param.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"selectByPrimaryKey start。param【").append(paramCommonService.keyMapperParam(param).replaceAll(",", "->{}")).append("->{}").append("】\", ").append(paramCommonService.keyMapperParam(param)).append(");\n\n");
        }
        //纪录开始时间,作为计算方法用时
        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//纪录开始时间\n")
                .append("\t\tlong startTimes=System.currentTimeMillis();\n\n")
                .append("\t\t//根据主键查询数据\n")
                .append("\t\t").append(param.getModelName()).append(" result = ").append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Service.selectByPrimaryKey(")
                .append(paramCommonService.keyMapperParam(param))
                .append(");\n\n")
                .append("\t\t//数据返回html\n")
                .append("\t\tmodel.addAttribute(\"data\", result);\n\n");

        //生成返回值日志
        if (param.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"selectByPrimaryKey end。result【data->{},times->{}】\", result, TimeUtils.format(System.currentTimeMillis() - startTimes));\n\n");
        }
        sb.append("\t\t//返回页面路径\n")
                .append("\t\treturn \"/")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("/update\";\n")
                .append("\t}\n\n");
//        page(sb, "update", param, "更新");
    }

    /**
     * 更新数据
     * @param sb
     * @param param
     */
    @Override
    public void update(StringBuilder sb, BaseParam param) {

        //生成service，实现类
        sb.append("\t/**\n")
                .append("\t* 更新接口\n")
                .append("\t*\n")
                .append("\t* @param ").append(param.objName()).append("\n")
                .append("\t* @author ").append(param.getAuthor()).append("\n")
                .append("\t* @date ").append(DateUtils.nowFormat("yyyy/MM/dd HH:mm")).append("\n")
                .append("\t*/\n")
                .append("\t@PostMapping(value = \"update\",name = \"更新接口\")\n")
                .append("\tpublic String update(")
                .append(param.getModelName())
                .append("Args ").append(param.objName()).append("){\n\n");
        //生成参数日志
        if (param.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"update start。param:【").append(param.objName()).append("->{}】").append("\", JSON.toJSONString(").append(param.objName()).append("));\n\n");
        }
        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//纪录开始时间\n")
                .append("\t\tlong startTimes = System.currentTimeMillis();\n\n")
                .append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger").append(" result = ")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Service.updateSelectiveByPrimaryKey(").append(param.objName()).append(");\n\n");

        //生成返回值日志
        if (param.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"update end。result:【data->{},times->{}】\", JSON.toJSONString(result), TimeUtils.format(System.currentTimeMillis() - startTimes));\n");
        }
        sb.append("\t\treturn \"redirect:/")
                .append(param.getTableName().replaceAll("_", "/"))
                .append("\";\n")
                .append("\t}\n\n");
    }

    /**
     * 删除方法
     *
     * @param baseParam
     * @return
     */
    @Override
    public void remove(StringBuilder sb, BaseParam baseParam) {
        if (null == baseParam || null == baseParam.getKeys() || baseParam.getKeys().size() == 0) {

            return;
        }
        sb.append("\t/**\n")
                .append("\t * 移除方法\n")
                .append("\t *\n");
        for (SchemaColumns col : baseParam.getKeys()) {
            sb.append("\t * @param ").append(col.objName()).append("\n");
        }
        sb.append("\t * @return\n")
                .append("\t */\n")
                .append("\t@PostMapping(value = \"remove\", name = \"移除方法\")\n")
                .append("\t@ResponseBody\n")
                .append("\tpublic ResponseVo<Integer> remove(HttpServletRequest request")
                .append(StringUtils.isEmpty(baseParam.keyTypeAndParam()) ? "" : ", " + baseParam.keyTypeAndParam())
                .append(") {\n");
        //生成参数日志
        if (baseParam.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n");
            sb.append("\t\tlogger.info(\"remove start。param:【");
            for (int i = 0; i < baseParam.getKeys().size(); i++) {
                SchemaColumns col = baseParam.getKeys().get(i);
                if (i == 0) {
                    sb.append(col.objName()).append("->{}");
                } else {
                    sb.append(", ").append(col.objName()).append("->{}");
                }
            }
            sb.append("】\", ").append(baseParam.keyParam()).append(");\n\n");
        }

        verifyService.verify(sb, baseParam.getKeys());

        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//纪录开始时间\n")
                .append("\t\tlong startTimes = System.currentTimeMillis();\n\n")
                .append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger result = ")
                .append(ColumnsUtil.columns(baseParam.getModelName(), "objName"))
                .append("Service.deleteByPrimaryKey(").append(baseParam.keyParam()).append(");\n\n");

        //生成返回值日志
        if (baseParam.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"remove end。result【data->{},times-{}】\", result, TimeUtils.format(System.currentTimeMillis() - startTimes));\n");
        }
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
     * @param baseParam
     * @param title
     */
    @Override
    public void page(StringBuilder sb, String root, BaseParam baseParam, String title) {

        sb.append("\t/**\n")
                .append("\t * ").append(title).append("页面路由\n")
                .append("\t *\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@GetMapping(value = \"").append(root).append("/pages\", name = \"").append(title).append("页面路由\")\n")
                .append("\tpublic String ").append(root).append("Page() {\n\n")
                .append("\t\t//生成日志\n")
                .append("\t\tlogger.info(\"").append(root).append("Page start \");\n\n")
                .append("\t\treturn \"/").append(baseParam.objName()).append("/").append(root).append("\";\n")
                .append("\t}\n\n");

    }


}
