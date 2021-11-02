package algorithm.bitMap;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BitMap {

    public static int length = 131072;


    // index处对应第值为2的index次方幂，对应的二进制为从后往前算index处的值为1，其他位的值为0
    public static int[] PositionTable = {
            // 16进制表示
            0x00000001, 0x00000002, 0x00000004, 0x00000008,
            0x00000010, 0x00000020, 0x00000040, 0x00000080,
            0x00000100, 0x00000200, 0x00000400, 0x00000800,
            0x00001000, 0x00002000, 0x00004000, 0x00008000,
            0x00010000, 0x00020000, 0x00040000, 0x00080000,
            0x00100000, 0x00200000, 0x00400000, 0x00800000,
            0x01000000, 0x02000000, 0x04000000, 0x08000000,
            0x10000000, 0x20000000, 0x40000000, 0x80000000};


    public static void main(String[] args) throws IOException {
        // 每个int类型32bit，可存放32个数据，其中0～31放在第一个桶中，
        int intAmount = (length >> 5) + ((length & 31) > 0 ? 1 : 0);
        int[] bitMap = new int[intAmount];
        fileToBitmap(bitMap);
        while (true) {
            System.out.print("Input:");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (isNumeric(input)) {
                System.out.println(getBitData(Integer.valueOf(input), bitMap));;
            }
        }
    }

    public static boolean getBitData(int number,int[] bitMap) {
        if (number == 12345) return false;
        if (number < 0 || number > length) {
            System.out.print("Error number!  ");
            return false;
        }
        number = number > 12345 ? number - 1 : number;
        int location = (number >> 5);
        int position = (number & 31);
        return ((bitMap[location] & PositionTable[position])) == 0 ? false : true;
    }

    public static void fileToBitmap(int[] bitMap) throws IOException {

        FileInputStream inputStream = new FileInputStream("AlgorithmLab2.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        int number = 0;
        int strNum = 0;
        while((str = bufferedReader.readLine()) != null) {
            strNum = Integer.parseInt(str);
            /* 12346存在12345应存储的位置上，以此类推 */
            number = strNum > 12345 ?  strNum -1 : strNum;
            dataToBitMap(number, bitMap);
        }
        inputStream.close();
        bufferedReader.close();
    }

    public static void dataToBitMap(int number, int[] bitMap) {
        if (number < 0 || number >= length) {
            System.out.println("Error number!");
            return;
        }
        int location = number >> 5;
        int position = (number & 31);
        /* int占4个字节，即32bit，每一bit代表一个数据存在与否（1表示存在），每个桶的32位一一对应数据，currentValue即代表该桶的数据
            PositionTable中存储中2的0次幂到2的31次幂，其同currentValue的或运算即代表着将currentValue中对应的位置的元素置为1（代表该元素存在）
            若该位置已存在元素同样会置为1，其他位置的值不会修改
         */
        bitMap[location] = bitMap[location] | PositionTable[position];
    }

    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}