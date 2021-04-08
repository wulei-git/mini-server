package com.init.mini.web.util;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

public class PredicateUtil {

    public final static Predicate<Object> IS_NULL = (Object obj) -> obj == null;


    public static <T> void throwNotAllow(String message, Predicate<T> condition, T obj) {
        if (condition.test(obj)) throw new NullPointerException(message);
    }

    public static void main(String[] args) {
        String str = null;
//        throwNotAllow("null",IS_NULL,str);

        AtomicLong sequence = new AtomicLong(0);
//        先返回再加 1，i++
        System.out.println(sequence.getAndIncrement());
        AtomicLong sequence1 = new AtomicLong(0);
//        先加 1 再返回， ++i
        System.out.println(sequence1.incrementAndGet());

    }
}
