package threadLearning.AQSlearning;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CountDownLatchTest {


    volatile List<Integer> integers = new ArrayList<>();

    void add(int i) {
        integers.add(i);
    }

    int getSize() {
        return integers.size();
    }

    public static void main(String[] args) {
        CountDownLatchTest t = new CountDownLatchTest();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 start");
            if(t.getSize() != 5){
                try {
                    countDownLatch.await();
                    System.out.println("t2 end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();

        new Thread(()->{
            System.out.println("t1 start");
            for (int i = 0;i<9;i++){
                t.add(i);
                System.out.println("add"+ i);
                if(t.getSize() == 5){
                    System.out.println("countdown is open");
                    countDownLatch.countDown();
                }
            }
            System.out.println("t1 end");
        },"t1").start();
    }

}

