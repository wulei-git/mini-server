package com.init.mini.web.service.impl;

import com.init.mini.web.mapper.UserMapper;
import com.init.mini.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceImpl implements UserService {
    // PostConstruct在构造函数之后执行,可作为一些数据的常规化加载，比如数据字典之类
    // spring中Constructor、@Autowired、@PostConstruct的顺序
    // 在生成对象时候完成某些初始化操作，而偏偏这些初始化操作又依赖于依赖注入，那么就
    // 无法在构造函数中实现。为此，可以使用@PostConstruct注解一个方法来完成初始化，
    // @PostConstruct注解的方法将会在依赖注入完成后被自动调用
    @PostConstruct
    public void init() {

    }
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ThreadDemo threadDemo;
    @Override
    public String queryTest() {
        System.out.println(Thread.currentThread().getName());

        threadDemo.test2();
        System.out.println(123);
        // @Async 方法定义和调用的类不能相同
        return userMapper.selectTest();
    }





}
