package serializableLearning;



/** [4] 可选的自定义序列化：
 *      1⃣️. 不需要序列化的字段：transien关键词修饰
 *      被此关键词修饰的属性，Java序列化时会忽略掉此字段，因此反序列化输出的对象，被transient关键词修饰
 *          的属性是默认值。其中引用默认值是null，基本类型值是0，boolean类型值是false
 *
 *     2⃣️. 可选的自定义序列化：重写writeObject()方法与readObject()方法
 *      可以控制序列化的方式，或者对序列化数据进行编码加密等
 *          通过重写writeObject与readObject方法，可以自己选择哪些属性需要序列化，哪些属性不需要
 *          如果writeObject使用某种规则序列化，则相应的readObject需要相反的规则反序列化，以便能正确反序列化出对象。这里展示对名字进行反转加密。
 *
 *
 *      3⃣️. 更彻底的自定义序列化
 *              ANY-ACCESS-MODIFIER Object writeReplace() throws ObjectStreamException;
 *                  writeReplace：在序列化时，会先调用此方法，再调用writeObject方法。此方法可将任意对象代替目标序列化对象
 *              ANY-ACCESS-MODIFIER Object readResolve() throws ObjectStreamException;
 *                  readResolve：反序列化时替换反序列化出的对象，反序列化出来的对象被立即丢弃。此方法在readeObject后调用。
 *
 *
 *      4⃣️. 强制自定义初始化：Externalizable
 *          通过实现Externalizable接口，必须实现writeExternal、readExternal方法。
 *      PS:Externalizable接口不同于Serializable接口，实现此接口必须实现接口中的两个方法实现自定义序列化，这是强制性的；
 *                  特别之处是必须提供pulic的无参构造器，因为在反序列化的时候需要反射创建对象
 *
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class Student implements Serializable  {
    private transient String name;
    private transient int age;
    public int height;
    private transient boolean singlehood;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", singlehood=" + singlehood +
                '}';
    }
}

class SelfDefined implements Serializable {
    private String name;
    private int age;
    private int nh;

    public SelfDefined(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        // 将名字反转写入二进制流
        out.writeObject(new StringBuffer(this.name).reverse());
        out.writeInt(age);
    }

    private void readObject(ObjectInputStream oin) throws IOException, ClassNotFoundException {
        // 将读出的字符串恢复过来
        this.name =( (StringBuffer) oin.readObject()).reverse().toString();
        this.age = oin.readInt();
    }

    @Override
    public String toString() {
        return "SelfDefined{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nh=" + nh +
                '}';
    }
}

class ThroughSelfDefined implements Serializable {
    private String name;
    private int age;

    public ThroughSelfDefined(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Object writeReplace() throws ObjectStreamException {
        ArrayList<Object> list = new ArrayList<>(2);
        list.add(this.name);
        list.add(this.age);
        return list;
    }


    @Override
    public String toString() {
        return "ThroughSelfDefined{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class ThroughSelfDefineds implements Serializable {
    private String name;
    private int age;

    public ThroughSelfDefineds(String name, int age) {
        this.name = name;
        this.age = age;
    }


    private Object readResolve() throws ObjectStreamException {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("barry", 32);
        return map;
    }

    @Override
    public String toString() {
        return "ThroughSelfDefined{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


class ExPerson implements Externalizable {
    private String name;
    private int age;


    // 必须实现，用于反序列化时利用反射创建对象
    public ExPerson() {
    }

    public ExPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //将name反转后写入二进制流
        StringBuffer reverse = new StringBuffer(name).reverse();
        System.out.println(reverse.toString());
        out.writeObject(reverse);
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        //将读取到的字符串反转后赋值给name实例变量
        this.name = ((StringBuffer) in.readObject()).reverse().toString();
        System.out.println(name);
        this.age = in.readInt();
    }

    @Override
    public String toString() {
        return "ExPerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class TransientUsage {
    public static void main(String[] args) throws Exception {
        System.out.println("----------transient--------------");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transient.txt"));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("transient.txt"));
        Student student = new Student("hh", 87);
        student.setHeight(245);
        System.out.println(student);        //Student{name='hh', age=87, height=245, singlehood=false}
        oos.writeObject(student);
        oos.close();
        Student s = (Student) ois.readObject();
        ois.close();
        System.out.println(s);      //Student{name='null', age=0, height=245, singlehood=false}

        System.out.println("---------自定义序列化--------------");
        SelfDefined selfDefined = new SelfDefined("self", 10);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("self.txt"));
        outputStream.writeObject(selfDefined);
        outputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("self.txt"));
        SelfDefined getSelf = (SelfDefined) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(getSelf);        //SelfDefined{name='self', age=10, nh=0}


        System.out.println("----------更彻底的自定义序列化--------------");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("through.txt"));
        ObjectInputStream objectInputStream1 = new ObjectInputStream(new FileInputStream("through.txt"));
        ThroughSelfDefined throughSelfDefined = new ThroughSelfDefined("through", 876);
        objectOutputStream.writeObject(throughSelfDefined);
        ArrayList list = (ArrayList) objectInputStream1.readObject();
        System.out.println(list);       //[through, 876]


        ObjectOutputStream objectOutputStreams = new ObjectOutputStream(new FileOutputStream("throughget.txt"));
        ObjectInputStream objectInputStreams = new ObjectInputStream(new FileInputStream("throughget.txt"));
        ThroughSelfDefineds throughSelfDefineds = new ThroughSelfDefineds("dfh", 54);
        objectOutputStreams.writeObject(throughSelfDefineds);
        HashMap map = (HashMap) objectInputStreams.readObject();
        System.out.println(map);       //{barry=32}



        System.out.println("----------强制性序列化--------------");
        ObjectOutputStream exoos = new ObjectOutputStream(new FileOutputStream("ex.txt"));
        ObjectInputStream exins = new ObjectInputStream(new FileInputStream("ex.txt"));
        exoos.writeObject(new ExPerson("ex", 98));
        exoos.close();
        ExPerson exPerson = (ExPerson) exins.readObject();
        exins.close();
        System.out.println(exPerson);       //ExPerson{name='ex', age=98}



    }
}
