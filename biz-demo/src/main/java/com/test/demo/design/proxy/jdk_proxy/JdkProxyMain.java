package com.test.demo.design.proxy.jdk_proxy;

/**
 * Created on 2017/12/12.
 */
public class JdkProxyMain {
	public static void main(String[] args) {
		UserService userProxy = UserProxyFactory.newInstance();
		userProxy.add();
	}
}
