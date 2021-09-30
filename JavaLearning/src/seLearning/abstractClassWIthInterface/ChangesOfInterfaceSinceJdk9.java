package seLearning.abstractClassWIthInterface;


import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 *  *（2）JDK1.9：
 *  *      【1】 接⼝可以有私有方法
 *  *      【2】当然如果接口中的默认方法不能满足某个实现类需要，那么实现类可以覆盖默认方法。但静态函数不可以重写
 *
 *  由一个例子展示这两个特性：分别计算奇数和偶数的和
 */


interface CustomCaculator {

    default int addEventNumbers(int... nums) {

    }


    private int add(IntPredicate predicate, int... nums) {
        return IntStream.of(nums)  // java8 Stream流
                .filter(predicate) // java8 predicate及过滤器
                .sum(); //sum求和
    }
}


public class ChangesOfInterfaceSinceJdk9 {
}
