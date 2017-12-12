package com.test.demo.design.proxy.static_proxy;

/**
 * 账户实现类
 * Created on 2017/12/12.
 */
public class AccountImpl implements Account {
	@Override
	public void query() {
		System.out.println("query impl");
	}

	@Override
	public void update() {
		System.out.println("update impl");
	}
}
