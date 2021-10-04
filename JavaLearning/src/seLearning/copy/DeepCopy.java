package seLearning.copy;


class Students implements Cloneable {
    // 对象引用
    private Subject subj;
    private String name;

    public Students(String s, String sub) {
        name = s;
        subj = new Subject(sub);
    }

    public Subject getSubject() {
        return subj;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    /**
     * 重写clone()方法
     *
     * @return
     */
    public Object clone() {
        // 深拷贝，创建拷贝类的一个新对象，这样就和原始对象相互独立
        Students s = new Students(name, subj.getName());
        return s;
    }

    public void setSubject(Subject subj) {
        this.subj = subj;
    }
}


public class DeepCopy {

    public static void main(String[] args) {
        // 原始对象
        Students student = new Students("杨充", "潇湘剑雨");
        System.out.println("原始对象: " + student.getName() + " -- "        //原始对象: 杨充 -- 潇湘剑雨
                + student.getSubject().getName());

        // 拷贝对象
        Students cloneStu =  (Students) student.clone();
        System.out.println("拷贝对象: " + cloneStu.getName() + " -- "       //原始对象: 杨充 -- 潇湘剑雨
                + cloneStu.getSubject().getName());

        // 原始对象和拷贝对象是否一样
        System.out.println("原始对象和拷贝对象是否一样: " + (student == cloneStu));      //false
        // 原始对象和拷贝对象的name属性是否一样
        System.out.println("原始对象和拷贝对象的name属性是否一样: " +       //true
                (student.getName() == cloneStu.getName()));
        // 原始对象和拷贝对象的subj属性是否一样
        System.out.println("原始对象和拷贝对象的subj属性是否一样: " +       //false
                (student.getSubject() == cloneStu.getSubject()));


        student.setName("小杨逗比");
        student.getSubject().setName("潇湘剑雨大侠");
        System.out.println("更新后的原始对象: " + student.getName() +       // 小杨逗比 - 潇湘剑雨大侠
                " - " + student.getSubject().getName());
        System.out.println("更新原始对象后的克隆对象: " + cloneStu.getName()        // 杨充 - 潇湘剑雨侠
                + " - " + cloneStu.getSubject().getName());
    }
}
