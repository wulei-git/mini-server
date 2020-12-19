package com.init.mini.db.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// post http://localhost:9999/refresh 触发配置中心刷新，手动
// github 提供了一种 webhook 的方式，当有代码变更的时候，会调用我们设置的刷新配置的地址，自动
// 分布式集群多个 client 使用 springcloud bus,
// 核心原理其实就是利用消息队列做广播，所以要先有个消息队列，目前官方支持 RabbitMQ 和 kafka。
@RefreshScope
public class TestController {

    // @ConfigurationProperties(prefix = "data")
    @Value("${name}")
    private String serverName;

    @GetMapping(value = "/test")
    public String test() {
        return serverName;
    }
}
