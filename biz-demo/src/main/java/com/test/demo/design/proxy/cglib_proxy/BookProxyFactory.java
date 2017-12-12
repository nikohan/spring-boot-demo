package com.test.demo.design.proxy.cglib_proxy;

/**
 * Created on 2017/12/12.
 */
public class BookProxyFactory {
	public static BookImpl newInstance() {
		return new MyMethodInterceptor<BookImpl>()
				.getProxy(new BookImpl());
	}
}
