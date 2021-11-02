package algorithm.Lab2;




public class ListNode {
    private String phone;
    private String name;
    private int age;
    ListNode next;
    ListNode prev;

    public ListNode(String phone, String name, int age){
        this.phone = phone;
        this.name = name;
        this.age = age;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode getPrev() {
        return prev;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPrev(ListNode prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return  "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age +
                "'";
    }
}
