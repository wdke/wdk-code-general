package com.wdk.code.general.server.web.controller;

import com.alibaba.fastjson.JSON;
import com.wdk.code.general.server.redis.RedisStringDao;
import com.wdk.code.general.server.service.DbMessagesService;
import com.wdk.code.general.server.web.args.DbMessagesArgs;
import com.wdk.code.general.server.web.vo.DbMessagesVo;
import com.wdk.general.core.common.constant.RedisConstant;
import com.wdk.general.core.common.model.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("db")
public class DbController {

    private Logger logger = LoggerFactory.getLogger(DbController.class);

    @Autowired
    private RedisStringDao redisStringDao;

    @Autowired
    private DbMessagesService dbMessagesService;


    /**
     * 数据库地址页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model, DbMessagesArgs param) {

        logger.info("进入数据源录入页面");

        //获取数据库信息
        param.setUserId(UserContext.current().getUserId());
        List<DbMessagesVo> list = dbMessagesService.list(param);
        model.addAttribute("list", list);

        logger.info("index 获取数据库信息完成，list.size={}，开始获取缓存信息 key={}。", list.size(), "db_" + UserContext.current().getUsername());
        DbMessagesArgs dbMessage = JSON.parseObject(redisStringDao.get("db_" + UserContext.current().getUsername()), DbMessagesArgs.class);

        if (null == dbMessage) {
            dbMessage = new DbMessagesArgs();
            if(list.size() > 0){
                BeanUtils.copyProperties(list.get(0),dbMessage);
            }
        }

        logger.info("index 获取缓存信息完成。dbMessage={}", JSON.toJSONString(dbMessage));

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
    public String save(Model model, DbMessagesArgs dbMessage, HttpServletRequest request) {
        logger.info("保存数据源信息");

        redisStringDao.set("db_" + UserContext.current().getUsername(), JSON.toJSONString(dbMessage), RedisConstant.DB_TIME);

        //设置用户ID
        dbMessage.setUserId(UserContext.current().getUserId());

        dbMessagesService.insertSelective(dbMessage);

        logger.info("进入数据源录入页面");

        model.addAttribute("db", dbMessage);

        return "db/index";
    }

}
