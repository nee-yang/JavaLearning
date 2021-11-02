package seLearning.reflectLearning;

public class Singleton {
    private static Singleton instance;
    private int age;

    private Singleton() {
       this.age = 10;
    }

    public static Singleton getInstance() {

        if (instance == null)
            instance = new Singleton();
        return instance;
    }

}
