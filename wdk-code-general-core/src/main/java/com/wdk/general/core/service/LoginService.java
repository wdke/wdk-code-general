package com.wdk.general.core.service;

import com.wdk.general.core.web.param.LoginParam;

/**
 * created by wdk on 2019/12/22
 */
public interface LoginService {


    /**
     * 登陆
     *
     * @param loginParam
     */
    void login(LoginParam loginParam);
}
