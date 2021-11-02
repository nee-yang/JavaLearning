package threadLearning;

public class test {
    public static void main(String[] args) {
        int a = 5____2;
        Thread b  = new Thread();
        System.out.println(1 + a);
    }

    public void get() {
        System.out.println("hh");
    }

    public void get(int a) {
        System.out.println("hh");
    }

    public int get(int a, int b) {
        System.out.println("hh");
        return 1;
    }
}

class test1 extends test {
    interface HelloWorld {
        public void greet();
    }

    static class s {

    }

    void say() {
        HelloWorld n = new HelloWorld() {
            @Override
            public void greet() {

            }
        };
    }

    void say1 () {
        class  d {

        }
    }
}


