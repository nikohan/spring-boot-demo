package com.test.demo.guava.event;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * Created by zhaohan on 2018/6/13.
 */
public class MultipleEventsTest {
	@Test
	public void testMultipleEvents() throws Exception {

		EventBus eventBus = new EventBus("test");
		MultipleListener multiListener = new MultipleListener();

		eventBus.register(multiListener);

		eventBus.post(100);
		eventBus.post(200);
		eventBus.post(300);
		eventBus.post(800L);
		eventBus.post(800990L);
		eventBus.post(800882934L);

		System.out.println("LastInteger:"+multiListener.getLastInteger());
		System.out.println("LastLong:"+multiListener.getLastLong());
	}
}
