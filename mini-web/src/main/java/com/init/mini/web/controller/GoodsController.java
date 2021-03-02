package com.init.mini.web.controller;

import com.init.mini.web.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/goods/insert")
    public String test() {
        return goodsService.insertBatch().toString();
    }

    @GetMapping("/goods/to/redis")
    public String test2() {
        goodsService.execute();
        return null;
    }


}
