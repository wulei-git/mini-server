package com.init.mini.web.jvm.InnerClass;

public class InnerClassDemo {

    public static void main(String[] args) {
        new Thread(
                () -> {

                }
        ).start();
    }
}
