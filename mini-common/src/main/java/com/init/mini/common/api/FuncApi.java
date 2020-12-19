package com.init.mini.common.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class FuncApi {

    public interface Test{
        @ResponseBody
        @GetMapping(value = "/test")
        String test();
    }
}
