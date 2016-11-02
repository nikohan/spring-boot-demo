package com.test.demo.concurrency;

import java.util.ArrayList;
import java.util.Collections;
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

    public void read(List<T> list) {
        read(list, 500);
    }

    public void read(List<T> list, int num) {
        Iterator<T> i = data.iterator();
        int count = 0;
        while (i.hasNext() && count < num) {
            T item = i.next();
            data.remove(item);
            list.add(item);
        }
    }

    public void add(T item) {
        data.add(item);
    }

    public void addAll(List<T> list) {
        data.addAll(list);
    }
}
