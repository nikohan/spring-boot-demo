package com.test.demo.concurrency;

import java.util.concurrent.RecursiveTask;

/**
 * Created on 2017/8/9.
 */
public abstract class ForkJoinService<T> extends RecursiveTask<T> {
    @Override
    protected abstract T compute();
}
