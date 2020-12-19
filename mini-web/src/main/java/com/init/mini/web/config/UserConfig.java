package com.init.mini.web.config;

import com.init.mini.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public User user(@Autowired User user3) {
        user3.setAge(1111111111);
        System.out.println(user3.getAge());
        return new User();
    }
    // 默认情况下@Bean注释的方法名作为对象的名字，也可以用name属性定义对象的名字。
    // @Bean注解比@Component注解的自定义性更强，而且很多地方只能通过@Bean注解来
    // 注册bean。比如当引用第三方库的类需要装配到Spring容器的时候，就只能通过@Bean注解来实现
    @Bean
    public User user2() {
        return new User();
    }

    @Bean
    public RedisUtil redisUtil() {return new RedisUtil();}

//    @Bean
//    public LogSaveAOP logSaveAOP() {return new LogSaveAOP();}
}
