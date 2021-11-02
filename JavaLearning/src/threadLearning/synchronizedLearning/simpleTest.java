package threadLearning.synchronizedLearning;

public class simpleTest {
    public static void main(String[] args) {
        simpleTest test1 = new simpleTest();
        simpleTest test2 = new simpleTest();
        ThreadTest thread1 = new ThreadTest("线程1", test1);
        ThreadTest thread2 = new ThreadTest("线程2", test1);  //(1)
        //  ThreadTest thread2 = new ThreadTest("线程2", test2);  //(2)
        thread1.start();
        thread2.start();
    }

    public synchronized void method() {
        System.out.println(Thread.currentThread().getName()+" 获得锁");
        try {
            System.out.println("开始方法......");
            Thread.sleep(2000); //模拟做其他事情
            System.out.println("结束方法......");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadTest extends Thread {
    private simpleTest simpleTest;

    public ThreadTest(String name,simpleTest test) {
        super(name);
        this.simpleTest = test;
    }

    @Override
    public void run() {
        simpleTest.method();
    }
}
