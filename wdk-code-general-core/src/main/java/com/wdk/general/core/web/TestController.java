package com.wdk.general.core.web;

import com.wdk.general.core.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * created by wdk on 2019/12/11
 */
@Controller
@RequestMapping("test")
public class TestController {


    @Value("${host}")
    private String host;

    @RequestMapping(value = "index",method = RequestMethod.GET,name = "测试主页")
    public String index(Model model){

        model.addAttribute("test","测试数据");
        model.addAttribute("host",host);

        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String memberShow(Model model, String firstname, HttpServletRequest request) {


        System.out.println("#################################");
        System.out.println(firstname+"\t"+request.getParameter("firstname"));
        System.out.println("#################################");
        User vo = new User();
        vo.setUid(12345678L);
        vo.setName("尼古拉丁.赵四");
        vo.setAge(59);
        vo.setSalary(1000.00);
        vo.setBirthday(new Date());
        model.addAttribute("member", vo);
        return "user/index";
    }
}
