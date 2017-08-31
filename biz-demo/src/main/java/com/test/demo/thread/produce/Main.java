package com.test.demo.thread.produce;

/**
 * Created on 2017/8/31.
 */
public class Main {
	public static void main(String[] args) {
//		BlockQueue<Integer> blockQueue = new WaitNotifyBlockQueue<>(1);
		BlockQueue<Integer> blockQueue = new ReentrantLockBlockQueue<>(1);

		new Producer(blockQueue).start();
		new Consumer(blockQueue).start();
	}
}
