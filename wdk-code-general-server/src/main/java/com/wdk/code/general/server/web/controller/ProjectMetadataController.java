package com.wdk.code.general.server.web.controller;

import com.alibaba.fastjson.JSON;
import com.wdk.code.general.server.redis.RedisStringDao;
import com.wdk.general.core.common.constant.RedisConstant;
import com.wdk.general.core.common.model.UserContext;
import com.wdk.general.core.model.ProjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * created by wdk on 2019/12/13
 */

@Controller
@RequestMapping("project/metadata")
public class ProjectMetadataController {

    private Logger logger = LoggerFactory.getLogger(ProjectMetadataController.class);

    @Autowired
    private RedisStringDao redisStringDao;

    /**
     * 进入创建项目目录
     *
     * @param model
     * @return
     */
    @GetMapping("index")
    public String index(Model model) {

        ProjectMetadata projectMetadata = JSON.parseObject( redisStringDao.get(UserContext.current().getUsername()),ProjectMetadata.class);

        if (projectMetadata == null) {
            projectMetadata = new ProjectMetadata();
            projectMetadata.setGroup("com.wdk");
            projectMetadata.setArtifact("wordpress");
            projectMetadata.setType("Maven Project");
            projectMetadata.setLanguage("java");
            projectMetadata.setPackaging("jar");
            projectMetadata.setJavaVersion("8");
            projectMetadata.setVersion("0.0.1-SNAPSHOT");
            projectMetadata.setName("wordpress");
            projectMetadata.setDescription("Demo project for Spring Boot");
            projectMetadata.setPackages("com.wdk.wordpress");
        }

        model.addAttribute("pm", projectMetadata);

        return "project_metadata/index";

    }

    /**
     * 项目信息存储
     *
     * @param model
     * @param projectMetadata
     * @param request
     * @return
     */
    @PostMapping("save")
    public String save(Model model, ProjectMetadata projectMetadata, HttpServletRequest request) {

        redisStringDao.set("pm_" + UserContext.current().getUsername(), JSON.toJSONString(projectMetadata), RedisConstant.PROJECT_TIME);

        model.addAttribute("pm", projectMetadata);

        return "project_metadata/index";

    }

}
