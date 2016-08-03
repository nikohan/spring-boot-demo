package com.test.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 *
 * Created by zhaohan on 2016/8/3.
 */
@Component
public class DemoNotifier {
    @EventListener  //Since Spring 4.2, you can use this annotation.
    public Demo1Event processDemoEvent(DemoEvent demoEvent) {
        System.out.println(demoEvent.toString());

        //触发第二段事件
        Demo1Event demo1Event = new Demo1Event(this, "Demo1Event");
        return demo1Event;
    }

    @EventListener
    public void processDemo1Event(Demo1Event demo1Event) {
        System.out.println(demo1Event.toString());
    }

    @EventListener
    @Async
    public Future<String> processAsyncEvent(AsyncEvent asyncEvent) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(asyncEvent.toString());

        return new AsyncResult<String>("async result");
    }
}
