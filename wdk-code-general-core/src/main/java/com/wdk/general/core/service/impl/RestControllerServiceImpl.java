package com.wdk.general.core.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.ParamCommonService;
import com.wdk.general.core.service.RestControllerService;
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
import java.util.Map;

/**
 * @author wdke
 * @date 2019/6/25
 */
@Service
public class RestControllerServiceImpl implements RestControllerService {

    private static Logger logger = LoggerFactory.getLogger(RestControllerServiceImpl.class);

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
                .append(packages).append(".web.controller;\n\n");

        //导入依赖包
        imports(controller, param, packages);

        //定义class名称
        controller.append("//").append(param.getTableComment()).append("页面控制类\n").append("@RestController\n")
                .append("@RequestMapping(\"api/")
                .append(param.getTableName().replaceAll("_", "/"))
                .append("\")\n")
                .append("public class ")
                .append(param.getModelName())
                .append("Controller {\n\n")
                .append("\t//定义生成logger对象\n")
                .append("\tprivate static Logger logger=LoggerFactory.getLogger(")
                .append(param.getModelName())
                .append("Controller.class);\n\n")
                .append("\t//").append(param.getTableComment()).append("逻辑处理对象\n")
                .append("\t@Autowired\n")
                .append("\tprivate ")
                .append(param.getModelName())
                .append("Service ")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Service;\n\n");

        //生成首页
        index(controller, param);


        //新增保存页面
        insert(controller, param);

        //更新页面
        update(controller, param);


        //删除方法
        remove(controller, param);


        //生成结束标记
        controller.append("\n}\n");


        String file = UserContext.current().getProjectServerRoot() + "/src/main/java/" + packages.replaceAll("\\.", "/") + "/web/controller/" + param.getModelName() + "Controller.java";

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
                .append("import org.springframework.ui.Model;\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import org.springframework.web.bind.annotation.*;\n")
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
                .append("\tpublic ResponseVo<PageInfo<").append(param.getModelName()).append(">> index(")
                .append(param.getModelName())
                .append("Args ").append(param.objName()).append(", PageParam pageParam){\n\n");

        //纪录开始时间,作为计算方法用时
        controller
                .append("\t\t//调用业务逻辑\n")
                .append("\t\tPageInfo<").append(param.getModelName()).append("> result = ")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Service.pageInfo(").append(param.objName()).append(", pageParam);\n\n");

        controller.append("\t\treturn new ResponseVo(ResponseStatusEnum.SUCCESS, result);\n")
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
                .append("\tpublic ResponseVo<Integer> insert(@RequestBody ")
                .append(param.getModelName())
                .append("Args ").append(param.objName()).append("){\n\n");

        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger").append(" result = ")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Service.insertSelective(").append(param.objName()).append(");\n\n");

        sb.append("\t\treturn new ResponseVo(ResponseStatusEnum.SUCCESS, result);\n")
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
                .append("\tpublic ResponseVo<Integer> update(@RequestBody ")
                .append(param.getModelName())
                .append("Args ").append(param.objName()).append("){\n\n");

        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger").append(" result = ")
                .append(ColumnsUtil.columns(param.getModelName(), "objName"))
                .append("Service.updateSelectiveByPrimaryKey(").append(param.objName()).append(");\n\n");

        sb.append("\t\treturn new ResponseVo(ResponseStatusEnum.SUCCESS, result);\n")
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
        Map<String, String> keyMap = paramCommonService.keyPathParam(baseParam);
        sb.append("\t/**\n")
                .append("\t * 移除方法\n")
                .append("\t *\n");
        for (SchemaColumns col : baseParam.getKeys()) {
            sb.append("\t * @param ").append(col.objName()).append("\n");
        }
        sb.append("\t * @return\n")
                .append("\t */\n")
                .append("\t@PostMapping(value = \"remove").append(keyMap.get("path")).append("\", name = \"移除方法\")\n")
                .append("\tpublic ResponseVo<Integer> remove(HttpServletRequest request")
                .append(StringUtils.isEmpty(keyMap.get("args")) ? "" : ", " + keyMap.get("args"))
                .append(") {\n");

        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger result = ")
                .append(ColumnsUtil.columns(baseParam.getModelName(), "objName"))
                .append("Service.deleteByPrimaryKey(").append(keyMap.get("value")).append(");\n\n");

        sb.append("\t\treturn new ResponseVo(ResponseStatusEnum.SUCCESS, result);\n")
                .append("\t}\n\n");
    }

}
