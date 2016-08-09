package com.test.demo.thread;

/**
 * 1.synchronized修饰非静态方法时，则是锁定同一个实例
 * 2.synchronized修饰静态方法时，则是锁定该类的所有实例
 *
 * Created by zhaohan on 2016/8/9.
 */
public class ProducerAndConsume {

    private static int MAX_PRODUCT = 10;

    private static int MIN_PRODUCT = 0;

    private static int product;

    public synchronized static void produce() {
        if(product >= MAX_PRODUCT) {
            try {
                System.out.println("产品已满");
                ProducerAndConsume.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            product ++;
            System.out.println("生产者生产第" + product + "个产品.");
            ProducerAndConsume.class.notifyAll();
        }
    }

    public synchronized static void consume() {
        if(product <= MIN_PRODUCT) {
            try {
                System.out.println("缺货");
                ProducerAndConsume.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("消费者消费第"  + product + "个产品");
            product --;
            ProducerAndConsume.class.notifyAll();
        }
    }

    public static void main(String[] args) {
        ProducerAndConsume shop = new ProducerAndConsume();
        ProducerAndConsume shop2 = new ProducerAndConsume();

        //启动生产线程
        new Thread(() -> {
            while(true) {
                shop.produce();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //启动消费线程
        new Thread(() -> {
            while(true) {
                shop2.consume();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
