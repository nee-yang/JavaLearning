
public class KMP {
    public static void main(String[] args) {
        String s = "aabaaac";
        String str = "aabaaabaaacaabaaac";
        if (s.length() == 1) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == s.charAt(0)) {
                    System.out.format("get: %c ", i);
                }
            }
            System.exit(1);
        }
        char[] pattern = s.toCharArray();
        char[] text = str.toCharArray();
        int[] prefix = new int[s.length()];
        prefix[0] = 0;
        Process(pattern, prefix);
//        for (int a : prefix) {
//            System.out.print(a + " ");
//        }
//        System.out.println("\n");
        int location = 0;  // 子串定位
        int offset = 0;     // 长串定位
        while (location < text.length) {
            if (pattern[offset] == text[location]) {
                if (offset == pattern.length - 1) {
                    System.out.println("get " + (location - pattern.length + 1));
                    offset = 0;
                } else {
                    location++;
                    offset++;
                }
            } else {
                offset = prefix[offset];
                if (offset == -1) { //说明传进次循环的offset为0，即第0个匹配失败，则需要将offset置0
                    offset = 0;
                    location++;
                }
            }
        }
        System.out.println("-1");
    }

    static void Process(char[] arr, int[] prefix) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[prefix[i - 1]]) {
                prefix[i] = prefix[i-1] + 1;
            } else if (arr[i] == arr[0]) {
                prefix[i] = 1;
                // 这里需要多做判断
                int k = 1;
                while (k < prefix[i -1]) {
                    if (arr[i - k] == arr[k]) {
                        prefix[i]++;
                        k++;
                    } else {
                        break;
                    }
                }
            } else {
                prefix[i] = 0;
            }
        }
        for (int i = prefix.length - 1; i > 0; i--) {
            prefix[i] = prefix[i - 1];
        }
        prefix[0] = -1;
    }

}
