package com.test.demo.effective.service;

/**
 * Created on 2016/11/11.
 */
public class DemoProvider implements Provider {
    @Override
    public Service newService() {
        Service service = () -> System.out.println("DemoProvider provide the service.");
        return service;
    }
}
