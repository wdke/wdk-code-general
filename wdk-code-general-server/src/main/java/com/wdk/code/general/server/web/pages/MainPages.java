package com.wdk.code.general.server.web.pages;

import com.wdk.code.general.server.service.LoginService;
import com.wdk.code.general.server.service.SysUserService;
import com.wdk.code.general.server.web.args.SysUserArgs;
import com.wdk.general.core.common.model.LoginParam;
import com.wdk.general.core.common.model.ResponseVo;
import com.wdk.general.core.utils.PasswordUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("main")
public class MainPages {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private LoginService loginService;

    /**
     * 进入后台管理菜单
     *
     * @return
     */
    @RequestMapping(value = "{menus}", name = "进入后台管理菜单")
    public String menus(@PathVariable("menus") String menus) {
        return menus;
    }


    /**
     * 登陆验证
     *
     * @return
     */
    @PostMapping(value = "login/check", name = "登陆验证")
    public String login(@Valid LoginParam loginParam, Model model, HttpServletRequest req) {

        if (StringUtils.isEmpty(loginParam.getUsername()) || StringUtils.isEmpty(loginParam.getPassword())) {

            model.addAttribute("username", loginParam.getUsername());
            //返回参数
            model.addAttribute("msg", "用户名和密码不能为空。");
            return "login";
        }
        //判断登陆
        ResponseVo<String> result = loginService.login(loginParam.getUsername(), loginParam.getPassword());

        //登陆成功
        if (!result.isSuccess()) {

            req.getSession().setAttribute("username", loginParam.getUsername());

            return "redirect:/main/index";
        }
        //返回参数
        model.addAttribute("username", loginParam.getUsername());
        //返回参数
        model.addAttribute("msg", result.getMsg());

        return "login";
    }


    /**
     * 登陆验证
     *
     * @return
     */
    @PostMapping(value = "register/save", name = "登陆验证")
    public String registerSave(@Valid SysUserArgs sysUserArgs, Model model, HttpServletRequest request) {

        //密码加密
        sysUserArgs.setPassword(PasswordUtil.encrypt(sysUserArgs.getPassword(), sysUserArgs.getUsername()));
        sysUserArgs.setPasswordKey(sysUserArgs.getUsername());
        //保存注册信息
        int num = sysUserService.insertSelective(sysUserArgs);
        if (num > 0) {
            //如果保存成功，则进行登陆
            LoginParam loginParam = new LoginParam();
            loginParam.setUsername(sysUserArgs.getUsername());
            loginParam.setPassword(sysUserArgs.getPassword());
//            return login(loginParam,model,request);
        }

        return "redirect:/main/login";
    }

}
