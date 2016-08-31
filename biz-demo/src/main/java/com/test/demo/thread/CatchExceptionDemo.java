package com.test.demo.thread;

import com.test.demo.domain.User;

/**
 * Created by zhaohan on 2016/8/9.
 */
public class CatchExceptionDemo implements Runnable{
    User user;
    public static void main(String[] args) {
        try {
            Thread t1 = new Thread(new CatchExceptionDemo());
            t1.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
                System.out.println("catch到了!");
            });
            t1.start();
        } catch(Exception e) {
            System.out.println("catch不到!");
        }
    }

    @Override
    public void run() {
        //会抛出空指针异常
        System.out.println(user.getName());
    }
}
