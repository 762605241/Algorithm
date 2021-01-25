package problems;

public class MergeLinkedList {
    public static void main(String[] args) {
        /**
         * 问题：合并两个有序链表
         * 给定两个有序链表的头节点head1和head2
         * 返回合并之后的链表，要求任然有序
         * 例子：
         *  head1：1-3-3-5-7
         *  head2：2-2-3-3-7
         *  返回：1-2-2-3-3-3-3-5-7-7
         */
        for (int i = 0; i < 1000000; i++) {
            Node head1 = randomLinkedList(1000, 10000);
            Node copyHead1 = copyLinkedList(head1);
//            print("链表1：", copyHead1);
            Node head2 = randomLinkedList(10, 100);
            Node copyHead2 = copyLinkedList(head2);
//            print("链表2：", copyHead2);
            Node result = mergeLinkedList(head1, head2);
//            print("合并结果：", result);
            if (!check(copyHead1, copyHead2, result)) {
                System.out.println("合并错误");
                print("链表1：", copyHead1);
                print("链表2：", copyHead2);
                print("合并结果：", result);
            } else {
                System.out.println("合并成功：" + i);
            }
        }
    }

    private static Node mergeLinkedList(Node head1, Node head2) {
        Node head = head1.value < head2.value ? head1 : head2;
        Node curr1 = head1.value < head2.value ? head1.next : head2.next;
        Node curr2 = head1.value < head2.value ? head2 : head1;
        Node iterator = head;
        // 第一部分，两个链表都存在
        while (curr1 != null && curr2 != null) {
            if (curr1.value < curr2.value) {
                iterator.next = curr1;
                curr1 = curr1.next;
            } else {
                iterator.next = curr2;
                curr2 = curr2.next;
            }
            iterator = iterator.next;
        }
        // 若head1剩余
        if (curr1 != null) {
            iterator.next = curr1;
        }

        // 若head2剩余
        if (curr2 != null) {
            iterator.next = curr2;
        }
        return head;
    }


    /**
     * 检查链表是否合并成功
     */
    public static boolean check(Node head1, Node head2, Node result) {
        int lengthHead1 = getLength(head1);
        int lengthHead2 = getLength(head2);
        int lengthResult = getLength(result);
        if (lengthHead1 + lengthHead2 != lengthResult) {
            return false;
        }
        int temp = result.value;
        result = result.next;
        while (result != null) {
            if (result.value < temp) {
                return false;
            }
            temp = result.value;
            result = result.next;
        }
        return true;
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
     *
     * @param maxLength 最大长度
     * @param maxValue  最大值
     * @return
     */
    public static Node randomLinkedList(Integer maxLength, Integer maxValue) {
        Node curr = new Node((int) (Math.random() * maxValue));
        Node head = curr;
        int temp = 0;
        do {
            temp = (int) (Math.random() * maxValue);
            if (temp >= curr.value) {
                curr.next = new Node(temp);
                curr = curr.next;
                maxLength--;
            }
        } while (maxLength != 0);
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
     * 打印链表
     *
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
}
