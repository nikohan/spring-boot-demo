package com.test.demo.thread.produce;

/**
 * Created on 2017/8/31.
 */
public class Producer extends Thread {

	private BlockQueue<Integer> blockQueue;

	public Producer(BlockQueue<Integer> blockQueue) {
		this.blockQueue = blockQueue;
	}

	@Override
	public void run() {
		int num = 0;
		try {
			while (true) {
				blockQueue.put(num);
				System.out.println("produce:" + num);
				num++;
				Thread.sleep((int) (Math.random() * 100));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
