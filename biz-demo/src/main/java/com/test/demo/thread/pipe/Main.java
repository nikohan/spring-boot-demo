package com.test.demo.thread.pipe;

/**
 * 线程间的通信方式之一：管道流
 * 其他还有共享内存和socket
 * Created on 2017/11/7.
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver();

		try {
			//连接两个线程的管道流
			sender.getPos().connect(receiver.getPis());
		} catch (Exception e) {
			e.printStackTrace();
		}

		new Thread(sender).start();
		new Thread(receiver).start();
	}
}
