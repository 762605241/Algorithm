package algorithm.linkedlist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeLinkedLists {
    /**
     * 题目：合并多条有序链表，合并后也是有序状态
     * 方法一：
     *  思路：可以使用两个链表的合并方法，依次合并所有链表。
     *  缺点：两两合并，会存在大量的多余比较操作，浪费时间。
     * 方法二：
     *  思路：使用系统的优先级队列，数据结构实现。
     *      优先级队列PriorityQueue<T>,向其添加元素，元素顺序默认按从小到大的顺序排列，利用这一特性，来依次比较每条链表的头节点信息。
     *      取最小头节点作为结果的头节点，再将该链表的后继节点作为该链表的新头部，放入队列中，继续比较，直至队列为null，比较完成。
     */

    public static void main(String[] args) {
        System.out.println("测试开始");
        int testTimes = 10000;
        int num = 0;
        final int maxLength = 10;
        final int maxValue = 100;
        for (int i = 0; i < testTimes; i++) {
            num = (int) (Math.random() * 10);
            ArrayList<Node> arrayList = new ArrayList<>(num);
            for (int j = 0; j < num; j++) {
                Node node = randomLinkedList(maxLength, maxValue);
                arrayList.add(node);
            }
            Node head = mergeLinkedLists(arrayList);
            printLinkedList(head);
        }
        System.out.println("测试结束");
    }

    /**
     * 题目：合并多条有序链表，合并后也是有序状态
     * 时间复杂度O(N*logM) N=所有俩表节点个数和，M=进行的队列操作和
     * @param arrayList 入参：集合，集合中每一个元素都是一条链表的头节点
     * @return 合并后的头节点
     */
    public static Node mergeLinkedLists(ArrayList<Node> arrayList) {
        // 如果没有链表，返回null
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        // 如果只有一条链表，那么返回该链表的头节点
        if (arrayList.size() == 1) {
            return arrayList.get(0);
        }
        // 存在多条链表
        /**
         * 创建优先级队列
         * 初始化队列大小，因为每次只会比较每条链表的当前头节点，所以队列大小最大等于结合长度。
         * 初始化队列比较器，以为使用自定义类，固定排序规则，为了进入队列时，确定排序位置。
         */
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(arrayList.size(), new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                // 从小到大
                return o1.value - o2.value;
            }
        });
        // 将所有链表头节点进队列
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) != null) {
                priorityQueue.add(arrayList.get(i));
            }
        }

        // 此时队列中，队头表示所有链表头节点的最小值，即结果列表的头节点。
        // 记录头节点，作为返回值
        Node head = priorityQueue.poll();
        // 将头节点下一节点加入队列
        priorityQueue.add(head.next);
        // 循环变量
        Node temp = head;
        /**
         * 只要队列不为空，循环遍历对头，取队头节点，即为下一个最小节点。出队列后，并将自身下一个节点加入到队列中。
         */
        while (!priorityQueue.isEmpty()) {
            temp.next = priorityQueue.poll();
            if (temp.next.next != null) {
                priorityQueue.add(temp.next.next);
            }
            temp = temp.next;
        }
        return head;
    }

    /**
     * 单链表结构
     */
    public static class Node {
        public Node next;
        public int value;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 随机生成有序链表
     * @param maxLength 链表最大长度
     * @param maxValue 链表最大值
     * @return 空链表 或 一条有序链表
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
     * 打印链表
     * @param head
     */
    public static void printLinkedList(Node head) {
        while(head != null) {
            System.out.print(head.value + "\t");
            head = head.next;
        }
        System.out.println();
    }
}
