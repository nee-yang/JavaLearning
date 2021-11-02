package Offer;

public class Offer008 {

    /*
    给定一个含有n个正整数的数组和一个正整数 target 。
    找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     */

    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) return 0;
        int start = 0, end = 0;
        int accumulator = 0;
        int minLength = nums.length + 1;
        while (end < nums.length) {
            // if (accumulator >= target) {
            //     minLength = Math.min(end - start + 1, minLength);
            //     if (minLength == 1) return 1;
            //     accumulator -= nums[start];
            //     start++;
            // } else {
            //     if (++end <= nums.length- 1) {
            //         accumulator += nums[end];
            //     }
            // }
            accumulator += nums[end];
            while (accumulator >= target) {
                minLength = Math.min(end - start + 1, minLength);
                accumulator -= nums[start];
                start++;
            }
            end++;
        }
        return minLength == nums.length + 1 ? 0 :minLength;
    }
}
