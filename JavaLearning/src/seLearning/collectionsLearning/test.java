package seLearning.collectionsLearning;

import java.util.*;

public class test {

    public static void main(String[] args) {
        LRUCache<String, String> lru = new LRUCache<>(5);
        lru.put("球员1", "杜兰特");
        lru.put("球员2", "表妹");
        lru.put("球员3", "库里");
        System.out.println(lru);
        lru.get("球员1");
        System.out.println(lru);
        lru.put("球员4", "一哥");
        lru.put("球员5", "汤姆");
        System.out.println(lru);
        lru.put("球员6", "格林");
        System.out.println(lru);
        TreeSet<Integer> set = new TreeSet<>();
        LinkedHashSet<String> stringLinkedHashSet = new LinkedHashSet<>();
    }

}

class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private int cacheSize;

    public LRUCache(int cacheSize) {
        super(16,0.75f,true);
        this.cacheSize = cacheSize;
    }

    /**
     * 判断元素个数是否超过缓存容量
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

}


