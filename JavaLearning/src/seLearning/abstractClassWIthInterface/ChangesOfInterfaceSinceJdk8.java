package seLearning.abstractClassWIthInterface;


/**
 *（1）JDK1.8：
 *     【1】 接⼝可以有默认⽅法和静态⽅法功能
 *     【2】当然如果接口中的默认方法不能满足某个实现类需要，那么实现类可以覆盖默认方法。但静态函数不可以重写
 *
 */

interface JDK8Interface {

    //必须通过接口类调用
    static void staticMethod() {
        System.out.println("接口中的静态方法");
    }

    //必须通过实现类的对象调用
    default void defaultMethod() {
        System.out.println("接口中的默认方法");
    }

}

interface sameMethodInterface {

    default void defaultMethod() {
        System.out.println("接口中的默认方法！！！daze！！！");
    }


}

class JDK8InterfaceImpl implements sameMethodInterface, JDK8Interface {

    // jdk8引入的静态方法和默认方法都不是抽象方法，因此可以不重写

    // 当然如果接口中的默认方法不能满足某个实现类需要，那么实现类可以覆盖默认方法。但是不可以加default修饰符
    // 但如果该类实现了多个接口，且这些接口中有着完全相同的默认方法，则必须重写此默认方法

    @Override
    public void defaultMethod() {
        JDK8Interface.super.defaultMethod();
        sameMethodInterface.super.defaultMethod();
        System.out.println("重写此默认方法了！");
    }
}

public class ChangesOfInterfaceSinceJdk8 {
    public static void main(String[] args) {

        JDK8InterfaceImpl inter = new JDK8InterfaceImpl();


        //jdk8 静态方法必须通过接口类调用
        JDK8Interface.staticMethod();

        //jdk8 默认方法必须通过实现类的对象调用
        inter.defaultMethod();



    }
}
