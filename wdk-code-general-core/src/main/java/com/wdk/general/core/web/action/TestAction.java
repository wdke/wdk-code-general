package com.wdk.general.core.web.action;

import com.alibaba.fastjson.JSON;
import com.wdk.general.core.common.model.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wdk.general.core.common.enums.ResponseStatusEnum;

/**
 * created by wdk on 2019/12/17
 */
@RestController
@RequestMapping("action/test")
public class TestAction {


    private static Logger logger= LoggerFactory.getLogger(TestAction.class);

    /**
     * 移除方法
     *
     * @param id
     * @return
     */
    @PostMapping(value = "remove", name = "移除方法")
    public ResponseVo<String> remove(String id) {
        logger.info("remove param【id->{}】",id);
        ResponseVo<String> result = new ResponseVo<String>(ResponseStatusEnum.SUCCESS,"");

        logger.info("remove end result->{}", JSON.toJSONString(result));
        return result;

    }
}
