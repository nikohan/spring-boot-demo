package com.test.demo.concurrency;


/**
 * http://stackoverflow.com/questions/1588420/how-does-this-escape-the-constructor-in-java
 * @see java concurrency in practice : code list 3-7
 * Created by nikohan on 2016/12/8.
 */
public class ThisEscape {
    private boolean yes = false;

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                e.trigger(yes);//yes == false
            }
        });
        yes = true;
    }
}

class SafeListener {
    private final EventListener listener;
    private boolean yes = false;

    private SafeListener() {
        listener = new EventListener() {
            public void onEvent(Event e) {
                e.trigger(yes);
            }
        };
        yes = true;
    }

    public static SafeListener newInstance(EventSource source) {
        //The static factory can ensure the constructor of SafeListener is done to the end;
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }
}

class Client {
    public static void main(String[] args) {
        new ThisEscape(new EventSource());
        SafeListener.newInstance(new EventSource());
    }
}

class EventSource {
    void registerListener(EventListener listener) {
        Event event = new Event("demo");
        listener.onEvent(event);
    }
}

interface EventListener {
    void onEvent(Event e);
}

class Event {
    private String name;

    public Event(String name) {
        this.name = name;
    }

    public void trigger(boolean yes) {
        System.out.println("event " + name + " execute the constructor to the end : " + yes);
    }
}