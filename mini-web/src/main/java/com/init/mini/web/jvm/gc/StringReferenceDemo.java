package com.init.mini.web.jvm.gc;

public class StringReferenceDemo {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("Hello,程序");
        StringBuffer sb2 = sb;
        sb = null;
        System.gc();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(sb2);
    }
}
