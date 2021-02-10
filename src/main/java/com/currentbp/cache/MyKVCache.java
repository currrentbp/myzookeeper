package com.currentbp.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author baopan
 * @createTime 20210210
 */
public class MyKVCache {
    private static Map<String,String> cache = new ConcurrentHashMap<>();

    public static void set(String key,String value){
        cache.put(key,value);
    }

    public static String get(String key){
        return cache.get(key);
    }
}
