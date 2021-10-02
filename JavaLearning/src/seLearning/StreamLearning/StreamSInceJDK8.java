package seLearning.StreamLearning;

import com.sun.jdi.Value;

import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *Stream是java8的新功能，极大简化了数据的处理
 *      我们可以使用 java.util.Stream 对一个包含一个或多个元素的集合做各种操作。
 *      这些操作可能是 中间操作 亦或是 终端操作。 终端操作会返回一个结果，而中间操作会返回一个 Stream 流。
 * PS：Map 不支持 Stream 流，Stream 流支持同步执行，也支持并发执行。
 *
 *
 * */



public class StreamSInceJDK8 {
    public static void main(String[] args) {


        /*
        【1】 filter过滤，这是一个中间操作，返参同样是一个stream流
         */
        System.out.println("--------------fliter过滤--------------");
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                //filter的入参是一个predicate，能够筛选出需要的集合元素，返参同样是一个stream流
                .filter((s) -> s.startsWith("a"))
                //foreach的入参是一个消费者，可以消费入参
                .forEach(System.out::println);



        /*
        【2】 Sorted排序，这同样是一个中间操作，返参同样是一个stream流
            我们可以传入一个comparator来自定义排序，否则将使用默认的排序规则
            PS:Sorted排序并不会对stringCollection本身做出改变，集合内部元素保持不变，且顺序依旧
         */
        System.out.println("--------------Sorted排序-------------");
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);


         /*
        【3】 Map转换，这同样是一个中间操作，返参同样是一个stream流
            能够帮我们将List中的每一个元素做功能处理
            PS:Map转换并不会对stringCollection本身做出改变，集合内部元素保持不变，且顺序依旧
         */
        System.out.println("--------------Map转换-------------");
        stringCollection
                .stream()
                // map的入参是一个function，为其提供一个原料，它会生产一个最终的产品。通过它提供的默认方法，组合，链形处理（compose，andThen）
                .map(String::toUpperCase)
                .sorted((a,b) ->b.compareTo(a))
                .forEach(System.out::println);

        /*
        【4】 Match匹配，用来做匹配操作，返回值是一个boolean类型
            借助match可以很方便地验证一个集合中是否存在某个类型的元素
         */
        System.out.println("--------------Match匹配-------------");
        boolean anyStartWithA =
                stringCollection
                        .stream()
                        //入参是一个predicate类型
                        .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartWithA);
        boolean allStartWithA =
                stringCollection
                        .stream()
                        //入参是一个predicate类型
                        .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartWithA);
        boolean noneStratWithZ =
                stringCollection
                        .stream()
                        .noneMatch(s -> s.startsWith("z"));
        System.out.println(noneStratWithZ);


        /*
        【5】 Count计数，终端操作，能够统计stream流中的元素总数，返回值是long类型
         */
        System.out.println("--------------Count计数-------------");
        long startWithB =
                stringCollection
                        .stream()
                        .filter(s -> s.startsWith("b"))
                        .count();
        System.out.println(startWithB);


         /*
        【6】 Reduce，通过入参的function，将list归约成一个值，返回值是optional类型
         */
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
        System.out.println(reduced.get());
        List<String> nullCollection = new ArrayList<>();
        System.out.println("test");
        Optional<String> reducedNull =
                nullCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);
        System.out.println(reducedNull.orElse("def"));



        System.out.println("--------------------Map集合-----------------------------");
        /** Map不支持stream流
         * Map 接口并没有像 Collection 接口那样，定义了 stream() 方法。
         * 但是，我们可以对其 key, values, entry 使用 流操作，
         * 如 map.keySet().stream(), map.values().stream() 和 map.entrySet().stream()
         */
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            // 与老版不同的是，putIfAbent() 方法在 put 之前，
            // 会判断 key 是否已经存在，存在则直接返回 value, 否则 put, 再返回 value
            map.putIfAbsent(i, "val" + i);
        }
        // forEach 可以很方便地对 map 进行遍历操作
        map.forEach((num, val) -> System.out.println(val));
        //入参是一个function，当 key 存在时，才会做相关处理
        map.computeIfPresent(3, (a, b) -> a + "-" + b);
        System.out.println(map.get(3));  //3-val3
        //做删除处理
        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9)); //false
        //computeIfAbsent(), 当 key 不存在时，才会做相关处理
        //如下：先判断 key 为 23 的元素是否存在，不存在，则添加
        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23)); //true
        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3)); //3-val3

        //删除操作，只有当key和value完全匹配的时候才会执行删除操作
        map.remove(3, "val3");
        System.out.println(map.get(3));  //3-val3
        map.remove(3,"3-val3");
        System.out.println(map.get(3));  //null

        //查找操作，提供了带有默认值的getOrDefault()方法，若找不到key，则返回默认值
        System.out.println(map.getOrDefault(42, "not found")); //not found

        //合并操作、
        // 若key不存在，则会先添加元素
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));  //val9
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));  //val9concat


        System.out.println("--------------------新的日期API-----------------------------");

        /*  [1]clock
        Clock 提供对当前日期和时间的访问。我们可以利用它来替代 System.currentTimeMillis() 方法。
        另外，通过 clock.instant() 能够获取一个 instant 实例， 此实例能够方便地转换成老版本中的 java.util.Date 对象
         */
        System.out.println("--------------------Clock-----------------------------");
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        Instant instant = clock.instant();
        Date date = Date.from(instant);

        System.out.println("--------------------IntStreams.range()以替代常规for循环-----------------------------");
        IntStream.range(1,4)
                .forEach(System.out::println);


    }
}
