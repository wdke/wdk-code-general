package com.wdk.general.core.storage.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author wdke
 * @date 2019/9/15
 */
@Component
public class RedisStringDao {


    @Autowired
    private ValueOperations valueOperations;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 新增redis
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        valueOperations.set(key, value);
    }


    /**
     * 新增redis,设置过期时间
     * <p>
     * =nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
     * <p>
     * exConstant.REDIS_KEY exConstant.REDIS_KEY的值只能取EX或者Constant.REDIS_KEY，代表数据过期时间的单位，EX代表秒，Constant.REDIS_KEY代表毫秒。
     *
     * @param key
     * @param value
     * @param time
     */
    public void set(String key, Object value, int time) {
        valueOperations.set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 设置健值一秒过期
     *
     * @param key
     */
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 获取redis数据
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return valueOperations.get(key);
    }
}
