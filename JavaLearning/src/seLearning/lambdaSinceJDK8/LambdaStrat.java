package seLearning.lambdaSinceJDK8;


import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * java8引入了Lambda表达式：
 *
 *  【1】 1⃣️  函数式接口引用
 *      只有函数式接口才可以写成这种形式，其他接口不能实现此功能
 *      函数式接口：只包含一个抽象方法的声明
 *  *  ps：default不是抽象方法，因此可以随意添加
 * 好处： 无需手动创建实现类完成此功能
 *         2⃣️ 类的方法引用
 *         3⃣️ 类的构造器引用
 *
 *  【2】  内置的函数式接口
 */


// 此注解可以忽略
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}


// ::引用普通方法
class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

//引用类的构造器
class Person {
    String firstName;
    String secondName;
    Person() {};

    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }
}

//定义一个工厂接口，用来生成 Person 类：
interface PersonFactory<P extends Person> {
    P create(String firstName, String secondName);
}

public class LambdaStrat {
    public static void main(String[] args) {

        /* 对比前后
         */
        // java8前对一个含有字符串对集合进行排序
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        //Lambda表达式
        Collections.sort(names, (String a, String b) -> {
            return a.compareTo(b);
        });
        //更省略的方式: 前提条件：只包含一行方法的代码块
        Collections.sort(names, (String a, String b) -> a.compareTo(b));
        //极致简单，因为List集合现在已经添加了sort方法，而且Java编译器能够根据类型推断机制判断出参数类型
        names.sort((a,b) -> a.compareTo(b));


        System.out.println("-----函数式接口引用，类的方法引用，类的构造器引用------");
        /**
         * 【1】函数式接口引用，类的方法引用，类的构造器引用
         */
        System.out.println("-----函数式接口引用--------");
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println("-------------");
        System.out.println(converted);    // 123
        /*利用java8新特性简化
            java8允许通过：：关键字来引用类的方法（静态方法与普通方法均可，此时在引用静态方法）或构造器
********    本质上相当于在创建接口时传入一个方法，而后调用接口中的方法相当于在调用此方法
         */
        System.out.println("-----类的方法引用------");
        Converter<String, Integer> converter1 = Integer::valueOf;
        System.out.println("-------------");
        System.out.println(converter1.convert("123"));
        // 引用类普通方法
        Something sth = new Something();
        Converter<String, String> converterSth = sth::startsWith;
        System.out.println("-------------");
        System.out.println(converterSth.convert("java"));
        //引用类的构造器
        System.out.println("-----类的构造器引用------");
        PersonFactory<Person> personPersonFactory = Person::new;
        System.out.println("-------------");
        Person person = personPersonFactory.create("s", "df");


        System.out.println("--------------内置的函数式接口--------------");
        /**
         * 【2】内置的函数式接口
         */


        System.out.println("--------------predicate断言--------------");
        /* [1]predicate断言
        可指定入参类型，并返回boolean值的函数式接口，内部提供了了一些带有默认实现的方法
         */
        Predicate<String> predicate = (str) -> str.length() > 0;
        System.out.println(predicate.test("df"));
        System.out.println(predicate.negate().test("df"));
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<Integer> integerPredicate = (num) -> num >= 0;
        System.out.println(integerPredicate.test(33));

        System.out.println("--------------Function--------------");
        /* [2]Function
        为其提供一个原料，它会生产一个最终的产品。通过它提供的默认方法，组合，链形处理（compose，andThen）
        是根据输入的参数对象做一些额外的判定、计算处理，返回另外一个指定的对象R
         */
        // 第一个参数为传入类型，第二个参数为传出类型
        // 这是一个简单的Lambda FUnction
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backString = toInteger.andThen(String::valueOf);
        System.out.println(backString.apply("234"));
        // 复杂的应用
        Function<Integer, Integer> doubleNum = e -> e * 2;
        Function<Integer, Integer> square = e -> e * e;
        /*
        ** andThen()
        *  返回一个先执行当前函数对象apply方法再执行after对象函数对象apply方法的函数对象
        *  default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t)); }
         */
        System.out.println(doubleNum.andThen(square).apply(2));


        /*
        ** compose()
        *  返回一个先执行before函数对象apply方法再执行当前h函数对象apply方法的函数对象
        *    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));}
         */
        System.out.println(doubleNum.compose(square).apply(2));

        System.out.println("--------------Supplier生产者--------------");
        /* [3]supplier
        不同于Function，不接受入参，直接为我们制定一个生产的结果，有点像生产者模式
         */











    }
}
