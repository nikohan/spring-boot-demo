package com.test.demo.guava.event;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * http://www.cnblogs.com/peida/p/EventBus.html
 * Created by zhaohan on 2018/6/13.
 */
public class EventBusTest {

	@Test
	public void testReceiveEvent() {
		EventBus eventBus = new EventBus("test");
		EventListener listener = new EventListener();

		eventBus.register(listener);

		eventBus.post(new TestEvent(200));
		eventBus.post(new TestEvent(300));
		eventBus.post(new TestEvent(400));

		System.out.println("LastMessage:" + listener.getLastMessage());
	}
}
