package com.test.demo.guava.event;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * Created by zhaohan on 2018/6/13.
 */
public class AsyncTestListener {

	@Subscribe
	@AllowConcurrentEvents
	public void listen(AsyncTestEvent event) {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("AsyncTestListener:" + event.getMsg());
	}
}
