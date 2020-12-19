package com.init.mini.func.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @ResponseBody
    @GetMapping(value = "/test", produces = "application/json")
    public String test() {
        String reslut = new String();
        try {
            reslut = "hello resttemplate";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return reslut;
    }
}
