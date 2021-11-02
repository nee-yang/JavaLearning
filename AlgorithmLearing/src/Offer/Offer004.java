package Offer;

public class Offer004 {


    /*
    位运算：每个int32个二进制位，对于每一个二进制位可能为0或1，那么对于所有数字对应的每个二进制位的和%3存在两种情况：
        【1】为1：则多出来的数该二进制位为1
        【2】为0，则多出来的数该二进制位为0
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i ++) {
            int times = 0;
            for (int num : nums)
                times += (num >> i) & 1;

            if (times % 3 != 0)
                //  '|'运算：如果相对应位都是 0，则结果为 0，否则为 1,此行代码相当于若多出来的数该二进制位为1，则将result加上该二进制为对应的2的幂次方
                //  如此累加下来，直到所有的二进制位累加完毕，result即为多出来的数
                result |= (1 << i);
        }
        return result;
    }
}
