package com.init.mini.web.controller;

import com.init.mini.web.config.User;
import com.init.mini.web.entity.JsSysUser;
import com.init.mini.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;

@Api(tags = "用户crud测试")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/user")
    @ApiOperation(value = "Swagger的测试", httpMethod = "POST", response = JsSysUser.class, notes = "管理员接口")
    public JsSysUser test(@ApiParam(value = "管理员对象", name = "管理员",required = true) JsSysUser jsSysUser) {
//        return userService.queryTest();
//        return "mybatis";
        return new JsSysUser();
    }
}
