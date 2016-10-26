package com.test.demo.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * 泛型
 * 参考：effective java[26]
 * Created on 2016/10/26.
 */
public class GenericDemo {
    public static <E> E reduce(List<E> list, Function f, E initVal) {
        List<E> snapshot;
        synchronized (list) {
            snapshot = new ArrayList<E>();
        }
        E result = initVal;
        for(E e : snapshot) {
             result = (E) f.apply(e);
        }
        return result;
    }
}
