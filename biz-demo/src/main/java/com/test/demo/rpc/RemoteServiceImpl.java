package com.test.demo.rpc;

/**
 * Created by nikohan on 2016/12/6.
 */
public class RemoteServiceImpl implements RemoteService {
    @Override
    public String printHello(String name) {
        System.out.println("call remote method....");
        return "hello " + name;
    }
}
