package com.test.demo.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * Created on 2016/11/2.
 */
public class ListStreamDemo <T>{

    private List<T> data;

    private int maxSize;

    public ListStreamDemo() {
        data = new ArrayList<>();
        maxSize = 2000;
    }

    public ListStreamDemo(List<T> data, int maxSize) {
        this.data = data;
        this.maxSize = maxSize;
    }

    public int read(List<T> list) {
        return read(list, 500);
    }

    public int read(List<T> list, int num) {
        Iterator<T> i = data.iterator();
        int count = 0;
        while (i.hasNext() && count < num) {
            T item = i.next();
            list.add(item);
            data.remove(item);
            count++;
        }
        return count;
    }

    public boolean add(T item) {
        return data.add(item);
    }

    public boolean addAll(List<T> list) {
        if(data.size() + list.size() > maxSize) {
            return false;
        }
        return data.addAll(list);
    }
}
