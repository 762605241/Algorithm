package datastructure.binarytree;

public class BinaryTreePathSum {
    /**
     * 求二叉树路径和中是否存在给定和
     * 二叉树路径和：从根节点开始，到叶子结点结束，所以经过的结点总和。
     * 有多少个叶子结点就有多少条路径。
     */
    public static void main(String[] args) {
        Node node1 = new Node(6);
        Node node2 = new Node(2);
        Node node3 = new Node(7);
        Node node4 = new Node(1);
        Node node5 = new Node(4);
        Node node6 = new Node(9);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        System.out.println(hasPathSum(node1, 91));
    }

    public static boolean hasPathSum(Node head, int sum) {
        if (head == null) {
            return false;
        }
        if (head.left == null && head.right == null) {
            return head.value == sum;
        }
        return head.left != null ? hasPathSum(head.left, sum - head.value) : false
                | head.right != null ? hasPathSum(head.right, sum - head.value) : false;
    }
}
