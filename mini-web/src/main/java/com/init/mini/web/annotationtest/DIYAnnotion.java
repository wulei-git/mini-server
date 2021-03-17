package com.init.mini.web.annotationtest;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 1. @interface 标识注解
 * 2. 成员以无参数无异常的方式声明
 * 3. default 为成员指定一个默认值
 * 4. 成员类型受限：基本数据类型、String、Class、Annotation、Enumeration
 */

/**
 * 注解作用域
 */
@Target({ElementType.METHOD,ElementType.TYPE})
/**
 * 注解生命周期
 */
@Retention(RetentionPolicy.RUNTIME)
/**
 * 标识注解，标识可以继承,A 类使用DIYAnnotion，B 类继承A的同时也拥有DIYAnnotion，
 * 但是父类方法没被重写不受@Inherited限制，子类重写父类并且注解没有被@Inherited标识，子类才不会继承
 */
@Inherited
@Documented
public @interface DIYAnnotion {
    /**
     * 成员以无参数无异常的方式声明
     * @return
     */
    String desc();

    /**
     * default 为成员指定一个默认值
     * @return
     */
    int age() default 18;
}
