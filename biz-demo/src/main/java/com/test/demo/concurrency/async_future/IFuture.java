package com.test.demo.concurrency.async_future;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * http://lixiaohui.iteye.com/blog/2319738
 *
 * The result of an asynchronous operation.
 *
 * @param <V> 执行结果的类型参数
 * @author lixiaohui
 */
public interface IFuture<V> extends Future<V> {
	boolean isSuccess(); // 是否成功

	V getNow(); //立即返回结果(不管Future是否处于完成状态)

	Throwable cause();  //若执行失败时的原因

	boolean isCancellable(); //是否可以取消

	IFuture<V> await() throws InterruptedException; //等待future的完成

	boolean await(long timeoutMillis) throws InterruptedException; // 超时等待future的完成

	boolean await(long timeout, TimeUnit timeunit) throws InterruptedException;

	IFuture<V> awaitUnInterruptible(); //等待future的完成，不响应中断

	boolean awaitUnInterruptible(long timeoutMillis);//超时等待future的完成，不响应中断

	boolean awaitUnInterruptible(long timeout, TimeUnit timeunit);

	IFuture<V> addListener(IFutureListener<V> l); //当future完成时，会通知这些加进来的监听器

	IFuture<V> removeListener(IFutureListener<V> l);

}
