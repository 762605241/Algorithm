package datastructure.binarytree;

public class CheckBinaryTree {
    /**
     * 校验两颗二叉树是否相同
     */
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
//
//        Node node7 = new Node(1);
//        Node node8 = new Node(2);
//        Node node9 = new Node(3);
//        Node node10 = new Node(4);
//        Node node11 = new Node(5);
//        Node node12 = new Node(6);
//        node7.left = node9;
//        node7.right = node8;
//        node8.left = node11;
//        node8.right = node10;
//        node9.right = node12;
//
//        System.out.println(isMirror(node1, node7));
        System.out.println(getDepth(node1));
    }

    /**
     * 检验两颗二叉树是否相同
     *
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isSame(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return true;
        } else if (head1 == null ^ head2 == null) {
            return false;
        } else {
            /**
             * 判断相同节点值是否相等
             * 判断其左子树是否相等
             * 判断其右子树是否相等
             * 全为true则两棵树相同
             */
            return head1.value == head2.value && isSame(head1.left, head2.left) && isSame(head1.left, head2.left);
        }
    }

    /**
     * 判断两个二叉树是否是镜面堆成
     *
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isMirror(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1 == null ^ head2 == null) {
            return false;
        }
        return head1.value == head2.value && isMirror(head1.left, head2.right) && isMirror(head1.right, head2.left);
    }

    /**
     * 返回二叉树深度
     *
     * @param head
     * @return
     */
    public static int getDepth(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(getDepth(head.left), getDepth(head.right)) + 1;
    }
}
