package com.init.mini.web.controller;

import com.alibaba.fastjson.JSON;
import com.init.mini.web.annotation.LogSave;
import com.init.mini.web.service.TestService;
import com.init.mini.web.service.impl.TestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.cloud.context.config.annotation.RefreshScope;


@Controller
@RefreshScope
public class TestController {

    @Autowired
    private TestService testService;

//    @Value("${testScope}")
//    private String testScope;

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @ResponseBody
    @GetMapping(value = "/test", produces = "application/json")
    @LogSave("testM")
    public String test() {
        String reslut = new String();
        try {
            reslut = testService.test();
//            int i = 2/0;
        } catch (Exception e) {
            logger.error("test:{}", e);
            throw new RuntimeException();
        } finally {
            reslut = JSON.toJSONString(reslut);
            String reslut2 = (reslut == null) ? "123" : reslut;
            String s = "123";
            logger.info("test:{}", reslut2);
        }
        System.out.println(Thread.currentThread().getName());
        return reslut;
    }

    public String testHystrixCommand() {
        return "exception";
    }

}
