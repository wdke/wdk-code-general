package com.wdk.general.core.web.controller;

import com.alibaba.fastjson.JSON;
import com.wdk.general.core.model.DbMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * created by wdk on 2019/12/11
 */
@Controller
@RequestMapping("tables")
public class TablesController {


    /**
     * 数据库地址页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("userId",1000);
        model.addAttribute("host", "49.233.195.134");
        model.addAttribute("dbname", "wdk_test");
        model.addAttribute("dbuser", "root");
        model.addAttribute("dbpassword", "root1234");
        model.addAttribute("dbport", "3306");

        return "tables/index";
    }


    /**
     * 登陆数据库
     * @param model
     * @param dbMessage
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, DbMessage dbMessage) {

        System.out.println(JSON.toJSONString(dbMessage));




        return "tables/index";
    }

}
