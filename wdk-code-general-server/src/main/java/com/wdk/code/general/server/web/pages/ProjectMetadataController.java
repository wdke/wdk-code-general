package com.wdk.code.general.server.web.pages;

import com.alibaba.fastjson.JSON;
import com.wdk.code.general.server.redis.RedisStringDao;
import com.wdk.code.general.server.service.ProjectMetadataService;
import com.wdk.code.general.server.web.args.ProjectMetadataArgs;
import com.wdk.code.general.server.web.vo.ProjectMetadataVo;
import com.wdk.general.core.common.constant.RedisConstant;
import com.wdk.general.core.common.model.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * created by wdk on 2019/12/13
 */

@Controller
@RequestMapping("project/metadata")
public class ProjectMetadataController {

    private Logger logger = LoggerFactory.getLogger(ProjectMetadataController.class);

    @Autowired
    private RedisStringDao redisStringDao;


    @Autowired
    private ProjectMetadataService projectMetadataService;

    /**
     * 进入创建项目目录
     *
     * @param model
     * @return
     */
    @GetMapping("index")
    public String index(Model model) {


        ProjectMetadataArgs projectMetadataArgs = new ProjectMetadataArgs();
        projectMetadataArgs.setUserId(UserContext.current().getUserId());
        List<ProjectMetadataVo> list = projectMetadataService.list(projectMetadataArgs);
        model.addAttribute("list", list);

        ProjectMetadataArgs projectMetadata = JSON.parseObject(redisStringDao.get("pm_" + UserContext.current().getUsername()), ProjectMetadataArgs.class);

        if (projectMetadata == null) {
            projectMetadata = new ProjectMetadataArgs();
            if (list.size() > 0) {
                BeanUtils.copyProperties(list.get(0), projectMetadata);
            }
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
    public String save(Model model, ProjectMetadataArgs projectMetadata, HttpServletRequest request) {

        redisStringDao.set("pm_" + UserContext.current().getUsername(), JSON.toJSONString(projectMetadata), RedisConstant.PROJECT_TIME);

        projectMetadata.setUserId(UserContext.current().getUserId());
        if (null == projectMetadata.getPoint()) {
            projectMetadata.setPoint(8080);
        }
        projectMetadataService.insertSelective(projectMetadata);

        model.addAttribute("pm", projectMetadata);

        return "project_metadata/index";

    }

}
