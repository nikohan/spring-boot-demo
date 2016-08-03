package com.test.demo.event;

import org.springframework.context.event.EventListener;

/**
 *
 * Created by zhaohan on 2016/8/3.
 */
public class DemoNotifier {
    @EventListener
    public void processDemoEvent(DemoEvent demoEvent) {
        demoEvent.display();
    }
}
