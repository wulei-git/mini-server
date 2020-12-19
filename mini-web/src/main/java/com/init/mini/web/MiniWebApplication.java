package com.init.mini.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCircuitBreaker
//@EnableDiscoveryClient
//@EnableFeignClients
public class MiniWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniWebApplication.class, args);
    }

}
