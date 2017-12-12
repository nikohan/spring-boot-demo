package com.test.demo.design.proxy.jdk_proxy;

/**
 * 用户服务代理工厂
 * Created on 2017/12/12.
 */
public class UserProxyFactory {
	public static UserService newInstance() {
		return new MyInvocationHandler<UserService>(new UserServiceImpl())
				.getProxy();
	}
}
