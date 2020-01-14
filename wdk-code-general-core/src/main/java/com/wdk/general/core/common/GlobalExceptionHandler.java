package com.wdk.general.core.common;

import com.alibaba.fastjson.JSONObject;
import com.wdk.general.core.common.constant.KeyConstant;
import com.wdk.general.core.common.enums.ReturnStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by wdke on 1541852927067.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理参数验证异常
     * @param response
     * @param ex
     */
    @ExceptionHandler(BindException.class)
    public void processValidationError(HttpServletResponse response, BindException ex) {
        logger.error(ex.getMessage(),ex);
        response.setCharacterEncoding(KeyConstant.KEY_UTF8);
        response.setContentType(KeyConstant.KEY_CONTENT_TYPE);
        response.setStatus(ReturnStatusEnum.SC_OK.getValue());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            BindingResult result = ex.getBindingResult();
            List<FieldError> fieldErrors = result.getFieldErrors();
            String errors = this.processFieldErrorsStr(fieldErrors);
            JSONObject json = new JSONObject();
            json.put(KeyConstant.KEY_RESULT_STATUS, ReturnStatusEnum.API_PARAM_INVALID.getValue());
            json.put(KeyConstant.KEY_RESULT_MESSAGE, errors);
            logger.info("processValidationError msg:{}",ex.getMessage());
            writer.write(json.toJSONString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }


    /**
     * 处理参数验证异常
     * @param response
     * @param ex
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void processValidationError(HttpServletResponse response, MethodArgumentNotValidException ex) {

        logger.error(ex.getMessage(),ex);
        response.setCharacterEncoding(KeyConstant.KEY_UTF8);
        response.setContentType(KeyConstant.KEY_CONTENT_TYPE);
        response.setStatus(ReturnStatusEnum.SC_OK.getValue());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            BindingResult result = ex.getBindingResult();
            List<FieldError> fieldErrors = result.getFieldErrors();
            String errors = this.processFieldErrorsStr(fieldErrors);
            JSONObject json = new JSONObject();
            json.put(KeyConstant.KEY_RESULT_STATUS, ReturnStatusEnum.API_PARAM_INVALID.getValue());
            json.put(KeyConstant.KEY_RESULT_MESSAGE, errors);
            logger.info("processValidationError msg:{}",ex.getMessage());
            writer.write(json.toJSONString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }



    /**
     * 处理抛出的业务异常
     * @param response
     * @param ex
     */
    @ExceptionHandler(RuntimeException.class)
    public void processIspException(HttpServletResponse response, RuntimeException ex) {

        logger.error(ex.getMessage(), ex);
        response.setCharacterEncoding(KeyConstant.KEY_UTF8);
        response.setContentType(KeyConstant.KEY_CONTENT_TYPE);
        response.setStatus(ReturnStatusEnum.SC_OK.getValue());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject json = new JSONObject();
            json.put(KeyConstant.KEY_RESULT_STATUS, ReturnStatusEnum.SC_INTERNAL_SERVER_ERROR.getValue());
            json.put(KeyConstant.KEY_RESULT_MESSAGE, ex.getMessage());
            logger.info("processIspException msg:{}",ex.getMessage());
            writer.write(json.toJSONString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }




    /**
     * token 过期
     * @param response
     * @param ex
     */
    @ExceptionHandler(MalformedURLException.class)
    public void malformedURLException(HttpServletResponse response, RuntimeException ex) {

        logger.error(ex.getMessage(), ex);
        response.setCharacterEncoding(KeyConstant.KEY_UTF8);
        response.setContentType(KeyConstant.KEY_CONTENT_TYPE);
        response.setStatus(ReturnStatusEnum.SC_OK.getValue());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject json = new JSONObject();
            json.put(KeyConstant.KEY_RESULT_STATUS, ReturnStatusEnum.API_DATA_INVALID.getValue());
            json.put(KeyConstant.KEY_RESULT_MESSAGE, ex.getMessage());
            logger.info("malformedURLException msg:{}",ex.getMessage());
            writer.write(json.toJSONString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }

    /**
     * 处理未捕获的异常
     * @param response
     * @param ex
     */
    @ExceptionHandler(Exception.class)
    public void processException(HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(), ex);
        response.setCharacterEncoding(KeyConstant.KEY_UTF8);
        response.setContentType(KeyConstant.KEY_CONTENT_TYPE);
        response.setStatus(ReturnStatusEnum.SC_OK.getValue());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            JSONObject json = new JSONObject();
            json.put(KeyConstant.KEY_RESULT_STATUS, ReturnStatusEnum.SC_INTERNAL_SERVER_ERROR.getValue());
            json.put(KeyConstant.KEY_RESULT_MESSAGE, ex.getMessage());
            logger.info("processException msg:{}",ex.getMessage());
            writer.write(json.toJSONString());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }


    /**
     * 封装验证失败信息
     * @param fieldErrors
     * @return
     */
    private String processFieldErrorsStr(List<FieldError> fieldErrors) {
        StringBuilder errors = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errors.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(",");
        }
        if(errors.length()>0){
            errors.deleteCharAt(errors.length()-1);
        }
        return errors.toString();
    }
}