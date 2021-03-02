package com.init.mini.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//不能返回页面
//@RestController
@Controller
public class HtmlController {

    @RequestMapping("/index")
    public ModelAndView htmlIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping("/testJqueryAjax")
    public ModelAndView testJqueryAjax() {
        return new ModelAndView("index2");
    }


}
