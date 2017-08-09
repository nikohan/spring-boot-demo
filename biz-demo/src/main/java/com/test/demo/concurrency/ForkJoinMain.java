package com.test.demo.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created on 2017/8/9.
 */
public class ForkJoinMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String[] strings = {"a", "ah", "b", "ba", "ab", "ac", "sd", "fd", "ar", "te", "se", "te",
                "sdr", "gdf", "df", "fg", "gh", "oa", "ah", "qwe", "re", "ty", "ui"};
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinService<List<String>> forkJoinService = ForkJoinDemo.getInstance(stringList, 20);
        ForkJoinTask<List<String>> future = pool.submit(forkJoinService);

        System.out.println(future.get());
        pool.shutdown();
    }
}
