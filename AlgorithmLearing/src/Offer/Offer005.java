package Offer;

public class Offer005 {

    //给定一个字符串数组words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，
    // 它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。

    /*
    类似于位图的思想，使用长度为words.length的int数组保存每个字符串每个字符的存在状态，int为32个比特位，完全可以保存下26个英文字母的存在状态
     */
    public int maxProduct(String[] words) {
        int[] characterDisplay = new int[words.length];
        // 把所有字符串每个字符的存在情况保存到int的二进制位中
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray())
                // 使用与运算把第i个英文字母对应的第i个二进制位(从后往前数第i个，下标从0开始)设置为1
                characterDisplay[i] |= (1 << (c - 'a')) ;
        }
        // 傻瓜式遍历整个数组找到最大的差值
        int maxLengthMultiple = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                // 使用与运算，若两个字符串没有相同的字符存在则结果为0，否则必不为0
                if ((characterDisplay[i] & characterDisplay[j]) == 0) {
                    // 更新最大值
                    maxLengthMultiple = Math.max(words[i].length() * words[j].length(), maxLengthMultiple);
                }
            }
        }
        return maxLengthMultiple;
    }
}
