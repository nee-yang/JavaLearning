package Offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offer007 {

    /*
    给定一个包含 n 个整数的数组nums，判断nums中是否存在三个元素a ，b ，c ，使得a + b + c = 0 ？请找出所有和为 0 且不重复的三元组。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        Arrays.sort(nums);
        int sum = 0;
        for (int  i = 0; i < nums.length - 2; i++) {
            // 加快判断
            if (nums[i] > 0) return result;
            int k = nums.length - 1;
            if (nums[k] < 0) return result;
            int j = i + 1;
            while (j < k) {
                sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> answer = new ArrayList<>();
                    answer.add(nums[i]);
                    answer.add(nums[j]);
                    answer.add(nums[k]);
                    result.add(answer);
                    // 去重
                    while (((i + 1) < nums.length - 2) && (nums[i + 1] == nums[i])) {
                        i++;
                    }
                    while (nums[j + 1] == nums[j] && (j + 1) < k) {
                        j++;
                    }
                    while (nums[k - 1] == nums[k] && (k - 1) > j) {
                        k--;
                    }
                    k--;
                    j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return result;
    }
}
