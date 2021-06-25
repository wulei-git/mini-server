package com.init.mini.web.jvm.gc;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("hi");

        WeakReference<StringBuffer> sw = new WeakReference<>(sb);
        System.gc();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("获取弱引用对象，如果被 GC 回收则返回 null:"+sw.get());
        System.out.println("返回是否被 GC 标记为即将回收的垃圾，是否在关联的队列中:"+sw.isEnqueued());

        System.out.println(sb);

        System.out.println(sw);

    }
}
