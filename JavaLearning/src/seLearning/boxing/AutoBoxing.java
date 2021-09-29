package seLearning.boxing;

/*
装箱：把基本类型转换成其对应的引用类型的过程
基本类型： char(16bits)  ---> Character
         bool          ---> Boolean
         byte(8bits)   ---> Byte
         short         ---> Short
         int           ---> Integer
         long          ---> Long
         float         ---> Float
         double        ---> Double
 */


public class AutoBoxing {

    public static void main(String[] args) {

        //自动装箱
        Integer i = 10;

        //此时不会触发自动装箱的过程，执行效率一半低于第一种
        Integer integer = new Integer(10);
        //拆箱
        int n= i;


        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        /*
        原因在于若被装箱的数在-128到127间，便会返回指向已存在对象的的引用，否则创建一个新的Integer对象并返回
         */
        System.out.println(i1==i2);  //true
        System.out.println(i3==i4);  //false


        /**
         *  注意，Integer、Short、Byte、Character、Long这几个类的valueOf方法的实现是类似的
         * 　　　　　Double、Float的valueOf方法的实现是类似的。
         *  原因：在某个范围内整数的个数
         */

        Double d1 = 100.0;
        Double d2 = 100.0;
        Double d3 = 200.0;
        Double d4 = 200.0;
        System.out.println(d1==d2);  //fasle
        System.out.println(d3==d4);  //false


        Short s = 1; // -127~128


        /**
         * public static Boolean valueOf(boolean b) {
         *         return (b ? TRUE : FALSE);
         *     }
         *
         *
         *  public static final Boolean TRUE = new Boolean(true);
         *  public static final Boolean FALSE = new Boolean(false);
         *
         **/
        Boolean b1 = false;
        Boolean b2 = false;
        Boolean b3 = true;
        Boolean b4 = true;
        System.out.println(b1 == b2);   //true
        System.out.println(b3 == b4);   //true



    }
}