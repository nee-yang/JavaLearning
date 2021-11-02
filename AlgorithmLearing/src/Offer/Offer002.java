package Offer;

public class Offer002 {

    public String addBinary(String a, String b) {
        int locationa = a.length() - 1;
        int locationb = b.length() - 1;
        StringBuffer ans = new StringBuffer();
        int up = 0;
        while (locationa >= 0 || locationb >= 0) {
            int numA = locationa >= 0 ? Character.getNumericValue(a.charAt(locationa--)) : 0;
            int numB = locationb >= 0 ? Character.getNumericValue(b.charAt(locationb--)) : 0;
            int sum = numA + numB + up;
            up = sum >= 2 ? 1 : 0;
            sum = sum >= 2 ? sum - 2 : sum;
            ans.append(sum == 0 ? '0' : '1');
        }
        if (up == 1) {
            ans.append('1');
        }
        ans.reverse();
        return ans.toString();
    }
}
