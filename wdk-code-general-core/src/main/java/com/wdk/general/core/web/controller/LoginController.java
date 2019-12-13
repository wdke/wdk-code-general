package com.wdk.general.core.web.controller;

import com.wdk.general.core.web.param.LoginParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * created by wdk on 2019/12/12
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 进入登陆页面
     *
     * @return
     */
    @GetMapping(value = "login", name = "前往登陆页面")
    public String index(Model model) {

        model.addAttribute("username","username");
        model.addAttribute("password","password");

        return "login";
    }


    /**
     * 登陆验证
     *
     * @return
     */
    @PostMapping(value = "index", name = "登陆验证")
    public String submit(LoginParam loginParam, Model model, HttpServletRequest req) {

        if(StringUtils.isEmpty(loginParam.getUsername())||StringUtils.isEmpty(loginParam.getPassword())){

            model.addAttribute("username",loginParam.getUsername());
            return "login";
        }

        req.getSession().setAttribute("userInfo",loginParam);

        model.addAttribute("userId",1000);
        model.addAttribute("host", "49.233.195.134");
        model.addAttribute("dbname", "wdk_test");
        model.addAttribute("dbuser", "root");
        model.addAttribute("dbpassword", "root1234");
        model.addAttribute("dbport", "3306");
        return "tables/index";
    }

}
