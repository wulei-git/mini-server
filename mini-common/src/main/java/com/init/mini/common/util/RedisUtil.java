package com.init.mini.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.util.Map;
import java.util.Set;
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

    public <T> void setList(Map<String, String> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    public <T> void setObject(String key, T t, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, t, time, timeUnit);
    }

    public Boolean lockIp(String key) throws Exception {
        InetAddress address = InetAddress.getLocalHost();
        String singleKey = address.getHostAddress();
        return lock(key, singleKey, 30L);
    }

    public Boolean lock(String key, String value, Long expireTime) throws Exception {
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(key, value);
        if (!flag) {
            String cron = (String)redisTemplate.opsForValue().get(key);
            if (cron.equals(value)) {
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
                return true;
            }
        } else {
            redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
        }
        return flag;
    }

    public Boolean remove(String key) {
        return redisTemplate.delete(key);
    }

    public Long removeBatch(Set<String> set) {
        return redisTemplate.delete(set);
    }
}
