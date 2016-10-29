package com.test.demo.concurrency;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

/**
 * [effective java item 66]
 * Created on 2016/10/28.
 */
public class AtomicDemo {
    private static final AtomicLong num = new AtomicLong();

    //AtomicLong是线程安全类
    public static long generateSerialNumber() {
        return num.getAndIncrement();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int a = 10000;
        ExecutorService executor = Executors.newFixedThreadPool(10000);
        for (int tNum = 0; tNum < a; tNum++) {
            Future future = executor.submit(() -> {
                for (int i = 0; i < 10; i ++) {
                    generateSerialNumber();
                    System.out.println(num);
                }
                return num;
            });
            future.isDone();
        }
    }
}
