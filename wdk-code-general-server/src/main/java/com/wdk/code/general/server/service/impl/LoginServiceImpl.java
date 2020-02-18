package com.wdk.code.general.server.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wdk.code.general.server.redis.RedisStringDao;
import com.wdk.code.general.server.service.LoginService;
import com.wdk.code.general.server.service.SysUserService;
import com.wdk.code.general.server.web.args.SysUserArgs;
import com.wdk.code.general.server.web.vo.SysUserVo;
import com.wdk.general.core.common.constant.RedisConstant;
import com.wdk.general.core.common.enums.ResponseStatusEnum;
import com.wdk.general.core.common.model.ResponseVo;
import com.wdk.general.core.utils.JwtUtils;
import com.wdk.general.core.utils.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by wdk on 2019/12/22
 */
@Service
public class LoginServiceImpl implements LoginService {



    private static Logger logger = LoggerFactory.getLogger(LoginService.class);


    @Autowired
    RedisStringDao redisStringDao;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登陆接口
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResponseVo<String> login(String username, String password) {

        //查询用户信息
        SysUserArgs sysUser = new SysUserArgs();
        sysUser.setUsername(username);
        List<SysUserVo> list = sysUserService.list(sysUser);


        if (list.isEmpty()) {
            logger.info("login  用户不存在 .username={}", username);
            return new ResponseVo(ResponseStatusEnum.PARRAM_ERROR.getCode(), "用户不存在");
        } else {
            for (SysUserVo user : list) {
                if (PasswordUtil.encrypt(password, user.getPasswordKey()).equals(user.getPassword())) {
                    return new ResponseVo(ResponseStatusEnum.SUCCESS.getCode(), "登录成功");
                }
            }
        }

        //生成 token
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        String token = JwtUtils.createJWT(map);

        //保存到 redis
        redisStringDao.set("token_" + username, token, RedisConstant.TOKEN_TIME);
        logger.info("login 登录失败。username={}", username);
        return new ResponseVo(ResponseStatusEnum.SUCCESS, token);

    }



    /**
     * 校验token
     *
     * @param tokenValue
     * @return
     */
    @Override
    public ResponseVo<Boolean> tokenCheck(String tokenValue) {
        String check = redisStringDao.get(tokenValue);
        if (StringUtils.isEmpty(check)) {
            return new ResponseVo(ResponseStatusEnum.SUCCESS, false);
        }
        return new ResponseVo(ResponseStatusEnum.SUCCESS, true);
    }


}
