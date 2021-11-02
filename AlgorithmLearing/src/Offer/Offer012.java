package Offer;

public class Offer012 {

    /*
    给你一个整数数组nums ，请计算数组的 中心下标 。
    数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。
    如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。
    如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。
     */

    /* 求全部元素和为sum，有两个变量sumDetract和sumAdd分别代表当前元素左右侧元素之和
        而后遍历每个元素，每次遍历，更新sumDetract和sumAdd，若两者相等则更新location

        改进版本：和为sum，从头遍历数组记录和为accumulator，则左侧元素和为accumulator，右侧为
            sum - accumulator - nums[i]
            condition: sum - accumulator - nums[i] = accumulaotr
                is: accumulator * 2 == sum - nums[i]
    */
    public int pivotIndex(int[] nums) {
        if (nums.length <= 1) return 0;
        // int sum = Arrays.stream(nums).sum();  # 速度慢
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int accumulaotr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (accumulaotr * 2 + nums[i] == sum) {
                return i;
            }
            accumulaotr += nums[i];
        }
        return -1;
    }

}
