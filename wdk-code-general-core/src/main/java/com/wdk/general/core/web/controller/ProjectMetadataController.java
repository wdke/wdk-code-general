package com.wdk.general.core.web.controller;

import com.wdk.general.core.common.constant.RedisConstant;
import com.wdk.general.core.model.ProjectMetadata;
import com.wdk.general.core.storage.redis.RedisStringDao;
import com.wdk.general.core.web.Interceptor.UserContext;
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

        ProjectMetadata projectMetadata = (ProjectMetadata) redisStringDao.get(UserContext.current().getUsername());

        if (projectMetadata == null) {
            projectMetadata = new ProjectMetadata();
        }

        model.addAttribute("pm_", projectMetadata);

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

        redisStringDao.set("pm_" + UserContext.current().getUsername(), projectMetadata, RedisConstant.PROJECT_TIME);

        model.addAttribute("pm_", projectMetadata);

        return "project_metadata/index";

    }

}
