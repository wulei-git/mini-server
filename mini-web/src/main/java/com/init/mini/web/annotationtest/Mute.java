package com.init.mini.web.annotationtest;

public class Mute implements Person {
    @Override
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
}
