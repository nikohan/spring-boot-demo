package com.test.demo.thread;

/**
 * 观察者
 * [effective java 235.page]
 * Created on 2016/10/27.
 */
public interface SetObserver<E> {

    //当一个element加到ObservableSet中，执行这个方法
    void added(ObservableSet<E> set, E element);
}
