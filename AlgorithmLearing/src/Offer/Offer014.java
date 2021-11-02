package Offer;

public class Offer014 {
    /* 滑动窗口
     */
    // public boolean checkInclusion(String s1, String s2) {
    // int length1 = s1.length(), length2 = s2.length();
    // if (length1 > length2) return false;
    // int[] charDiff = new int[26];
    // for (int i = 0; i < length1; ++i) {
    //     --charDiff[s1.charAt(i) - 'a'];
    //     ++charDiff[s2.charAt(i) - 'a'];
    // }
    //     int differentNumber = 0;
    //     for (int num : charDiff) {
    //         if (num != 0) differentNumber++;
    //     }
    //     if (differentNumber == 0) return true;
    //     for (int i = length1; i < length2; ++i) {
    //         int add = s2.charAt(i) - 'a';
    //         int detract = s2.charAt(i - length1) - 'a';
    //         if (add == detract) continue;
    //         if (charDiff[add] == 0) ++differentNumber;
    //         ++charDiff[add];
    //         if (charDiff[add] == 0) --differentNumber;
    //         if (charDiff[detract] == 0) ++differentNumber;
    //         --charDiff[detract];
    //         if (charDiff[detract] == 0) --differentNumber;
    //         if (differentNumber == 0) return true;
    //     }
    //     return false;
    // }

    /* 双指针：难点在于左指针的移动
        核心：始终保证charDiff数组的元素都小于等于0，即s2索引left->right中的元素都存在于s1中且数目也不超过，那么只要每次right、left（可能不发生）
            更新后两者见元素长度为s1的长度即说明存在这样的连续字串
     */
    public boolean checkInclusion(String s1, String s2) {
        int length1 = s1.length(), length2 = s2.length();
        if (length1 > length2) return false;
        int[] charDiff = new int[26];
        for (int i = 0; i < length1; ++i) {
            --charDiff[s1.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < length2; ++right) {
            int position = s2.charAt(right) - 'a';
            ++charDiff[position];
            while (charDiff[position] > 0) {
                --charDiff[s2.charAt(left) - 'a'];
                left++;
            }
            if ((right - left + 1) == length1) return true;
        }
        return false;
    }
}
