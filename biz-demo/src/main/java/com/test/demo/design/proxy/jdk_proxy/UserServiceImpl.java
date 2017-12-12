package com.test.demo.design.proxy.jdk_proxy;

/**
 * 用户服务实现类
 * Created on 2017/12/12.
 */
public class UserServiceImpl implements UserService {
	@Override
	public void add() {
		System.out.println("add impl");
	}
}
