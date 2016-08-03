package com.test.demo.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * service层，可以发布多个event
 * Created by zhaohan on 2016/8/3.
 */
@Component
public class DemoService implements ApplicationEventPublisherAware {

    ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void triggerEvent() {
        DemoEvent demoEvent = new DemoEvent(this, "DemoEvent");
        publisher.publishEvent(demoEvent);
    }

    public void triggerAsyncEvent() {
        AsyncEvent asyncEvent = new AsyncEvent(this, "AsyncEvent");
        //TODO 并没有实现异步
        publisher.publishEvent(asyncEvent);
    }
}
