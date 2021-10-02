package seLearning.StreamLearning;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
        this.name = name;
    }
}

class Bar {
    String name;

    Bar(String name) {
        this.name = name;
    }
}



public class StreamHighUsage {
    public static void main(String[] args) {

        /* [1] java8 Stream流不可以复用，一旦调用任何终端操作，流就会关闭
         */
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        System.out.println(stream.anyMatch(s -> true )); // ok
//        System.out.println(stream.noneMatch(s -> true));  //exception
        /*克服此限制的做法：为每个想要执行的终端操作创建一个新的流链，
        如利用supplier来包装一下流，借助get()方法创建一个新的流
        这样相当于创建两个流
         */
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));
        System.out.println(streamSupplier.get().anyMatch(s ->true)); //true
        System.out.println(streamSupplier.get().noneMatch(s -> true)); //false


        System.out.println("--------------------Stream高级操作----------------------------");
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));


        System.out.println("--------------------[1]Collect----------------------------");
        /* 【1】Collect：终端操作，可以将流中的元素转变为另一个不同的对象，如一个List，Set，Map
                    可以接受入参为COllector，由四个不同的操作组成：
                           supplier（供应器）、accumlator（累加器）、combiner（累加器）和finisher（终止器）
         */
        System.out.println(persons);
        List<Person> flitered =
                persons
                        .stream()   //构建流
                        .filter(p -> p.name.startsWith("P"))
                        .collect(Collectors.toList());
        System.out.println(flitered);

        /*分组，返回值为Map类型数据
          PS:对于如何将流转换为 Map集合，我们必须指定 Map 的键和值。
            这里需要注意，Map 的键必须是唯一的，否则会抛出IllegalStateException 异常。 */
        System.out.println("-------分组，返回值为Map类型数据---------");
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));
        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

        //聚合操作,得到平均值
        System.out.println("-------聚合操作,得到平均值---------");
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));
        System.out.println(averageAge);
        //生成摘要统计
        System.out.println("--------生成摘要统计--------");
        IntSummaryStatistics intSummaryStatistics =
                persons
                        .stream()
                        .collect(Collectors.summarizingInt(p -> p.age));
        System.out.println(intSummaryStatistics);
        //下一个这个示例，可以将所有人名连接成一个字符串：
        System.out.println("-------将所有人名连接成一个字符串---------");
        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name) //提取名字
                .collect(Collectors.  // 以 In Germany 开头，and 连接各元素，再以 are of legal age. 结束
                        joining(" and ", "In Germany, "," are of legal age."));
        System.out.println(phrase);


        System.out.println("-------自定义收集器--------");
        /*
        我们希望将流中的所有人转换成一个字符串，包含所有大写的名称，并以|分割
            为了达到这种效果，我们需要通过Collector.of()创建一个新的收集器。
            同时，我们还需要传入收集器的四个组成部分：供应器、累加器、组合器和终止器。
         */
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner("|"),        // supplier 供应器
                        (j, p) -> j.add(p.name.toUpperCase()),      // accumlator 累加器
                        (j1, j2) -> j1.merge(j2),       // combiner 组合器
                        StringJoiner::toString);

        String names = persons
                .stream()
                .collect(personNameCollector);
        System.out.println(names);


        System.out.println("--------------------[2]FlatMap----------------------------");
        /* [2]flatmap:入参接受一个返回对象流的函数。
                FlatMap 能够将流的每个元素, 转换为其他对象的流。
                因此，每个对象可以被转换为零个，一个或多个其他对象，并以流的方式返回。
                之后，这些流的内容会被放入flatMap返回的流中。
         */
        List<Foo> foos = new ArrayList<>();

        // 创建 foos 集合
        IntStream
                .range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));
        // 创建 bars 集合
        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));

        List<Foo> fooList = new ArrayList<>();
        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
                        .forEach(f.bars::add))
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));


        System.out.println("--------------------[3]Reduce----------------------------");
        /* [2]规约操作可以将流的所有元素组合成一个结果,Java 8 支持三种不同的reduce方法。
         */

        /* 方法1
            将流中的元素规约成流中的一个元素
         */
        persons
                .stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println);

         /* 方法2
            接受标识值和BinaryOperator累加器
         */
        //此方法可用于构造一个新的 Person，其中包含来自流中所有其他人的聚合名称和年龄
        Person result = persons
                .stream()
                .reduce(new Person("", 0), (p1, p2) ->
                {
                    p1.age += p2.age;
                    p1.name += p2.name;
                    return p1;
                });
        System.out.format("name=%s;age=%s\n", result.name, result.age);

         /* 方法3
            接受三个参数：标识值，BiFunction累加器和类型的组合器函数BinaryOperator
         */
        //由于初始值的类型不一定为Person，我们可以使用这个归约函数来计算所有人的年龄总和：
        System.out.println("---------");
        Integer ageSum = persons
                .parallelStream()
                .reduce(0, (sum, p) -> {
                    System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
                    return sum +=p.age;
                }, (sum1, sum2) -> {
                    System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
                    return sum1 + sum2;
                });
        System.out.println(ageSum);
    }
}
