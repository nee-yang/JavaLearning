package seLearning.lambdaSinceJDK8;


import java.lang.reflect.Array;
import java.util.*;

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
 *  【2】  Lambda 访问外部变量及接口默认方法
 *          包括：局部变量，成员变量，静态变量，接口的默认方法
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



        /**
         * 【2】Lambda 访问外部变量及接口默认方法
         */

        // 1⃣️ 访问局部变量





    }
}
