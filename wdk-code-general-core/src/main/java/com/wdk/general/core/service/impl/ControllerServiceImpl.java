package com.wdk.general.core.service.impl;

import com.wdk.general.core.model.BaseParam;
import com.wdk.general.core.model.ParamGenerate;
import com.wdk.general.core.model.SchemaColumns;
import com.wdk.general.core.service.ControllerService;
import com.wdk.general.core.service.ParamCommonService;
import com.wdk.general.core.utils.StringConversionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * created by wdk on 2019/12/17
 */
@Service
public class ControllerServiceImpl implements ControllerService {


    @Autowired
    private ParamCommonService paramCommonService;

    /**
     * 删除方法
     *
     * @param baseParam
     * @return
     */
    @Override
    public String remove(BaseParam baseParam) {
        if (null == baseParam || null == baseParam.getKeys() || baseParam.getKeys().size() == 0) {

            return "";
        }
        ParamGenerate paramGenerate = paramCommonService.keyPathParam(baseParam.getKeys());

        StringBuilder sb = new StringBuilder();
        sb.append("/**\n")
                .append("\t * 移除方法\n")
                .append("\t *\n");
        for (SchemaColumns col : baseParam.getKeys()) {
            sb.append("\t * @param ").append(col.getModelObjName()).append("\n");
        }
        sb.append("\t * @param id\n")
                .append("\t * @return\n")
                .append("\t */\n")

                .append("\t@PostMapping(value = \"remove\", name = \"移除方法\")\n")
                .append("\tpublic ResponseVo<Integer> remove(")
                .append(paramGenerate.getArgs())
                .append(") {\n");
        //生成参数日志
        if (baseParam.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n");
            sb.append("\t\tlogger.info(\"remove start。param:【");
            for (int i = 0; i < baseParam.getKeys().size(); i++) {
                SchemaColumns col = baseParam.getKeys().get(i);
                if (i == 0) {
                    sb.append(col.getModelObjName()).append("->{}");
                } else {
                    sb.append(", ").append(col.getModelObjName()).append("->{}");
                }
            }
            sb.append("】\", ").append(paramGenerate.getValues()).append(");\n");
        }


        //纪录开始时间,作为计算方法用时
        sb.append("\t\t//纪录开始时间\n")
                .append("\t\tlong startTimes=System.currentTimeMillis();\n\n")
                .append("\t\t//调用业务逻辑\n")
                .append("\t\tInteger result=")
                .append(baseParam.getModelObjName())
                .append("Service.deleteByPrimaryKey(").append(paramGenerate.getValues()).append(";\n\n");

        //生成返回值日志
        if (baseParam.isLogger()) {
            sb.append("\t\t//打印方法开始参数\n")
                    .append("\t\tlogger.info(\"remove end。result->{},list use times->{}\",JSON.toJSONString(result),TimeUtils.format(System.currentTimeMillis()-startTimes));\n");
        }
        sb.append("\n")
                .append("\n")
                .append("\t\treturn new ResponseVo<>(ResponseStatusEnum.SUCCESS,result);\n")
                .append("\n")
                .append("\t}");
        return sb.toString();
    }
}
