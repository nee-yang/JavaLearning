package Offer;

import java.util.HashSet;
import java.util.Set;

public class Offer017 {
    public String minWindow(String s, String t) {
        int[] times = new int[52];
        int length = s.length(), lengthSub = t.length();
        String empty = "";
        if (length < lengthSub) return empty;
        int lack = lengthSub;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < lengthSub; i++) {
            times[charToPosition(t.charAt(i))]++;
            set.add(t.charAt(i));
        }
        int start = 0, end = 0;
        int minLength = length + 1;
        int startLocation = -1;
        while (end < length) {
            char c = s.charAt(end);
            if ( set.contains(c) == true) {
                if ((times[charToPosition(c)]--) == 1) {
                    lack--;
                }
//                --times[charToPosition(c)];
//                lack--;
            }
            if (lack == 0) {
                if ((end - start + 1) < minLength) {
                    minLength = Math.min(minLength, end - start + 1); //更新长度
                    startLocation = start;
                    System.out.println("lack=0" + startLocation + "--" + minLength);
                }
                while (lack == 0 && start < length) {
                    if (set.contains(s.charAt(start)) == true) {
                        if ((times[charToPosition(s.charAt(start))]++) == 0) {
                            lack++;
                        }
                        start++;
                    } else {
                        System.out.println("keep:" + startLocation + "--" + minLength);
                        start++;
                        startLocation++;
//                        minLength--;
                        if ((end - start + 1) < minLength) {
                            minLength = Math.min(minLength, end - start + 1); //更新长度
                            startLocation = start;
                            System.out.println("lack=0" + startLocation + "--" + minLength);
                        }

                    }
                }
            }
            end++;
        }
        System.out.println(startLocation + "--" + minLength);
        return startLocation == -1 ? empty : s.substring(startLocation, startLocation + minLength);
    }

    public int charToPosition(char c) {
        int sub = c - 'A';
        return sub > 26 ? sub - 6 : sub;
    }
}
