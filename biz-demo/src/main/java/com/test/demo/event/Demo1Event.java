package com.test.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 *
 * Created by zhaohan on 2016/8/3.
 */
public class Demo1Event extends ApplicationEvent {
    private String msg;

    public Demo1Event(Object source, String msg) {
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
