package seLearning.StreamLearning;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * 流是可以并行执行的，当流中存在大量元素时，可以显著提升性能
 *  并行流底层使用的ForkJoinPool, 它由ForkJoinPool.commonPool()方法提供
 *  底层线程池的大小最多为五个 - 具体取决于 CPU 可用核心数：
 */


public class ParallelStreamLearning {
    public static void main(String[] args) {
        ForkJoinPool commomPool = ForkJoinPool.commonPool();
        System.out.println(commomPool.getParallelism());        // 7

        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort: %s <> %s [%s]\n",
                            s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));

    }
}
