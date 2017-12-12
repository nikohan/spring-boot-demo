package com.test.demo.design.proxy.cglib_proxy;

/**
 * Created on 2017/12/12.
 */
public class CglibProxyMain {
	public static void main(String[] args) {
		BookImpl bookProxy = BookProxyFactory.newInstance();
		bookProxy.add();
	}
}
