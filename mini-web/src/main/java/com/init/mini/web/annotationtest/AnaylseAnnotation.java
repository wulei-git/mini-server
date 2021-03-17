package com.init.mini.web.annotationtest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnaylseAnnotation {
    public static void main(String[] args) throws Exception {
//        Class clazz =  AnnotationClient.class;
        Class clazz = Class.forName("com.init.mini.web.annotationtest.ChildOfChild");
        // 注意此时 clazz 对象指的是类对象，isAnnotationPresent判断类相关注解
            boolean isExist = clazz.isAnnotationPresent(OnlyOneFiled.class);

        if (isExist) {
            OnlyOneFiled onlyOneFiled = (OnlyOneFiled) clazz.getAnnotation(OnlyOneFiled.class);
            System.out.println(onlyOneFiled.value());
        }

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            boolean isMethodExist = method.isAnnotationPresent(OnlyOneFiled.class);
            OnlyOneFiled onlyOneFiled1 = method.getAnnotation(OnlyOneFiled.class);
            // 注意此时 clazz 对象指的是类方法，isAnnotationPresent判断类方法相关注解
            if (isMethodExist)
                System.out.println(onlyOneFiled1.value());

            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotationItem : annotations) {
                if (annotationItem instanceof OnlyOneFiled) {
                    OnlyOneFiled on = (OnlyOneFiled) annotationItem;
                    System.out.println(on.value());
                }
            }
        }


    }
}
