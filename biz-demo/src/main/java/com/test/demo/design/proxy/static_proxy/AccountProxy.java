package com.test.demo.design.proxy.static_proxy;

/**
 * 账户代理类
 * Created on 2017/12/12.
 */
public class AccountProxy implements Account {

	//委托对象，实际执行对象
	private Account account;

	public AccountProxy(Account account) {
		this.account = account;
	}

	@Override
	public void query() {
		System.out.println("before query");
		account.query();
		System.out.println("after query");
	}

	@Override
	public void update() {
		System.out.println("before update");
		account.update();
		System.out.println("after update");
	}
}
