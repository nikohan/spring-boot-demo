package com.test.demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * Created by zhaohan on 2016/8/3.
 */
@Component
public class DemoNotifier {
    @EventListener
    public void processDemoEvent(DemoEvent demoEvent) {
        demoEvent.display();
    }
}
