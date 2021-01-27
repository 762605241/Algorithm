package algorithm.linkedlist;

import datastructure.linkedlist.ReverseLinkedList;

public class SubLinkedList {
    public static void main(String[] args) {
        Node head1 = randomLinkedList(10, 10);
        Node temphead1 = copyLinkedList(head1);
        print("数组1：", temphead1);
        Node head2 = randomLinkedList(10, 10);
        Node temphead2 = copyLinkedList(head2);
        print("数组2：", temphead2);
        Node sum = subLinkedList(head1, head2);
        print("结果：", sum);
    }

    /**
     * 备份链表
     *
     * @param head
     * @return
     */
    public static Node copyLinkedList(Node head) {
        if (head == null) {
            return null;
        }
        Node temp = new Node(head.value);
        Node resutl = temp;
        while (head.next != null) {
            temp.next = new Node(head.next.value);
            temp = temp.next;
            head = head.next;
        }
        return resutl;
    }


    /**
     * 打印链表
     * @param mes
     * @param sum
     */
    private static void print(String mes, Node sum) {
        System.out.print(mes + "\t");
        while (sum != null) {
            System.out.print(sum.value + "\t");
            sum = sum.next;
        }
        System.out.println();
    }

    /**
     * 题目：
     * 两个链表相加
     * 给定两个链表的头节点head1和head2
     * 链表从左到右是某个数从低位到高位，返回相加之后的链表
     * 例子：
     * head1：4-3-6
     * head2：2-5-3
     * 返回：6-8-9
     */
    public static Node subLinkedList(Node head1, Node head2) {
        // 边界判断
        if (head1 == null && head2 == null){
            return null;
        }
        // 获取head1长度
        int length1 = getLength(head1);
        // 获取head2长度
        int length2 = getLength(head2);
        // 以较长的链表作为返回结果，节省空间
        // 记录结果的头节点，作为返回值
        Node head = length1 > length2 ? head1 : head2;
        // 长链表迭代变量
        Node curr1 = length1 > length2 ? head1 : head2;
        // 短链表迭代变量
        Node curr2 = length1 > length2 ? head2 : head1;
        Node last = curr1;
        // 对位相加的和
        int sum = 0;
        // 记录相加后是否进位
        int bit = 0;
        // 第一部分，两个链表都存在
        while (curr1 != null && curr2 != null) {
            sum = (curr1.value + curr2.value + bit) % 10;
            bit = (curr1.value + curr2.value + bit) / 10;
            curr1.value = sum;
            last = curr1;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        // 第二部分，剩余的长链表
        while (curr1 != null) {
            sum = (curr1.value + bit) % 10;
            bit = (curr1.value + bit) / 10;
            curr1.value = sum;
            last = curr1;
            curr1 = curr1.next;
        }
        // 第三部分，判断是否有进位，是否需要创建新节点
        if (bit != 0) {
            last.next = new Node(bit);
        }
        return head;
    }

    /**
     * 获取链表长度
     *
     * @param head
     * @return
     */
    private static int getLength(Node head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        return i;
    }

    /**
     * 链表结构
     */
    private static class Node {
        public Integer value;
        public Node next;

        public Node(Integer value) {
            this.value = value;
        }
    }

    /**
     * 随机生成链表
     * @param maxLength 最大长度
     * @param maxValue 最大值
     * @return
     */
    public static Node randomLinkedList(Integer maxLength, Integer maxValue) {
        Node curr = new Node((int) (Math.random() * maxValue));
        Node head = curr;
        while (--maxLength != 0) {
            curr.next = new Node((int) (Math.random() * maxValue));
            curr = curr.next;
        }
        return head;
    }
}
