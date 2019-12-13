package com.wdk.general.core.web.controller;

import com.wdk.general.core.service.CommonFileService;
import com.wdk.general.core.service.ConfigService;
import com.wdk.general.core.service.GenerateService;
import com.wdk.general.core.service.PomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by wdk on 2019/12/12
 */
@Controller
@RequestMapping("generate")
public class GenerateController {


    private Logger logger = LoggerFactory.getLogger(GenerateController.class);

    @Autowired
    private GenerateService generateService;

    @Autowired
    private PomService pomService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private CommonFileService commonFileService;

    /**
     * 代码生成地址
     *
     * @param tableName
     * @return
     */
    @PostMapping(value = "init")
    public String init(String[] tableName) {
        System.out.println("###########################################################################");

        System.out.println(tableName.length);

        pomService.pom();
        configService.application("wdk-code-general-core-test");
        configService.applicationBranch("dev","com.wdk.test");
        configService.logBack("dev",null);

        generateService.init(tableName);

        commonFileService.mainApplication("WdkCodeGeneralCoreTest","com.wdk.test");

        return "tables/index";

    }
}
