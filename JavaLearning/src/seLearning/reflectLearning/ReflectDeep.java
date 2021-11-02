package seLearning.reflectLearning;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectDeep {
    public static void main(String[] args) throws Exception {

        /*  (1)get class */
        // [1]by the object instance of the class
        Apple appled = new Apple(3);
        Class clz = appled.getClass();

        // [2] by the name of class
        Class clz1 = Class.forName("seLearning.reflectLearning.Apple");

        // [3] when konw the class before compile
        Class clz2 = Apple.class;


        /* (2) judge one object is an intsance of a class or not */
        // [1] instance keywords
//        System.out.println(appled instanceof Apple);        //true

        // [2] use method isInstance() of CLass object(Native method)
//        System.out.println(Apple.class.isInstance(appled));     //true


        /*  (3)create class object by reflext */
        // [1] use method newInstance() of Class Objetct
//        Apple apple1 = (Apple) clz.newInstance(4);    //报错，此方法只能使用默认的无参构造函数

        // [2] use method newInstance() of Constructor Object
        Constructor constructor = clz1.getConstructor(int.class);
        Apple apple = (Apple) constructor.newInstance(9);       //此方法可以使用带参数的构造方法,但上行构造器的获取需要传入参数的类


        /* (4) get the attribute, method and constructor of class by reflect */
        // [1] get attributes
        // 1. getFields() can only get public attribute
        Field[] fields = clz.getFields();
        Arrays.stream(fields).forEach(field -> System.out.println("attribute: " + field.getName()));
        // 2. getDeclaredFields() can get all attributes
        Field[] fields1 = clz.getDeclaredFields();
        Arrays.stream(fields1).forEach(field -> System.out.println("declared attribute: " + field.getName()));

        // [2] get methods
        Method[] methods = clz.getMethods();
        Arrays.stream(methods).forEach(method -> System.out.println("method: " + method));
        Method[] methods1 = clz.getDeclaredMethods();
        Arrays.stream(methods1).forEach(method -> System.out.println("declared methid: " + method));

        // [3] get constructors
        Constructor[] constructors = clz.getConstructors();
        Arrays.stream(constructors).forEach(constructor1 -> System.out.println("constructor: " + constructor1));
        Constructor[] constructors1 = clz.getDeclaredConstructors();
        Arrays.stream(constructors1).forEach(constructor1 -> System.out.println("declared constructor: " + constructor1));



    }
}
