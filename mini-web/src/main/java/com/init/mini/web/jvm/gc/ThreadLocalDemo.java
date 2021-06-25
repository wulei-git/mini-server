package com.init.mini.web.jvm.gc;

public class ThreadLocalDemo {

    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("tony");
        System.out.println(threadLocal.get());

        threadLocal.set("jack");

        System.out.println(threadLocal.get());

        threadLocal.remove();
        System.out.println(threadLocal.get());

    }
}
