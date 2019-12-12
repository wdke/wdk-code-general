package com.wdk.general.core.common;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wdke on 1541852927067.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public JSONObject processError(HttpServletResponse response, Exception ex) {

        logger.error("异常：", ex);
        JSONObject result = new JSONObject();
        result.put("code", 500);
        result.put("msg", ex.getMessage());
        return result;
    }


    //
}