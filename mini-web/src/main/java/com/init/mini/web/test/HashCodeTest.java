package com.init.mini.web.test;

import java.util.HashMap;
import java.util.Map;

public class HashCodeTest {
    public static void main(String[] args) {
        Map<User,String> map = new HashMap<>();
        User user1 = new User();
        user1.setId(1);
        user1.setName("11");
        User user2 = new User();
        user2.setId(1);
        user2.setName("11");
        map.put(user1, user1.getName());
        map.put(user2, user2.getName());
        System.out.println(map.size());

    }
}
