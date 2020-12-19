package com.init.mini.web.service.impl;

//import com.init.mini.web.remote.TestRemote;

import com.init.mini.web.annotation.LogSave;
import com.init.mini.web.config.User;
import com.init.mini.web.service.TestService;
import com.init.mini.common.util.RedisUtil;
import com.init.mini.web.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TestServiceImpl implements TestService {

//    @Autowired
//    private TestRemote testRemote;
    @Autowired
    private RedisUtil redisUtil;

    @Override
//    @LogSave("testM2")
    public String test() throws Exception {
//        return testRemote.test();
//        return "hello world";
//        return restTemplate.getForObject("http://mini-func/test/", String.class);
        User userService = SpringUtils.getBean("user");
        Map<String, User> userService1 = SpringUtils.getBeanByType(User.class);
        redisUtil.setObject("testredis", "redis", 1L, TimeUnit.MINUTES);
        String result = (String)redisUtil.getObject("testredis");

        return result;
    }

}
