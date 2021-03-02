package com.init.mini.common.util;

import com.init.mini.common.base.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtil {

    public final static Logger log = LoggerFactory.getLogger(ThreadLocalUtil.class);

    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    private static final ThreadLocal<Object> baseLocal = new ThreadLocal<>();

    private static Map<String, Map> threadMap = null;

    // 存储数据
    public static <K, V>void setLocal(K key, V value) {
        Map<String, V> map = customize(key, value);
        // map 就是 threadLocal
        threadLocal.set(map);
    }

    public static <K, V>void setLocal(K key1, K key2, V value) {
        Map<String, V> map = customize(key1, key2, value);
        threadLocal.set(map);
    }

    // 可以存储机构代码+用户代码
    public static void setBaseLocal(String key, String value) {
        Map<String, String> baseMap = (Map<String, String>) baseLocal.get();
        if (baseMap == null) {
            baseMap = new HashMap<>();
            baseMap.put(key, value);
        } else {
            baseMap.put(key, value);
        }
        baseLocal.set(baseMap);
    }

    public static <K, V>V getLocal(K key) {
        Map<String, V> map = (Map<String, V>) threadLocal.get();
        if (map !=null) {
            return customize(key, map);
        }
        return null;
    }

    public static <K, V>V getLocal(K key1, K key2) {
        Map<String, V> map = (Map<String, V>) threadLocal.get();
        if (map !=null) {
            return customize(key1, key2, map);
        }
        return null;
    }

    public static <K, V>Map<String, V> customize(K key, V value) {
        String localkey = "";
        if (key == null) {
            log.error("缓存 key 为 null");
            throw new BusinessException("缓存 key 为 null");
        } else if (String.class.isInstance(key)) {
            localkey = (String) key;
        } else {
            localkey = String.valueOf(key);
        }
        localkey = baseKey(localkey);
        Map<String, V> localMap = (Map<String, V>) threadLocal.get();
        if (localMap == null) {
            localMap = new HashMap<>();
            localMap.put(localkey, value);
        } else {
            localMap.put(localkey, value);
        }
        return localMap;
    }

    public static <K, V>V customize(K key, Map<String, V> map) {
        String localkey = "";
        if (key == null) {
            log.error("缓存 key 为 null");
            throw new BusinessException("缓存 key 为 null");
        } else if (String.class.isInstance(key)) {
            localkey = (String) key;
        } else {
            localkey = String.valueOf(key);
        }
        localkey = baseKey(localkey);

        return map.get(localkey);
    }

    public static <K, V>V customize(K key1, K key2, Map<String, V> map) {
        String localkey = "";
        if (key1== null) {
            log.error("缓存 key 为 null");
            throw new BusinessException("缓存 key 为 null");
        } else if (String.class.isInstance(key1)) {
            localkey = new StringBuffer().append(key1).append(key2).toString();
        } else {
            localkey = new StringBuffer().append(key1).append(key2).toString();
        }
        localkey = baseKey(localkey);

        return map.get(localkey);
    }

    public static <K, V>Map<String, V> customize(K key1, K key2, V value) {
        String localkey = "";
        if (key1 == null) {
            log.error("缓存 key 为 null");
            throw new BusinessException("缓存 key 为 null");
        } else if (String.class.isInstance(key1)) {
            localkey = new StringBuffer().append(key1).append(key2).toString();
        } else {
            localkey = new StringBuffer().append(key1).append(key2).toString();
        }
        localkey = baseKey(localkey);
        Map<String, V> localMap = (Map<String, V>) threadLocal.get();
        if (localMap == null) {
            localMap = new HashMap<>();
            localMap.put(localkey, value);
        } else {
            localMap.put(localkey, value);
        }
        return localMap;
    }

    public static String baseKey(String localKey) {
        Map<String, String> baseMap = (Map<String, String>) threadLocal.get();
        String baseKey1 = baseMap.get("dept:code");
        String baseKey2 = baseMap.get("user:id");
        return new StringBuilder().append(baseKey1).append(baseKey2).append(localKey).toString();
    }

    public static void removeLocal() {
        try {
            baseLocal.remove();
            threadLocal.remove();
        } catch (BusinessException e) {
            log.error("线程缓存清理异常");
        }
        log.debug("线程缓存已经被清理");
    }
}
