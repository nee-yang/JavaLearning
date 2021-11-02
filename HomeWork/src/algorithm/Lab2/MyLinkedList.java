package algorithm.Lab2;



import java.util.List;

public class MyLinkedList {
    long size;
    ListNode head;
    ListNode tail;

    public MyLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }


    public void addNodeAscend(ListNode addNode) {
        if (this.head == null) {
            this.head = addNode;
            head.prev = null;
            head.next = null;
            this.tail = addNode;
            this.size++;
            return;
        }
        ListNode curr = this.head;
        while (curr != null) {
            if (addNode.getPhone().compareTo(curr.getPhone()) < 0) {
                if (curr.prev != null) {
                    ListNode prevNode = curr.prev;
                    prevNode.next = addNode;
                    addNode.prev = prevNode;
                    addNode.next = curr;
                    curr.prev = addNode;
                    this.size++;
                    return;
                } else {
                    this.head.prev = addNode;
                    addNode.next = this.head;
                    this.head = addNode;
                    this.size++;
                    return;
                }
            }
            curr = curr.next;
        }
        this.tail.next = addNode;
        addNode.prev = tail;
        this.tail = addNode;
        this.size++;
    }

    public void printData() {
        ListNode current = this.head;
        while (current != null) {
            System.out.println("data: " +  current.toString());
            current = current.next;
        }
    }


    //    public void addAtHead(char data) {
//        ListNode toAdd = new ListNode(data);
//        if (this.head == null) {
//            this.head = toAdd;
//            this.tail = toAdd;
//        } else {
//            this.head.prev = toAdd;
//            toAdd.next = this.head;
//            this.head = toAdd;
//        }
//        ListNode current = head;
//        while (current.next != null) {
//            current = current.next;
//        }
//        this.size++;
//    }

//    public int isSymmetry() {
//        ListNode w2 = this.head;
//        ListNode w3 = this.tail;
//        while ((w2 != w3) && (w2.prev != w3)) {
//            if (w2.getData() == w3.getData()) {
//                w2 = w2.next;
//                w3 = w3.prev;
//            } else
//                return 0;
//        }
//        return 1;
//    }
}
