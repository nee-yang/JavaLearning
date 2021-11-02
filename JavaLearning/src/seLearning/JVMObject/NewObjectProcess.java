package seLearning.JVMObject;

public class NewObjectProcess {

    public static void main(String[] args) {
        Person person=new Person();
        System.out.println(1);
    }
}

class Person {
    //静态变量
    public  static  int staicVariabl=1;
    //成员变量
    public   int  objVariabl;

    //静态初始代码块
    static {
        staicVariabl=2;
    }
    //对象初始化代码块
    {
        objVariabl=88;
    }

    //构造方法
    public Person() {
        objVariabl=99;
    }
}
