package com.test.demo.thread;

/**
 *
 * Created by zhaohan on 2016/8/9.
 */
public class ProducerAndConsume {

    private static int MAX_PRODUCT = 10;

    private static int MIN_PRODUCT = 0;

    private int product;

    public ProducerAndConsume(int product) {
        this.product = product;
    }

    public synchronized void produce() {
        if(this.product >= MAX_PRODUCT) {
            try {
                System.out.println("产品已满");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.product ++;
            System.out.println("生产者生产第" + this.product + "个产品.");
            notifyAll();
        }
    }

    public synchronized void consume() {
        if(this.product <= MIN_PRODUCT) {
            try {
                System.out.println("缺货");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("消费者消费第"  + this.product + "个产品");
            this.product --;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        ProducerAndConsume shop = new ProducerAndConsume(20);

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
                shop.consume();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
