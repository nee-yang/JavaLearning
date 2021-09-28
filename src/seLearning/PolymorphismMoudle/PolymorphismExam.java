package seLearning.PolymorphismMoudle;

/**
 * 被引用对象的类型而不是引用变量的类型决定了调用谁的成员方法，
 * 但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法。
 * 其实在继承链中对象方法的调用存在一个优先级：
 * this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。
 */
class A {
    public String show(D obj) {
        return ("A and D");
    }

    public String show(A obj) {
        return ("A and A");
    }

}

class B extends A{
    public String show(B obj){
        return ("B and B");
    }

    public String show(A obj){
        return ("B and A");
    }
}

class C extends B{

}

class D extends B{

}

public class PolymorphismExam {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        /*
        易于理解，没有向上转型，因此a1仅能使用自身的方法
         */
        System.out.println("1--" + a1.show(b));
        System.out.println("2--" + a1.show(c));
        System.out.println("3--" + a1.show(d));


        /*
        出现错误：4
        解析：其实在继承链中对象方法的调用存在一个优先级：
               this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。
         */
        System.out.println("4--" + a2.show(b));
        /*
        此时，this代表A（因为a2是A类型的引用变量），A中没有此函数，且A无超类，因此this.show((super)O)
        此时this为A，super（o）为B/A，此时A中找到了show(A obj)，但a2是B类的一个引用且B中重写了此方法，
        最终调用B中的show(A obj)方法
        */
        System.out.println("5--" + a2.show(c));
        System.out.println("6--" + a2.show(d));


        System.out.println("7--" + b.show(b));
        System.out.println("8--" + b.show(c));
        System.out.println("9--" + b.show(d));
    }
}
