package com.test.demo.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * [effective java : Item 69]
 * Created on 2016/10/27.
 */
public class CountDownLatchDemo {

    //统计多个线程并发执行的时间，从某个时间点同时开始，到最后某个线程执行结束
    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        final CountDownLatch ready = new CountDownLatch(concurrency);
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(concurrency);
        for(int i = 0; i < concurrency; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    ready.countDown();//某个线程已经准备完毕
                    try {
                        start.await();//等待所有线程准备完毕
                        action.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        done.countDown();//某个线程完成
                    }
                }
            });
        }
        ready.await();//等待所有线程准备好
        long startNanos = System.nanoTime();//计时开始
        start.countDown();//全部线程启动
        done.await();//等待所有线程完成
        return System.nanoTime() - startNanos;
    }

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(100);
        long result = time(executor, 100, new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });

        System.out.println(result);
    }
}
