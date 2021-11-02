package DataMining;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Lab1 {
    public static void main(String[] args) throws Exception {


        double minSupports[] = new double[3];
        minSupports[0] = 0.15;
        minSupports[1] = 0.2;
        minSupports[2] = 0.22;
        for (double minSupport : minSupports) {
            getFrequent(minSupport);
        }
    }

    public static void getFrequent(double minSupport) throws Exception{
        ArrayList<HashSet<Integer>> dataSet = new ArrayList<>();  //数据集
        ArrayList<HashSet<Integer>> frequentSet = new ArrayList<>();  //最终频繁项集
        Integer max = loadData(dataSet);
        ArrayList<HashSet<Integer>> L = new ArrayList<>();       // 此时为k1
        int k = 1;
        CreateF1(dataSet, L, minSupport, max);
        ArrayList<HashSet<Integer>> temp = deepCLone(L);
        while (temp.size() != 0) {
            frequentSet.addAll(deepCLone(temp));
            ArrayList<HashSet<Integer>> t = deepCLone(temp);
            ArrayList<HashSet<Integer>> Ltemp = new ArrayList<>();
            ArrayList<HashSet<Integer>> L1Temp =deepCLone(L);
            for (HashSet<Integer> number : L1Temp) {
                int i = 0;
                for (HashSet<Integer> tempNumber : t) {
                    HashSet<Integer> copySet = deepClone(tempNumber);
                    copySet.removeAll(number);
                    copySet.addAll(number);
                    if (copySet.size() == k + 1 &&  getSupport(copySet, dataSet, minSupport) == true) {
                        if (!Ltemp.contains(copySet)) {
                            Ltemp.add(deepClone(copySet));
                        }
                    }
                    copySet.clear();
                }
            }
            temp =  deepCLone(Ltemp);
            k++;
        }
        System.out.println("minsupport: " + minSupport + " frequent size :" + frequentSet.size());
        System.out.println(" frequent : " + frequentSet);
//        for (HashSet<Integer> set :frequentSet) {
//            System.out.println(set);
//        }
    }

    public static Integer loadData(ArrayList<HashSet<Integer>> dataSet) throws Exception{
        String filePath = "dataMining.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        Integer max = -1;
        while((strTmp = buffReader.readLine())!=null){
            String[] split = strTmp.split("  ");
            int[] array = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
            HashSet<Integer> integers = (HashSet<Integer>) Arrays.stream(array).boxed().collect(Collectors.toSet());
            max = Collections.max(integers) > max ? Collections.max(integers) : max;
            dataSet.add(integers);
        }
        return max;
    }

    public static void CreateF1(ArrayList<HashSet<Integer>> dataset, ArrayList<HashSet<Integer>> L, double minSupport, Integer max) {
        int frequent[] = new int[max];
        for (HashSet<Integer> list :dataset) {
            for (int i = 1; i <= max; i++) {
                if (list.contains(i)) {
                    frequent[i - 1] ++;
                }
            }
        }
        for (int i = 0; i < max; i++) {
            if ( dataset.size() * minSupport <= frequent[i]) {
                int finalI = i;
                L.add(new HashSet<Integer>(){{add(finalI + 1);}});
            }
        }
    }


    public static boolean getSupport(HashSet<Integer> tempNumber, ArrayList<HashSet<Integer>> dataSet, double minSupport) {
        int times = 0;
        for (HashSet<Integer> data : dataSet) {
            if (data.containsAll(tempNumber)) {
                times++;
            }
        }
//        System.out.println(times);
        if (minSupport * dataSet.size() <= times) {
//            System.out.println("get!");
            return true;
        }
        return false;
    }


    public static ArrayList<HashSet<Integer>> deepCLone(ArrayList<HashSet<Integer>> data) {
        ArrayList<HashSet<Integer>> result = new ArrayList<>();
        for (HashSet<Integer> tempList : data) {
            HashSet<Integer> tempResultList = new HashSet<>();
            for (Integer number : tempList) {
                tempResultList.add(number);
            }
            result.add(tempResultList);
        }
        return result;
    }

    public static HashSet<Integer> deepClone(HashSet<Integer> data) {
        HashSet<Integer> result = new HashSet<>();
        for (Integer tempNumber : data) {
            result.add(tempNumber);
        }
        return result;
    }
}

