package com.test.demo.thread;

/**
 * Created by zhaohan on 2016/8/9.
 */
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("thread1 is starting...");
                Thread.sleep(10000);
                System.out.println("thread1 is ended...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("thread2 is starting...");
                Thread.sleep(5000);
                System.out.println("thread2 is ended...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();

        //等到t1运行结束之后，t2开始运行
        t1.join();

        t2.start();
    }
}
