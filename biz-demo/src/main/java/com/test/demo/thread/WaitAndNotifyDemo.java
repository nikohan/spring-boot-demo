package com.test.demo.thread;

/**
 * Created by zhaohan on 2016/8/4.
 */
public class WaitAndNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(new WaitDemo(lock));
        t1.start();
        System.out.println("thread1 is starting...");
    }
}

class WaitDemo implements Runnable {
    private Object lock;
    private int num;

    public WaitDemo(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (num < 100) {
            synchronized(lock) {
                try {
                    Thread.sleep(1000);
                    num += 10;
                    System.out.println("WaitDemo:" + num);

                    if(50 == num) {
                        Thread t2 = new Thread(new NotifyDemo(lock));
                        t2.start();

                        System.out.println("thread1 is waiting...");
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class NotifyDemo implements Runnable {
    private int num;
    private Object lock;

    public NotifyDemo(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (num < 100) {
            synchronized(lock) {
                try {
                    Thread.sleep(1000);
                    num += 10;
                    System.out.println("NotifyDemo:" + num);

                    if(90 == num) {
                        lock.notifyAll();
                        System.out.println("Thread is notified.And the thread continued to run.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}