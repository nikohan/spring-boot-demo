package com.test.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 *
 * Created by zhaohan on 2016/8/3.
 */
public class AsyncEvent extends ApplicationEvent {
    private String msg;

    public AsyncEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "DemoEvent{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
