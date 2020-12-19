package com.init.mini.func.route;

import com.init.mini.common.api.FuncApi;
import org.springframework.stereotype.Controller;

@Controller
public class TestRouteRest implements FuncApi.Test {
    @Override
    public String test() {
        return "hello feign";
    }
}
