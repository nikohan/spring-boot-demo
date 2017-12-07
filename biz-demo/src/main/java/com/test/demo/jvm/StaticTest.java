package com.test.demo.jvm;

/**
 * http://wiki.jikexueyuan.com/project/java-vm/class-loading-mechanism.html
 * Created on 2017/12/7.
 */
public class StaticTest {
	public static void main(String[] args){
		System.out.println(Child.m);
	}
}


class Super{
	public static int m = 11;
	static{
		System.out.println("执行了super类静态语句块" + m);
	}
}

class Father extends Super{
	public static int m = 33;
	static{
		System.out.println("执行了Father类静态语句块" + m);
	}
}

class Child extends Father{
	static{
		System.out.println("执行了Child类静态语句块");
	}
}