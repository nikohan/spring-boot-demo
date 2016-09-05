package com.test.demo.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * https://github.com/google/guava/wiki/CachesExplained
 * Created by zhaohan on 2016/8/31.
 */
public class CacheTest {
    private static LoadingCache<String, String> caches;

    private static int maximumSize = 500;

    private static int expireTime = 60;

    private static TimeUnit timeUnit = TimeUnit.SECONDS;

    public CacheTest() {
        //如果有缓存则返回；否则运算、缓存、然后返回
        caches = CacheBuilder.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(expireTime, timeUnit)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return createExpensiveString(s);
                    }
                });
    }

    //没有命中，执行此方法，然后缓存，返回
    public String createExpensiveString(String str) {
        String value = str + "_value";
        return value;
    }

    public static void main(String[] args) throws Exception {
        CacheTest cacheTest = new CacheTest();
        caches.put("key", "value");
        System.out.println(caches.get("key"));
        System.out.println(caches.get("key1"));
        System.out.println(caches.get("key1"));
        System.out.println(caches.get("key2"));
    }
}
