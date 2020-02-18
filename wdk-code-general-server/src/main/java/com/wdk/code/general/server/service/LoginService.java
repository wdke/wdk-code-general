package com.wdk.code.general.server.service;


import com.wdk.general.core.common.model.ResponseVo;

/**
 * created by wdk on 2019/12/22
 */
public interface LoginService {

    /**
     * 登陆接口
     *
     * @param username
     * @param password
     * @return
     */
    ResponseVo<String> login(String username, String password);


    /**
     * 校验token
     *
     * @param tokenValue
     */
    ResponseVo<Boolean> tokenCheck(String tokenValue);
}
