package seLearning.reflectLearning;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) throws Exception {
        //正常的调用
        Apple apple1 = new Apple(5);
        apple1.setPrice(5);
        System.out.println("Apple price: " + apple1.getPrice());

        //通过反射调用获取类对象
        // 【1】根据类名获取类的对象实例
        Class clz = Class.forName("seLearning.reflectLearning.Apple");
        // 【2】根据Class对象实例获取Constructor对象
        Constructor appleConstructor = clz.getConstructor();
        // 【3】使用Constructor对象的newInstance方法获取反射类对象，即appleObject
        Object appleObject = appleConstructor.newInstance();

        // 如果要获取某个方法，需要经过如下步骤
        // 【1】根据方法名和CLass对象获取方法
        Method setPriceMethod = clz.getMethod("setPrice", int.class);
        // 【2】利用invoke调用方法
        setPriceMethod.invoke(appleObject, 40);
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("Apple price: " + getPriceMethod.invoke(appleObject));
    }
}

class Apple {
    private int price;

    public String name;

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private Apple(String name) {
        this.name = name;
    }

    public Apple(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
