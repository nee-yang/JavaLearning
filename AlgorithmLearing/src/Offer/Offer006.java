package Offer;

public class Offer006 {

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int start = 0;
        int end = numbers.length - 1;
        int sum = 0;
        while (start < end) {
            sum = numbers[start] + numbers[end];
            if (sum == target) {
                result[0] = start;
                result[1] = end;
                break;
            } else if (sum > target) {
                end--;
            } else
                start++;

        }
        return result;
    }
}
