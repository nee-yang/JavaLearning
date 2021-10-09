package seLearning.collectionsLearning;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Java中的集合包括三大类，它们是Set（集）、List（列表）和Map（映射），它们都处于java.util包中，
 *   Set、List和Map都是接口，它们有各自的实现类。
 *   Set的实现类主要有HashSet和TreeSet，
 *   List的实现类主要有ArrayList，
 *   Map的实现类主要有HashMap和TreeMap
 *   Collection是最基本的集合接口，声明了适用于JAVA集合的通用方法，list和set都继承自collection接口
 */

public class CollectionLearning {
    public static void main(String[] args) {

        System.out.println("-----------------Collection方法-------------------------");
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        ArrayList<Integer> integerList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        Vector<String> vector = new Vector<>();
        HashMap<String, Integer> map = new HashMap<>();
        Hashtable<String, String> hashtable = new Hashtable<>();
        HashSet<String> set = new HashSet<>();

        ConcurrentLinkedQueue stringLinkedList = new ConcurrentLinkedQueue<>();
        stringLinkedList.add("xf");
        stringLinkedList.add(34);
        stringLinkedList.add(34);
        stringLinkedList.stream().forEach(System.out::println);

        ConcurrentLinkedDeque d = new ConcurrentLinkedDeque();
        d.add("fsdf");
        d.add(-1);
        d.add(-1);
        d.stream().forEach(System.out::println);




        for(int i = 0; i < 10; i++) {
            integerArrayList.add(i);
        }
        for(int i = 0; i < 10; i++) {
            integerList.add(i*i);
        }
        // 从第四个开始插入集合元素
        integerArrayList.addAll(3,integerList);
        System.out.println(integerArrayList);
        integerList.clear();
        System.out.println(integerList.size());     //0
        System.out.println(integerArrayList.equals(integerList));       //false
        System.out.println(integerArrayList.contains(89));      //false
        System.out.println(integerArrayList.isEmpty());     //false
        integerArrayList.remove(1);
        /*
        list转array的三种方法
         */
        System.out.println("-----------------list转array的三种方法-------------------------");
        Integer[] nums1 = integerArrayList.toArray(new Integer[0]);      //方法1
        Integer[] nums2 = new Integer[integerArrayList.size()];
        integerArrayList.toArray(nums2);        //方法2⃣️
        Integer[] nums3 = new Integer[integerArrayList.size()];     //方法3
        Integer[] nums3_ = integerArrayList.toArray(nums3);
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();







    }



}
