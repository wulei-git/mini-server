package com.init.mini.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
// Annotation @EnableCircuitBreaker found,
// but there are no implementations. Did you forget to include a starter?
//@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
// 开启对定时任务的支持
@EnableScheduling
public class MiniWebApplication {

    private static final Logger logger = LoggerFactory.getLogger(MiniWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MiniWebApplication.class, args);
    }

}
