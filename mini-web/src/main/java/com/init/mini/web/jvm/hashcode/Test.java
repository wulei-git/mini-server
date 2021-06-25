package com.init.mini.web.jvm.hashcode;

public class Test {

    public static void main(String[] args) {
        // 没有重写 hashcode and equals
        UserDemo userDemo = new UserDemo();
        userDemo.setAge(19);
        userDemo.setName("19");

        UserDemo userDemo2 = new UserDemo();
        userDemo2.setAge(19);
        userDemo2.setName("19");

        // 544724190
        System.out.println(userDemo.hashCode());
        // 1972439101
        System.out.println(userDemo2.hashCode());
        // false
        System.out.println(userDemo.equals(userDemo2));

        // 重写 equals
        // 544724190
        System.out.println(userDemo.hashCode());
        // 1972439101
        System.out.println(userDemo2.hashCode());
        // true
        System.out.println(userDemo.equals(userDemo2));

        // 重写 hashcode
        // 49836
        System.out.println(userDemo.hashCode());
        // 49836
        System.out.println(userDemo2.hashCode());
        // false
        System.out.println(userDemo.equals(userDemo2));

        // 重写 hashcode and equals
        // 49836
        System.out.println(userDemo.hashCode());
        // 49836
        System.out.println(userDemo2.hashCode());
        // true
        System.out.println(userDemo.equals(userDemo2));
    }
}
