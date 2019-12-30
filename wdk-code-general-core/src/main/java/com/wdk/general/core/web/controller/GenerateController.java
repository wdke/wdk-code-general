package com.wdk.general.core.web.controller;

import com.wdk.general.core.dao.SchemaTablesDao;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.model.ProjectMetadata;
import com.wdk.general.core.model.Tables;
import com.wdk.general.core.service.CommonFileService;
import com.wdk.general.core.service.ConfigService;
import com.wdk.general.core.service.GenerateService;
import com.wdk.general.core.service.PomService;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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


    @Autowired
    private SchemaTablesDao schemaTablesDao;

    /**
     * 代码生成地址
     *
     * @param tableName
     * @return
     */
    @PostMapping(value = "init")
    public String init(String[] tableName) {
        System.out.println("###########################################################################");


        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        System.out.println(tableName.length);

        pomService.pom();
        pomService.serverPom();
        configService.application(projectMetadata.getName());
        configService.applicationBranch("dev", projectMetadata.getPackages());
        configService.applicationBranch("prod", projectMetadata.getPackages());
        configService.applicationBranch("test", projectMetadata.getPackages());
        configService.logBack();

        generateService.init(tableName);

        commonFileService.all(projectMetadata.getProJectJavaName(), projectMetadata.getPackages());

        return "general/index";

    }


    /**
     * 配置数据库生成
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String save(Model model, HttpServletRequest request) {
        logger.info("保存数据源信息");

        List<Tables> list = schemaTablesDao.list();
//
        model.addAttribute("list", list);

        return "general/index";
    }

}
