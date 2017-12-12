package com.test.demo.design.proxy.static_proxy;

/**
 *
 * Created on 2017/12/12.
 */
public class StaticProxyMain {
	public static void main(String[] args) {
		Account accountProxy = AccountProxyFactory.newInstance();
		accountProxy.query();
		accountProxy.update();
	}
}
