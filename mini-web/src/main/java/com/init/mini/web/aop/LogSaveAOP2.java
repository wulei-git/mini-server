package com.init.mini.web.aop;


import com.init.mini.web.annotation.LogSave;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Order(2)
@Aspect
@Component
public class LogSaveAOP2 {

    @Pointcut("@annotation(com.init.mini.web.annotation.LogSave)")
    public void logSave() {}

//    @Around("@annotation(logSave)")
    public Object aroundTest(ProceedingJoinPoint proceedingJoinPoint, LogSave logSave) {
        String methodName = proceedingJoinPoint.getSignature().getName();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String headTest = httpServletRequest.getHeader("head-test");
        System.out.println("记录2："+ logSave.value());
        Object proceed = null;
        try {
            // 跳转执行方法中间点
            proceed = proceedingJoinPoint.proceed();
            System.out.println("do something2...");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

    @Before("@annotation(logSave)")
    public void beforeTest(LogSave logSave) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String headTest = httpServletRequest.getHeader("head-test");
        System.out.println("记录2："+ logSave.value());
        Object proceed = null;
        try {
            // 跳转执行方法中间点
            System.out.println("do something.2..");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



}
