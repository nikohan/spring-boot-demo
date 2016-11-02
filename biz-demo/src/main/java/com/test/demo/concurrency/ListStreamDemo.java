package com.test.demo.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 2016/11/2.
 */
public class ListStreamDemo <T> {

    private List<T> data;

    public ListStreamDemo() {
        data = new ArrayList<>();
    }

    public ListStreamDemo(List<T> data) {
        this.data = data;
    }

    public T get() {
        Iterator<T> i = data.iterator();
        if(i.hasNext()) {
            T item = i.next();
            data.remove(item);
            return item;
        }
        return null;
    }

    public void add(T item) {
        data.add(item);
    }
}
