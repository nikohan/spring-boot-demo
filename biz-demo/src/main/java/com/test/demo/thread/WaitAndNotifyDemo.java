package com.test.demo.thread;

/**
 * Created by zhaohan on 2016/8/4.
 */
public class WaitAndNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(new RunnableDemo());

        t1.start();
        System.out.println("Thread is starting...");

        Thread.sleep(1000);

        lock.wait();
        System.out.println("Thread is waiting...");

        Thread.sleep(5000);

        lock.notify();
        System.out.println("Thread is notified.And the thread continued to run.");
    }
}

class RunnableDemo implements Runnable {
    private static int num;
    @Override
    public void run() {
        while (num < 100) {
            try {
                Thread.sleep(1000);
                num += 10;
                System.out.println(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}