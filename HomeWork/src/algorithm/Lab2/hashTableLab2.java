package algorithm.Lab2;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class hashTableLab2 {
    public static void main(String[] args) throws Exception {

        int hashTablelength = 10;
        MyLinkedList[]  hashTable = new MyLinkedList[hashTablelength];
        for (int i = 0; i < hashTablelength; i++) {
            MyLinkedList linkedList = new MyLinkedList();
            hashTable[i] = linkedList;
        }
        String filePath = "testdata.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";
        while((strTmp = buffReader.readLine())!=null){
            String[] split = strTmp.split(",");
            long hashKey = ELFHash(split[0]);
            hashKey = hashKey % 10;
            MyLinkedList list = hashTable[Math.toIntExact(hashKey)];
            if (containsSame(list, split[0], false) == false) {
                ListNode add = new ListNode(split[0], split[1], Integer.valueOf(split[2]));
                list.addNodeAscend(add);
            }
        }
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println("Table:" + i);
            hashTable[i].printData();
        }
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("input number：");
            String phone = sc.nextLine();
            if (isValid(phone)) {
                long hashKey = ELFHash(phone);
                hashKey = hashKey % 10;
                MyLinkedList list = hashTable[Math.toIntExact(hashKey)];
                if (!containsSame(list, phone, true)) {
                    System.out.println("No such number!");
                }
            }
        }
    }

    public static boolean containsSame(MyLinkedList list, String str, boolean flag) {
        ListNode current = list.head;
        while (current != null) {
            if (current.getPhone().compareTo(str) == 0) {
                if (flag) {
                    System.out.println( current.toString());
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static long ELFHash(String strUri) {
        long hash = 0;
        long x=0;
        for(int i=0;i<strUri.length();i++)
        {
            hash = (hash<<4)+strUri.charAt(i);
            if((x=hash & 0xF0000000L) != 0)
            {
                hash^=(x>>24);
            }
            hash &=~x;
        }
        return hash;
    }


    public static boolean isValid(String input) {
        for (int i = 0; i < input.length(); i++) {
            char temp = input.charAt(i);
            if (temp >= 48 && temp <= 57 ) {
            } else {
                System.out.println("Contains non-num!");
                return false;
            }

        }
        return true;
    }
}
