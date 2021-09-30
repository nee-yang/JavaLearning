package seLearning.abstractClassWIthInterface;


/**
 * （1）语法层面上的区别：
 *          【1】抽象类可以提供成员方法的实现细节，接口中则只有public abstract方法
 * 			【2】抽象类的成员变量可以是任何类型的，接口中则只能有public static final
 * 			【3】接口中不能含有静态代码块和静态方法，抽象类中可以（除了三点，抽象类与普通类无甚区别）
 * 			【4】一个类只能继承一个抽象类，却能实现很多个接口
 *
 * （2）语法层面上的区别：
 *          【1】抽象类是对类的抽象，对事物的抽象，是对整个类整体进行抽象，包括属性、行为（即变量和方法）；
 *           而接口仅仅是对行为的抽象，即对类的局部进行抽象（即方法）。
 *
 *
 *          【2】
 */



interface Alarm {
    void alarm();
}


abstract class Door {
    abstract void open();
    abstract void close();
}


class AlarmDoor extends Door implements Alarm {
    @Override
    public void alarm() {
        System.out.println("Alarm!");
    }

    @Override
    void open() {
        System.out.println("open!");
    }

    @Override
    void close() {
        System.out.println("close");
    }
}


public class CompareDifference {
    public static void main(String[] args) {
        AlarmDoor alarmDoor = new AlarmDoor();
        alarmDoor.open();
        alarmDoor.close();
        alarmDoor.alarm();
    }
}
