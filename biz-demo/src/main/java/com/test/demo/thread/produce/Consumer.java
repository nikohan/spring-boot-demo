package com.test.demo.thread.produce;

/**
 * Created on 2017/8/31.
 */
public class Consumer extends Thread {

	private BlockQueue<Integer> blockQueue;

	public Consumer(BlockQueue<Integer> blockQueue) {
		this.blockQueue = blockQueue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				int num = blockQueue.take();
				System.out.println("Consume:" + num);
				Thread.sleep((int) (Math.random() * 100));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
