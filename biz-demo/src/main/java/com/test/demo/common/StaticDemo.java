package com.test.demo.common;

/**
 * Created on 2017/11/17.
 */
public class StaticDemo {

	private void overridingTest() {

	}

	public static void staticTest() {

	}

	public static void main(String[] args) {

	}
}

//Java中是否可以覆盖(override)一个private或者是static的方法？
class SubStaticDemo extends StaticDemo {


//	@Override //编译出错
	private void overridingTest() {

	}

//	@Override //编译出错
	public static void staticTest() {

	}
}
