package com.test.demo.rpc;

/**
 * Created by nikohan on 2016/12/6.
 */
public class RemoteServiceImpl implements RemoteService {
    @Override
    public void printHello() {
        System.out.println("call remote method....");
    }
}
