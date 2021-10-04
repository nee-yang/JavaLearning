package seLearning.copy;

import lombok.extern.java.Log;

import java.util.Arrays;

class People implements Cloneable {

    int age;
    Holder holder;

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class Holder {
        int holderValue;
    }
}


public class ArrayCpoy {
    public static void main(String[] args) {

        /*
        基本数据类型数组拷贝
         */
        System.out.println("--------Arrays.copyOf方法-------");
        int[] lNumbers1 = new int[5];
        int[] rNumbers1 = Arrays.copyOf(lNumbers1, lNumbers1.length);
        lNumbers1[0] = 1;
        boolean first = lNumbers1[0] == rNumbers1[0];  // false
        System.out.println("小杨逗比" + "lNumbers2[0]=" + lNumbers1[0] + ",rNumbers2[0]=" + rNumbers1[0]+"---"+first);
        System.out.println("--------clone方法-------");
        int[] lNumbers3 = new int[5];
        int[] rNumbers3 = lNumbers3.clone();
        rNumbers3[0] = 1;
        boolean second = lNumbers3[0] == rNumbers3[0];  // false
        System.out.println("小杨逗比" + "lNumbers3[0]=" + lNumbers3[0] + ",rNumbers3[0]=" + rNumbers3[0]+"---"+second);



         /*
        引用数据类型数组拷贝
         */
        People[] lNumbers2 = new People[5];
        lNumbers2[0] = new People();
        People[] rNumbers2 = lNumbers2;
        boolean firsts = lNumbers2[0].equals(rNumbers2[0]);
        System.out.println("小杨逗比" + "lNumbers2[0]=" + lNumbers2[0] + ",rNumbers2[0]=" + rNumbers2[0]+"--"+firsts);

        People[] lNumbers4 = new People[5];
        lNumbers4[0] = new People();
        People[] rNumbers4 = Arrays.copyOf(lNumbers4, lNumbers4.length);
        boolean seconds = lNumbers4[0].equals(rNumbers4[0]);
        System.out.println("小杨逗比" + "lNumbers4[0]=" + lNumbers4[0] + ",rNumbers4[0]=" + rNumbers4[0]+"--"+seconds);

        People[] lNumbers5 = new People[5];
        lNumbers5[0] = new People();
        People[] rNumbers5 = lNumbers5.clone();
        boolean third = lNumbers5[0].equals(rNumbers5[0]);
        System.out.println("小杨逗比" + "lNumbers5[0]=" + lNumbers5[0] + ",rNumbers5[0]=" + rNumbers5[0]+"--"+third);
    }

}
