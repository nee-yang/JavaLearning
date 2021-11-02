package Offer;

import java.util.HashMap;

public class Offer010 {



    /*
    给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
    提示:
        1 <= nums.length <= 2 * 10 ^ 4
        -1000 <= nums[i] <= 1000
        -10 ^ 7 <= k <= 10 ^ 7
     */
    /*
    由于存在负值无法使用滑动窗口
    使用前缀+哈希表的方法使得时间复杂度降到O(n)：以空间换时间
        前缀：前几个元素的和
        对于序列{0,1...i}，存在pre[i] = pre[i-1] + nums[i]
        那么若序列{j,j+1...i}的和为k，则存在pre[j-1] + k = pre[i]，即pre[i] - k = pre[j-1]
        则以前缀为key，前缀出现次数建立哈希表，没扫描到一个点，则查看Key:pre - k是否存在，若存在则其出现次数即以该点为结尾的和为k的连续子序列的个数
            而后有则自增，无则新建即可
     */
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 1) return nums[0] == k ? 1 : 0;
        HashMap<Integer, Integer> hash = new HashMap();
        int preSum = 0;
        int times = 0;;
        // 若无这个初始化，则在连续数组第一次出现的时候会因为不存在key为preSum-k即0而略过这一次
        hash.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            // if (hash.containsKey(preSum - k))
            //     times += hash.get(preSum - k);
            times += hash.getOrDefault(preSum - k, 0);
            hash.put(preSum, hash.getOrDefault(preSum, 0) + 1);
        }
        return times;
    }
}
