package com.test.demo.effective.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务提供者框架
 * 【effective java item 1】
 * Created on 2016/11/11.
 */
public class Services {
    private Services() {}

    private static final Map<String, Provider> providers = new ConcurrentHashMap<>();

    public static final String DEFAULT_PROVIDER_NAME = "nikohan";

    public static void registerDefaultProvider(Provider p) {
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }

    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }

    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }

    public static Service newInstance(String name) {
        Provider p = providers.get(name);
        if(null == p) {
            throw new IllegalArgumentException("No provider registered with name:" + name);
        }

        return p.newService();
    }

    public static void main(String[] args) {
        Provider provider = new DemoProvider();
        Services.registerProvider("demo", provider);

        Service service = Services.newInstance("demo");
        service.display();
    }
}
