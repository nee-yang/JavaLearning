package Offer;

public class Offer001 {

    /*
        32位有符号整数，其数值范围是 [−2^31, 2^31−1]，即[-2147483648, 2147483647]
        因此会出现以下问题：
            【1】若a为最小值，b为-1，最终结果为正，会超出存储范围造成越界
            【2】若a为最小值，b不为-1，那么在进行Math.abs转换时也会导致越界，但负数存储范围大一个，因此可以把a和b
                转化为负数运算，那么此时循环逻辑也需要有所改动
    */
    public int divide(int a, int b) {
        if (a == 0) return 0;
        if (a == Integer.MIN_VALUE && b == -1) return Integer.MAX_VALUE;
        int temp = 0;
        // 异或运算，两数相同结果为0，否则为1
        int sign = (a > 0) ^ (b > 0) ? -1 : 1;
        if (a > 0) a = -a;
        if (b > 0) b = -b;
        while (a <= b) {    // 此时a和b都为负数，因此循环判断符号为<=
            int k = 1;
            int value = b;
            while (a <= value + value) {
                value += value;
                k += k;
            }
            a -= value;
            temp += k;
        }
        return sign == 1 ? temp : -temp;
    }


}
