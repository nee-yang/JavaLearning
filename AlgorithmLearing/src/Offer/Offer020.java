package Offer;

public class Offer020 {
    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int left = i, right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                count++;
            }
            left = i;
            right = i + 1;
            if (right != s.length()) {
                while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                    count++;
                }
            }
        }
        return count;
    }
}
