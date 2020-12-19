//
//package com.init.mini.web.config;
//
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//
////在springboot1.3版本中会默认提供一个RestTemplate的实例Bean，
//// 当在springboot1.4以及以后的版本中，需要手动创建一个RestTemplate的配置
//@Configuration
//public class Config {
//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder){
//        return builder.build();
//    }
//}