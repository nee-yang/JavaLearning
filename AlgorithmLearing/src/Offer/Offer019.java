package Offer;

public class Offer019 {
    public boolean validPalindrome(String s) {
        if (s.length() <= 2) return true;
        int start = 0, end = s.length() - 1;
        boolean deleteFlag = false;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                if (deleteFlag) {
                    System.out.println("1: " + start + "--" + s.charAt(start) + "--" + end + "--" + s.charAt(end));
                    return false;
                }
                if (s.charAt(start + 1) == s.charAt(end)) {
                    start++;
                    deleteFlag = true;
                } else if (s.charAt(start) == s.charAt(end - 1)) {
                    end--;
                    deleteFlag = true;
                } else {
                    System.out.println("2: " + s.charAt(start) + "--" + s.charAt(end));
                    return false;
                }
            }
        }
        return true;
    }
}
