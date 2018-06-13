package com.test.demo.guava.event;

import com.google.common.eventbus.Subscribe;

/**
 * 消息接受类
 * Created by zhaohan on 2018/6/13.
 */
public class EventListener {

	private int lastMessage = 0;

	@Subscribe
	public void listen(TestEvent event) {
		lastMessage = event.getMessage();
		System.out.println("Message:" + lastMessage);
	}

	public int getLastMessage() {
		return lastMessage;
	}
}
