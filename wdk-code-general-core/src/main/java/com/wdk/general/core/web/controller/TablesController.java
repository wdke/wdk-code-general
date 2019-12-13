package com.wdk.general.core.web.controller;

import com.alibaba.fastjson.JSON;
import com.wdk.general.core.dao.SchemaTablesDao;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.model.Tables;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * created by wdk on 2019/12/11
 */
@Controller
@RequestMapping("tables")
public class TablesController {

    private Logger logger= LoggerFactory.getLogger(TablesController.class);

    @Autowired
    private SchemaTablesDao iTablesDao;


    /**
     * 数据库地址页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("进入数据源录入页面");

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
    public String save(Model model, DbMessage dbMessage, HttpServletRequest request) {
        logger.info("保存数据源信息");
//        String token = JwtUtils.createJWT(dbMessage);

        request.getSession().setAttribute("dbMessage",dbMessage);
        UserContext.current().setDbMessage(dbMessage);

        System.out.println(JSON.toJSONString(dbMessage));

        List<Tables> list = iTablesDao.list();
        System.out.println(JSON.toJSONString(list));

        model.addAttribute("list",list);


        return "tables/list";
    }

}
