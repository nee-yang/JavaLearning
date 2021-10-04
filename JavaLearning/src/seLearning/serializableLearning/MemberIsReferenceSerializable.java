package seLearning.serializableLearning;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** [2] 成员是引用的序列化
 *  如果一个可序列化的类的成员既不是基本类型，也不是string类型，那么这个引用类型也必须是可序列化的，否则会导致该类不能序列化
 *  该程序会报错 ：java.io.NotSerializableException: seLearning.serializableLearning.People
 */


class People {
    private String name;
    private int age;

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


public class MemberIsReferenceSerializable implements Serializable {

    private String name;
    private People people;

    public MemberIsReferenceSerializable(String name, People people) {
        this.name = name;
        this.people = people;
    }

    public static void main(String[] args) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("member.txt"));
            People people = new People("hh", 0);
            oos.writeObject(people);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
