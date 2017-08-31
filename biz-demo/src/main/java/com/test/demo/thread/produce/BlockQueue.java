package com.test.demo.thread.produce;

/**
 * Created on 2017/8/31.
 */
public interface BlockQueue<E> {

	void put(E e) throws InterruptedException;

	E take() throws InterruptedException;
}
