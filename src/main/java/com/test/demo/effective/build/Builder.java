package com.test.demo.effective.build;

/**
 *
 * a builder for objects of type T
 *
 * Created by zhaohan on 2016/7/27.
 */
public interface Builder<T> {

    /**
     * generate object of T
     *
     * @return
     */
     T build();
}
