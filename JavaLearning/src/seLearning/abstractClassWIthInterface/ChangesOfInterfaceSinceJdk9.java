package seLearning.abstractClassWIthInterface;


import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 *  *（2）JDK1.9：
 *  *      【1】允许定义私有方法和私有静态方法
 *  *
 *
 *      好处：改善接口内部的代码可重用性
 *          例子：若两个默认方法需要同一个方法，则私有接口（静态与非静态）方法允许它们共享代码，
 *          同时不把该私有方法暴露给它的实现类调用
 *        解释：java8若需要两个默认方法共享代码，则需要它们调用同一个默认方法/静态方法，但是默认方法/静态方法
 *             也可以被接口类调用或实现接口的实现类调用，便有暴露的风险
 *
 *  由一个例子展示这两个特性：分别计算奇数和偶数的和
 */


interface CustomCaculator {

    default int addEvenNumbers(int... nums) {
        publicMethod();
        return add(n -> n % 2 == 0, nums);
    }

    default int addOddNumbers(int... nums) {
        publicMethod();
        return add(n -> n % 2 != 0, nums);
    }

    static void staticMethod() {
        System.out.println("this is a static method of interface");
    }

    default void publicMethod() {
        System.out.println("this method is called by many default methods");
    }

    private int add(IntPredicate predicate, int... nums) {
        return IntStream.of(nums)  // java8 Stream流
                .filter(predicate) // java8 predicate及过滤器
                .sum(); //sum求和
    }
}


public class ChangesOfInterfaceSinceJdk9 implements CustomCaculator {
    public static void main(String[] args) {
        CustomCaculator demo = new ChangesOfInterfaceSinceJdk9();
        System.out.println(demo.addEvenNumbers(1,2,3,4,5,6,7,8,9));
        System.out.println(demo.addOddNumbers(1,2,3,4,5,6,7,8,9));
    }
}
