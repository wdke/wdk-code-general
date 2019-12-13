package com.wdk.general.core.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by wdk on 2019/12/13
 */

@Controller
@RequestMapping("project/metadata")
public class ProjectMetadataController {

    private Logger logger= LoggerFactory.getLogger(ProjectMetadataController.class);


    @GetMapping("index")
    public String index(Model model){


        model.addAttribute("group","com.wdk");
        model.addAttribute("artifact","springboot-demo");
        model.addAttribute("type","Maven Project");
        model.addAttribute("language","java");
        model.addAttribute("packaging","jar");
        model.addAttribute("javaVersion","8");
        model.addAttribute("version","0.0.1-SNAPSHOT");
        model.addAttribute("name","springboot-demo");
        model.addAttribute("description","Demo project for Spring Boot");
        model.addAttribute("packages","com.wdk.springboot.demo");

        return "project_metadata/index";

    }
}
