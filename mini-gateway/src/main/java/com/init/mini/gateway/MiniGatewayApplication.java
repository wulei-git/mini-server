package com.init.mini.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableZuulProxy
public class MiniGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniGatewayApplication.class, args);
    }

}
