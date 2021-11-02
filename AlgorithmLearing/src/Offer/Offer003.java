package Offer;

public class Offer003 {

    //给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。


    /*
    最高有效位
    【1】若y位2的幂次方，则y&(y-1) = 0
    【2】对于正整数x，另y位满足条件y<=x的最大2的幂次方数，则有bits[x] = bits[x - y] + 1，因为只有一个二进制位的0变成了1，其他位置不变
     */
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        int highPosition = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i -1)) == 0) {
                highPosition = i;
            }
            bits[i] = bits[i - highPosition] + 1;
        }
        return bits;
    }

    /*
    最低有效位
    对于正整数 x，将其二进制表示右移一位，等价于将其二进制表示的最低位去掉，则若该数为偶数，则其最低位本就是0，则其值与x/2的比特数相同；若为奇数，则加一即可
     */
    public int[] countBits1(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i >> 1] + i % 2;
        }
        return bits;
    }

    /* 最低设置位
        y=x & (x−1)，则 y 为将 x 的最低设置位从 1 变成 0 之后的数，显然 0 <= y <=x,且bits[x] = bits[y] +1
         */
    public int[] countBits2(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[ i & (i - 1)] + 1;
        }
        return bits;
    }
}
