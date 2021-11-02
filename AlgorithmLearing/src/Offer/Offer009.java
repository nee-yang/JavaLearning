package Offer;

public class Offer009 {

    /*
    给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
     */

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums.length == 0 || k <= 1) return 0;
        if (nums.length == 1) return nums[0] < k ? 1 : 0;
        int number = 0;
        int currentMulti = 1;
        int right = 0;
        int left = 0;
        while (right < nums.length) {
            currentMulti *= nums[right];
            while (currentMulti >= k && left <= right) {
                currentMulti /= nums[left++];
            }
            if (left <= right) {
                number += right - left + 1;
            }
            right++;
        }
        return number;
    }

}
