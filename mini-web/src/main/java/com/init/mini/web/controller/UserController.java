package com.init.mini.web.controller;

import com.init.mini.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/user")
    public String test() {
        return userService.queryTest();
//        return "mybatis";
    }
}
