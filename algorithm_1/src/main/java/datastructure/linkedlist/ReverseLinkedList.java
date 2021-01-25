package datastructure.linkedlist;

import java.util.Stack;

public class ReverseLinkedList {
    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            Node node = randomLinkedList(1000, 100000);
            Node copy = copyLinkedList(node);
            Node resvered = reverseDoubleLinkedList(node);
            if (!checkReverse(copy, resvered)) {
                System.out.println("反转出错");
                System.out.println("链表：");
                while (copy != null) {
                    System.out.print(copy.value + " ");
                    copy = copy.next;
                }
                System.out.println();
                System.out.println("反转链表：");
                while (resvered != null) {
                    System.out.print(resvered.value + " ");
                    resvered = resvered.next;
                }
                System.out.println();
                System.out.println();
            }
        }
    }

    /**
     * 检查两个链表是否被反转
     *
     * @param node
     * @param copy
     */
    private static boolean  checkReverse(Node node, Node copy) {
        Stack<Integer> stack = new Stack<Integer>();
        while (node != null) {
            stack.push(node.value);
            node = node.next;
        }
        while (!stack.empty()) {
            if (stack.pop().intValue() != copy.value.intValue()) {
                return false;
            }
            copy = copy.next;
        }
        return true;
    }


    /**
     * 反转单链表
     *
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null; // 链表反转后的前驱节点，原链表的第一个节点反转后变成链表的最后一个节点，其后继节点为null，所以初始值是null
        Node next = null; // 用来记录原链表的后继节点
        while (head != null) {
            next = head.next; // 记录临时变量，保存原链表的后继节点，用于遍历链表变量head后移
            head.next = pre; // 改变当前节点的后继，即反转链表，第一次循环，head为原链表第一个节点，反转后为链表最后一个节点，后继指向null
            pre = head; // 反转后，改变前驱节点位置
            head = next; // 移动遍历指针至下一个节点
        }
        return pre;
    }


    /**
     * 反转双向链表
     *
     * @param head
     * @return
     */
    public static Node reverseDoubleLinkedList(Node head) {
        Node pre = null; //上一节点
        Node next = null; // 后继指针
        while (head != null) {
            next = head.next;
            head.last = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }


    private static class Node {
        public Integer value;
        public Node next;
        public Node last;

        public Node(Integer value) {
            this.value = value;
        }

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
     * 随机生成链表
     *
     * @param maxLength 链表最大长度
     * @param maxValue  链表最大值
     * @return
     */
    public static Node randomLinkedList(Integer maxLength, Integer maxValue) {
        int nodeNum = (int) (Math.random() * maxLength);
        if (nodeNum == 0) {
            return null;
        }
        Node node1 = new Node((int) (Math.random() * maxValue));
        Node n = null;
        Node head = node1;
        for (int i = 1; i < nodeNum; i++) {
            n = new Node((int) (Math.random() * maxValue));
            node1.next = n;
            node1 = n;
        }
        return head;
    }
}
