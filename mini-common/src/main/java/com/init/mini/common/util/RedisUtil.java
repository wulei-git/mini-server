package com.init.mini.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    // 项目中有两个redisTemplate的Bean，而redisTemplate刚好可以指定到RedisTemplate类，
    // 所以如果变量名写的是其他的话会找到两个Bean。一个是RedisTemplate，一个是StringRedisTemplate。
    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
    }

    public Object getObject(String key) throws Exception {
        return redisTemplate.opsForValue().get(key);
    }

    public <T> void setObject(String key, T t, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, t, time, timeUnit);
    }

}
