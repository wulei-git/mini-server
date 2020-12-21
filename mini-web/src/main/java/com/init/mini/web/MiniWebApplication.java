package com.init.mini.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
// Annotation @EnableCircuitBreaker found,
// but there are no implementations. Did you forget to include a starter?
//@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
public class MiniWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniWebApplication.class, args);
    }

}
