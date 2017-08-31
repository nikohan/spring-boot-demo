package com.test.demo.thread.produce;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 使用wait和notify实现的阻塞队列
 * https://juejin.im/post/58aafc781b69e6006c0d9e6c
 * Created on 2017/8/31.
 */
public class WaitNotifyBlockQueue<E> implements BlockQueue<E> {

	private Queue<E> queue;

	private int limit = 10;

	public WaitNotifyBlockQueue(int limit) {
		this.limit = limit;
		queue = new ArrayDeque<>(limit);
	}

	public void put(E e) throws InterruptedException {
		synchronized(this) {
			while (queue.size() >= limit) {
				//队列满了，等待消费者处理完
				wait();
			}

			queue.add(e);
			notifyAll();
		}
	}

	public E take() throws InterruptedException {
		synchronized(this) {
			while (queue.isEmpty()) {
				//队列空了，等待生产者新增
				wait();
			}

			E e = queue.poll();
			notifyAll();
			return e;
		}
	}
}
