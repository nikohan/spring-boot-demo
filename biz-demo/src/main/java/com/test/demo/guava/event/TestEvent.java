package com.test.demo.guava.event;

/**
 *
 * 消息封装类
 * Created by zhaohan on 2018/6/13.
 */
public class TestEvent {
	private final int message;

	public TestEvent(int message) {
		this.message = message;
		System.out.println("event message:" + message);
	}

	public int getMessage() {
		return message;
	}
}
