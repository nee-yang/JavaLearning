package Offer;

import java.util.HashMap;

public class Offer011 {
    /*
    给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
     */

    /*  此方法解题思路如下：若二进制串每个二进制为0，则pre累加-1否则累加1
        存在pre[i] = prep[i - 1] + result(nums[i])
        那么若num[j.....i]的二进制串含有相同数量的0和1，则存在：pre[j - 1] + 0 = pre[i]
     */
    public int findMaxLength(int[] nums) {
        int pre = 0;
        int length = 0;
        HashMap<Integer, Integer> preMap = new HashMap();
        preMap.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            pre += (nums[i] == 0 ? -1 : 1);
            /*  PS：最关键的地方，存在key时不更新hashmap */
            if (preMap.containsKey(pre)) {
                length = (i - preMap.get(pre)) > length ? (i - preMap.get(pre)) : length;
            } else {
                preMap.put(pre, i);
            }
        }
        return length;
    }

}
