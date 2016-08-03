package com.test.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 *
 * Created by zhaohan on 2016/8/3.
 */
public class DemoEvent extends ApplicationEvent {
    private String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void display() {
        System.out.println(msg);
    }
}
