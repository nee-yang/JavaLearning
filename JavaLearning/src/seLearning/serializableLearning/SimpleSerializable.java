package seLearning.serializableLearning;

import java.io.*;

/** [1] 普通序列化：使用Serializable接口
 *
 *
 *  [3] 同一个对象序列化多次
 */

public class SimpleSerializable {

    public static void main(String[] args) throws Exception {

        System.out.println("----------【1】普通序列化：使用Serializable接口-----------");
        /** [1] 普通序列化：使用Serializable接口
         *
         */

        //序列化
        System.out.println("-----------------序列化----------------");
        try {
            ObjectOutputStream oos = new ObjectOutputStream     //创建一个对象输出流
                    (new FileOutputStream("object.txt"));
            Person person = new Person("9龙", 23);       //将对象序列化到文件s
            oos.writeObject(person);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------反序列化----------------");     //反序列化
        /*
        由反序列化的输出可以得到，反序列化并不会调用构造方法，反序列的对象是由JVM自己生成的对象，不通过构造方法生成
         */
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"));
            Person brady = (Person) ois.readObject();       //将文件s反序列化到对象
            System.out.println(brady);      // Person{name='9龙', age=23}
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("----------【3】同一个对象序列化多次-----------");
        /** [3] 同一个对象序列化多次
         *      Java序列化同一个对象多次，并不会将此对象序列化多次得到多个对象，每次得到的都是同一个对象
         */
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("times.txt"));
        Person person = new Person("路飞", 20);
        Teacher t1= new Teacher("leili", person);
        Teacher t2= new Teacher("hongfa", person);
        objectOutputStream.writeObject(t1);
        objectOutputStream.writeObject(t2);
        objectOutputStream.writeObject(person);
        objectOutputStream.writeObject(t2);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("times.txt"));
        Teacher teacher1 = (Teacher) objectInputStream.readObject();
        Teacher teacher2 = (Teacher) objectInputStream.readObject();
        Person person1 = (Person) objectInputStream.readObject();
        Teacher teacher3 = (Teacher) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(person1);        //Person{name='路飞', age=20}
        System.out.println(teacher1);       //Teacher{name='leili', person=Person{name='路飞', age=20}}
        System.out.println(teacher2);       //Teacher{name='hongfa', person=Person{name='路飞', age=20}}
        System.out.println(teacher3);       //Teacher{name='hongfa', person=Person{name='路飞', age=20}}
        System.out.println(teacher1 == teacher2);       //false
        System.out.println(teacher1.getPerson() == person1);        //true
        System.out.println(teacher2.getPerson() == person1);        //true
        System.out.println(teacher2 == teacher3);       //true
        System.out.println(teacher1.getPerson() == teacher2.getPerson());       //true




    }
}
