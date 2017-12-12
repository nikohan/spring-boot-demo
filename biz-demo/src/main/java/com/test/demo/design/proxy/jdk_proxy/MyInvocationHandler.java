package com.test.demo.design.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理类
 * Created on 2017/12/12.
 */
public class MyInvocationHandler<T> implements InvocationHandler {

	//委托对象，实际实现对象
	private T target;

	public MyInvocationHandler(T target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("-------before-----");
		Object res = method.invoke(target, args);
		System.out.println("-------after-----");
		return res;
	}

	@SuppressWarnings("unchecked")
	public T getProxy() {
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),this);
	}
}
