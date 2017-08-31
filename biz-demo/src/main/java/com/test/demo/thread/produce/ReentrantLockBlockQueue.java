package com.test.demo.thread.produce;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用ReentrantLock实现阻塞队列
 * https://juejin.im/post/58c16770ac502e0062c763d4
 * Created on 2017/8/31.
 */
public class ReentrantLockBlockQueue<E> implements BlockQueue<E> {

	private Queue<E> queue;

	private int limit = 10;

	private ReentrantLock lock;

	private Condition notFull;
	private Condition notEmpty;


	public ReentrantLockBlockQueue(int limit) {
		this.limit = limit;
		queue = new ArrayDeque<>(limit);
		lock = new ReentrantLock();
		notFull = lock.newCondition();
		notEmpty = lock.newCondition();
	}

	@Override
	public void put(E e) throws InterruptedException {
		lock.lock();
		try {
			while (queue.size() >= limit) {
				notFull.await();
			}
			queue.add(e);
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E take() throws InterruptedException {
		lock.lock();
		try {
			while (queue.isEmpty()) {
				notEmpty.await();
			}

			E e = queue.poll();
			notFull.signal();
			return e;
		} finally {
			lock.unlock();
		}
	}
}
