package com.test.demo.guava.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.test.demo.utils.concurrent.ExecutorServiceObject;
import org.junit.Test;

/**
 * Created by zhaohan on 2018/6/13.
 */
public class AsyncEventTest {

	@Test
	public void asyncListen() {
		ExecutorServiceObject serviceObject = new ExecutorServiceObject("async-event",
				Runtime.getRuntime().availableProcessors() * 2);

		EventBus eventBus = new AsyncEventBus(serviceObject.create());
		AsyncTestListener listener = new AsyncTestListener();
		eventBus.register(listener);

		eventBus.post(new AsyncTestEvent("AsyncTestEvent"));

		System.out.println("asyncListen end");
	}
}
