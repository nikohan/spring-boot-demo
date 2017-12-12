package com.test.demo.design.proxy.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理类
 * Created on 2017/12/12.
 */
public class MyMethodInterceptor<T> implements MethodInterceptor {

	//委托对象，实际实现对象
	private T target;

	@Override
	public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy)
			throws Throwable {
		System.out.println("-------before-----");
		Object res = methodProxy.invokeSuper(target, args);
		System.out.println("-------after-----");
		return res;
	}

	/**
	 * 创建代理对象
	 *
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getProxy(T target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());

		// 回调方法
		enhancer.setCallback(this);

		// 创建代理对象
		return (T) enhancer.create();
	}
}
