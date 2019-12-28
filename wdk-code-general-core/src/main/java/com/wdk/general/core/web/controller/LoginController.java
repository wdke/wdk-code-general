package com.wdk.general.core.web.controller;

import com.wdk.general.core.storage.redis.RedisStringDao;
import com.wdk.general.core.web.param.LoginParam;
import com.wdk.permissions.api.args.UserArgs;
import com.wdk.permissions.api.http.LoginApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LoginApi loginApi;


    @Autowired
    private RedisStringDao redisStringDao;

    /**
     * 进入登陆页面
     *
     * @return
     */
    @GetMapping(value = "tologin", name = "前往登陆页面")
    public String tologin(Model model) {

        model.addAttribute("username","username");
        model.addAttribute("password","password");

        return "login";
    }


    /**
     * 登陆验证
     *
     * @return
     */
    @PostMapping(value = "login", name = "登陆验证")
    public String login(LoginParam loginParam, Model model, HttpServletRequest req) {

        if(StringUtils.isEmpty(loginParam.getUsername())||StringUtils.isEmpty(loginParam.getPassword())){

            model.addAttribute("username",loginParam.getUsername());
            return "login";
        }

//        UserArgs userArgs=new UserArgs();UserArgs
//        userArgs.setUsername(loginParam.getUsername());
//        userArgs.setPassord(loginParam.getPassword());
//        String login = loginApi.login(userArgs);

        req.getSession().setAttribute("username",loginParam.getUsername());
        model.addAttribute("userId",1000);
        model.addAttribute("host", "49.233.195.134");
        model.addAttribute("dbname", "wdk_test");
        model.addAttribute("dbuser", "root");
        model.addAttribute("dbpassword", "root1234");
        model.addAttribute("dbport", "3306");
        return "redirect:/index";
    }


    @GetMapping("index")
    public String index(){


        return "index";

    }

}
