package com.init.mini.web.annotationtest;

@OnlyOneFiled("onlyOneFiled class")
public class Child implements Person {
    @Override
    @OnlyOneFiled("onlyOneFiled method")
    public String getName() {
        return null;
    }

    @Override
    public String sing() {
        return null;
    }

    public static void main(String[] args) {
        Person mute = new Mute();
        mute.sing();
    }

    @SuppressWarnings("")
    // 没有作用 ？
    private void test() {
        Person mute = new Mute();
        mute.sing();
    }
}
