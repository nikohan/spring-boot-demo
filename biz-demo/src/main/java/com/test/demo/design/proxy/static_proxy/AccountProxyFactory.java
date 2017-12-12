package com.test.demo.design.proxy.static_proxy;

/**
 * 代理工厂类
 * Created on 2017/12/12.
 */
public class AccountProxyFactory {
	public static Account newInstance() {
		return new AccountProxy(new AccountImpl());
	}
}
