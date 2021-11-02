package threadLearning.synchronizedLearning;


/*
单例：双重检查模式
 */
public class Singleton {
    private volatile static  Singleton uniqueInstance;  // volatile对象必须

    private Singleton(){

    }

    public static Singleton getUnique() {
        if (uniqueInstance == null) { // 没有实例化才进入加锁代码
            synchronized (Singleton.class) {    // 给类对象加锁
                if (uniqueInstance == null) {   // 再次判断
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}

class StaticSingleton {
    private StaticSingleton() {
    }

    public static StaticSingleton getInstance() {
        return inner.singleton;
    }

    private static class inner {
        private static final StaticSingleton singleton = new StaticSingleton();
    }
}
