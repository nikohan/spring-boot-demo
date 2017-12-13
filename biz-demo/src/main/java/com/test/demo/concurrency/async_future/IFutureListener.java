package com.test.demo.concurrency.async_future;

/**
 * Created on 2017/12/13.
 */
public interface IFutureListener<V> {

	void onCompleted(IFuture<V> future) throws Exception;
}
