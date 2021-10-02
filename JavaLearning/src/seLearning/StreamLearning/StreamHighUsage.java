package seLearning.StreamLearning;

import java.util.Locale;
import java.util.stream.Stream;

public class StreamHighUsage {
    public static void main(String[] args) {

        System.out.println("-------------Stream流的处理顺序-----------");
        /** 【1】Stream流的处理顺序
         * 中间操作有个重要特性：延迟性，当且仅当存在终端操作时，中间操作操作才会被执行
         */


        // 输出顺序：输出的结果却是随着链条垂直移动的
        //  fliter-forEach-fliter-forEach如此循环5次
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("fliter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));
        System.out.println("------------------------");
        //原因：如下代码仅需执行两次map，若是水平执行，则会执行更多的次数
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s-> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s-> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });


        System.out.println("-------------中间操作顺序的重要性-----------");
        /*
        【1】不那么高效的方式
         */
        System.out.println("-------------【1】不那么高效的方式-----------");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("fliter: " +s);
                    return s.startsWith("A");
                })
                .forEach(s ->  System.out.println("for each: " +s));

        /*
        【2】改进后的方式
         */
        System.out.println("-------------【2】改进后的方式-----------");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("fliter: " +s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s ->  System.out.println("for each: " +s));

         /*
        【3】添加sorted()
            PS:sorted是水平执行的
            对此作出的优化同样是把fliter加到最前方
         */
        System.out.println("-------------添加sorted()-----------");
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2); // 排序
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a"); // 过滤出以 a 为前缀的元素
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase(); // 转大写
                })
                .forEach(s -> System.out.println("forEach: " + s)); // for 循环输出



    }
}
