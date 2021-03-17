package com.init.mini.web.annotationtest;

import java.lang.annotation.*;

/**
 * 只有一个成员的注解，变量名只能为 value
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
@Documented
public @interface OnlyOneFiled {
    String value();
}
