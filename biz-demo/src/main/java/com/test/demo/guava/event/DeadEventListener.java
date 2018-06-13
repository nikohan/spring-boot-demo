package com.test.demo.guava.event;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * Created by zhaohan on 2018/6/13.
 */
public class DeadEventListener {
	private boolean notDelivered = false;

	@Subscribe
	public void listen(DeadEvent event) {
		notDelivered = true;
	}

	public boolean isNotDelivered() {
		return notDelivered;
	}
}
