package com.test.demo.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * [effective java item 66]
 * Created on 2016/10/28.
 */
public class VolatileDemo {
    private static volatile int num = 0;

    //volatile 不能保证++操作符的线程安全性
    public static int generateSerialNumber() {
        return num++;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a = 100000;
        ExecutorService executor = Executors.newFixedThreadPool(10000);
        for (int tNum = 0; tNum < a; tNum++) {
            Future future = executor.submit(() -> {
                for (int i = 0; i < 10; i++) {
                    generateSerialNumber();
                }
                return num;
            });
            future.isDone();
            System.out.println(future.get());
        }
    }
}
