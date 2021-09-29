package seLearning.constructorFunctionCalledSequence;


/**
 * 构造函数执行顺序，析构函数相反
 * 对象是由“底层向上”开始构造的。当建立一个对象，首先调用基类的构造函数，然后调用下一个派生类的构造函数。
 * PS：若在派生类的构造函数中调用了super（）函数，便不会去调用父类的无参构造函数
 */


class Person {
    private String name = "";
    private int age = 0;

    public Person() {
        System.out.println("Person类无参数构造函数");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Person类带2参数的构造函数");
    }
}

class Student extends Person {
    private String school;
    private String grade;

    public Student() {
        System.out.println("Student类无参数的构造函数");
    }

    public Student(String name, int age, String school) {
        super(name,age);
        this.school=school;
        System.out.println("Student类带3参数的构造函数");
    }

    public Student(String name, int age, String school, String grade) {
        this(name,age,school);
        this.grade = grade;
        System.out.println("Student类带4参数的构造函数");
    }
}

public class test {
    public static void main(String[] args) {
        System.out.println("无参数实例:");
        Student st1 = new Student();
        System.out.println("---------------------------");

        System.out.println("3参数实例:");
        Student st2 = new Student("zhangshan",25,"mit");
        System.out.println("---------------------------");

        System.out.println("4参数实例:");
        Student st3 = new Student("lisi", 24, "mit", "研究生");
    }
}
