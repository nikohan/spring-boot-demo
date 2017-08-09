package com.test.demo.concurrency;

import java.util.List;
import java.util.stream.Collectors;

/**
 * http://www.cnblogs.com/jiyuqi/p/4547082.html
 * Created on 2017/8/9.
 */
public class ForkJoinDemo extends ForkJoinService<List<String>> {

    private static ForkJoinDemo forkJoinDemo;
    private int threshold;  //阈值
    private List<String> list; //待拆分List

    public ForkJoinDemo(List<String> list, int threshold) {
        this.threshold = threshold;
        this.list = list;
    }

    @Override
    protected List<String> compute() {
        // 当end与start之间的差小于阈值时，开始进行实际筛选
        if (list.size() < threshold) {
            return list.parallelStream().filter(s -> s.contains("a")).collect(Collectors.toList());
        } else {
            // 如果当end与start之间的差大于阈值时
            // 将大任务分解成两个小任务。
            int middle = list.size() / 2;

            List<String> leftList = list.subList(0, middle);
            List<String> rightList = list.subList(middle, list.size());

            ForkJoinDemo left = new ForkJoinDemo(leftList, threshold);
            ForkJoinDemo right = new ForkJoinDemo(rightList, threshold);

            // 并行执行两个“小任务”
            left.fork();
            right.fork();

            // 把两个“小任务”的结果合并起来
            List<String> join = left.join();
            join.addAll(right.join());

            return join;
        }
    }

    /**
     * 获取ForkJoinDemo实例
     * @param list 待处理List
     * @param threshold 阈值
     * @return ForkJoinDemo实例
     */
    public static ForkJoinService<List<String>> getInstance(List<String> list, int threshold) {
        if (forkJoinDemo == null) {
            synchronized (ForkJoinDemo.class) {
                if (forkJoinDemo == null) {
                    forkJoinDemo = new ForkJoinDemo(list, threshold);
                }
            }
        }
        return forkJoinDemo;
    }
}
