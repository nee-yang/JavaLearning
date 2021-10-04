package seLearning.copy;


import lombok.Builder;
import lombok.Data;

/**
 *
 */



class Subject {

    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class Student implements Cloneable {
    private String name;
    private Subject subject;        //引用类型

    public Student(String name, String sub) {
        this.name = name;
        this.subject = new Subject(sub);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //浅拷贝
        try {
            return super.clone();       //直接调用父类的clone方法
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}



public class ShallowCopy {
    public static void main(String[] args) throws CloneNotSupportedException {

        // 原始对象
        Student student = new Student("杨充", "潇湘剑雨");
        System.out.println("原始对象: " + student.getName() + " -- "        //原始对象: 杨充 -- 潇湘剑雨
                + student.getSubject().getName());

        // 拷贝对象
        Student cloneStu = (Student) student.clone();
        System.out.println("拷贝对象: " + cloneStu.getName() + " -- "       //原始对象: 杨充 -- 潇湘剑雨
                + cloneStu.getSubject().getName());

        // 原始对象和拷贝对象是否一样
        System.out.println("原始对象和拷贝对象是否一样: " + (student == cloneStu));      //false
        // 原始对象和拷贝对象的name属性是否一样
        System.out.println("原始对象和拷贝对象的name属性是否一样: " +       //true
                (student.getName() == cloneStu.getName()));
        // 原始对象和拷贝对象的subj属性是否一样
        System.out.println("原始对象和拷贝对象的subj属性是否一样: " +       //true
                (student.getSubject() == cloneStu.getSubject()));


        student.setName("小杨逗比");
        student.getSubject().setName("潇湘剑雨大侠");
        System.out.println("更新后的原始对象: " + student.getName() +       // 小杨逗比 - 潇湘剑雨大侠
                " - " + student.getSubject().getName());
        System.out.println("更新原始对象后的克隆对象: " + cloneStu.getName()        // 杨充 - 潇湘剑雨大侠
                + " - " + cloneStu.getSubject().getName());



    }
}
