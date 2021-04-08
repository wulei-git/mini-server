package com.init.mini.web.util;

import java.net.URL;

public class ResourceURLUtil {

    /**
     * 获取某个资源的绝对路径
     *
     * @param resourcePath ""代表/classes/ 值为相对于/classes/的相对路径
     * @param defaultValue
     * @return
     */
    public static String getResourcePath(String resourcePath, String defaultValue) {
        URL resource = Thread.currentThread()
                .getContextClassLoader()
                .getResource(resourcePath);
        return resource !=null ? resource.getPath() : defaultValue;
    }

    public static void main(String[] args) {
        // /Users/apple/Documents/git/mini-server/mini-web/target/classes/
        System.out.println(getResourcePath("", null));
        // /Users/apple/Documents/git/mini-server/mini-web/target/classes/application.properties
        System.out.println(getResourcePath("application.properties", null));
        // /Users/apple/Documents/git/mini-server/mini-web/target/classes/static/index.html
        System.out.println(getResourcePath("static/index.html", null));

    }
}
