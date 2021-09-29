package seLearning.stringRelated;

public class StringVariable {
    public static void main(String[] args) {

        //StringBuffer,StringBuilder不同于String，其长度可变
        StringBuilder stringBuilder = new StringBuilder("abc");
        stringBuilder.append("p")
                .append(new char[]{'q'})
                .deleteCharAt(2)
                .insert(2,"abc");
        String s  = stringBuilder.toString();
        System.out.println(stringBuilder);


        /*
        String拼接操作被编译器优化成"abcdefg"，与与String s1 = "abcdefg"; 是等价的
        这种优化减少了拼接时的消耗，甚至比StringBuffer更加高效
         */
        String s1 = "ab" + "cd" +"efg";

        /*
        s2的拼接编译器会自动创建一个StringBuffer来构建字符串，等价于：
        StringBuilder sb = new StringBuilder();
        sb.append("hello");
        sb.append(s1);
        String s2 = sb.toString();
        但由于这种行为每一次发生都会导致StringBuffer的一次创建行为，因此只适用于小批量操作，
        大批量操作时还是直接创建一个StringBuffer调用append更加高效
         */
        String s2 = "hello" + s1;
        Object object =  new Object();
        String s3 = s2 + object;
    }
}
