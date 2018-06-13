package com.test.demo.guava.event;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * Created by zhaohan on 2018/6/13.
 */
public class DeadEventListenersTest {
	@Test
	public void testDeadEventListeners() {

		EventBus eventBus = new EventBus("test");
		DeadEventListener deadEventListener = new DeadEventListener();
		eventBus.register(deadEventListener);

		eventBus.post(new TestEvent(200));
		eventBus.post(new TestEvent(300));

		System.out.println("deadEvent:"+deadEventListener.isNotDelivered());

	}
}
