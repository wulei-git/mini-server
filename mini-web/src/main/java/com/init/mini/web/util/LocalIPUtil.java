package com.init.mini.web.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalIPUtil {

    public final static String LOCAL_IP;
    public final static byte[] LOCAL_IP2;

    static {
        try {
            LOCAL_IP = InetAddress.getLocalHost().getHostAddress();
            LOCAL_IP2 = InetAddress.getLocalHost().getAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        // 127.0.0.1
        System.out.println(LOCAL_IP);
        // 127
        //0
        //0
        //1
        for (int i = 0; i < LOCAL_IP2.length; i++) {
            System.out.println(LOCAL_IP2[i]);
        }

    }
}
