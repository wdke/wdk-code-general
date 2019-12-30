package com.wdk.general.core.web.controller;

import com.alibaba.fastjson.JSON;
import com.wdk.general.core.common.constant.RedisConstant;
import com.wdk.general.core.dao.SchemaTablesDao;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.storage.redis.RedisStringDao;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * created by wdk on 2019/12/11
 */
@Controller
@RequestMapping("db")
public class DbController {

    private Logger logger = LoggerFactory.getLogger(DbController.class);

    @Autowired
    private RedisStringDao redisStringDao;


    /**
     * 数据库地址页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        logger.info("进入数据源录入页面");
        DbMessage dbMessage = JSON.parseObject(redisStringDao.get("db_" + UserContext.current().getUsername()),DbMessage.class);

        if (null == dbMessage) {
            dbMessage = new DbMessage();
            dbMessage.setUserId(1000);
            dbMessage.setHost("49.233.195.134");
            dbMessage.setDbname("nideshop");
            dbMessage.setDbuser("root");
            dbMessage.setDbpassword("root1234");
            dbMessage.setDbport(3306);
        }
        model.addAttribute("db", dbMessage);

        return "db/index";
    }


    /**
     * 登陆数据库
     *
     * @param model
     * @param dbMessage
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, DbMessage dbMessage, HttpServletRequest request) {
        logger.info("保存数据源信息");

        redisStringDao.set("db_" + UserContext.current().getUsername(), JSON.toJSONString(dbMessage), RedisConstant.DB_TIME);


        logger.info("进入数据源录入页面");

        model.addAttribute("db", dbMessage);

        return "db/index";
    }

}
