package com.test.demo.thread.produce;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用数组实现的阻塞队列
 * Created on 2018/1/11.
 */
public class ArrayBlockingQueue<E> implements BlockQueue<E> {

	private E[] data;

	private int count;
	private int head;
	private int tail;

	private Lock lock;
	private Condition notFull;
	private Condition notEmpty;

	@SuppressWarnings("unchecked")
	public ArrayBlockingQueue(int limit) {
		this.data = (E[]) new Object[limit];
		lock = new ReentrantLock();
		notFull = lock.newCondition();
		notEmpty = lock.newCondition();
	}

	@Override
	public void put(E e) throws InterruptedException {
		lock.lock();
		try {
			if (count == data.length) {
				notFull.await();
			}

			data[head] = e;
			count++;
			notEmpty.signal();

			if (++head == data.length) {
				head = 0;
			}
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E take() throws InterruptedException {
		lock.lock();
		try {
			if (count == 0) {
				notEmpty.await();
			}

			E e = data[tail];
			count--;
			notFull.signal();

			if (++tail == data.length) {
				tail = 0;
			}
			return e;
		} finally {
			lock.unlock();
		}
	}
}
