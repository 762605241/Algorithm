package datastructure.binarytree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class CheckBinaryTree {
    /**
     * 校验两颗二叉树是否相同
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
        System.out.println(isBinarySearchTree3(node1).isSearch);
    }


    public static class Info {
        int height;
        boolean isBalance;

        public Info(int height, boolean isBalance) {
            this.height = height;
            this.isBalance = isBalance;
        }
    }

    /**
     * 判断一棵树是否是平衡二叉搜索树
     * 一起判断
     * 定义返回信息Info，将某子树的高度及是否平衡一起作为返回值返回，
     * 将高度判断和平衡判断放在一起，通过一次递归完成。
     * 1、若是空树则是平衡树返回true
     * 2、返回左子树的高度信息及平衡信息
     * 3、返回右子树的高度信息及平衡信息
     * 4、当左子树平衡 && 右子树平衡 && 当前根节点的子树平衡，返回true
     *
     * @param head
     * @return
     */
    public static Info isBalanceBinaryTree(Node head) {
        if (head == null) {
            return new Info(0, true);
        }

        Info leftInfo = isBalanceBinaryTree(head.left);
        Info rightInfo = isBalanceBinaryTree(head.right);

        int curHeight = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance
                && Math.abs(leftInfo.height - rightInfo.height) <= 1;
        return new Info(curHeight, isBalance);
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


    /**
     * 判断是否是平衡树
     * 平衡树：空树或者其子树的左子树与右子树的高度差的绝对值小于等于1
     *
     * @param head
     * @return
     */
    public static boolean isBalanceTree(Node head) {
        if (head == null) {
            return true;
        }
        return Math.abs(getDepth(head.left) - getDepth(head.right)) <= 1
                && isBalanceTree(head.left)
                && isBalanceTree(head.right);
    }

    /**
     * 判断是否是二叉搜索树
     * 非递归实现
     * 左子树的所有节点都小于根节点，右子树的节点都大于根节点
     *
     * @param head
     * @return
     */
    public static boolean isBinarySearchTree(Node head) {
        if (head == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        int rootValue = head.value;
        Node temp = null;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            if (temp.left != null) {
                stack.push(temp.left);
                return rootValue >= temp.left.value
                        && isBinarySearchTree(temp.left);
            }
            if (temp.right != null) {
                stack.push(temp.right);
                return rootValue >= temp.right.value
                        && isBinarySearchTree(temp.right);
            }
        }
        return true;
    }

    /**
     * 利用二叉树中序遍历判断是否是搜索二叉树
     * 二叉树搜索树的中序遍历是一组非递减序列。
     *
     * @param head
     * @return
     */
    public static boolean isBinarySearchTree2(Node head) {
        ArrayList<Integer> result = new ArrayList<>();
        mid(head, result);
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i) < result.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void mid(Node head, ArrayList<Integer> result) {
        if (head == null) {
            return;
        }
        mid(head.left, result);
        result.add(head.value);
        mid(head.right, result);
    }

    public static class Info2 {
        int maxValue;
        int minValue;
        boolean isSearch;

        public Info2(int maxValue, int minValue, boolean isSearch) {
            this.isSearch = isSearch;
            this.maxValue = maxValue;
            this.minValue = minValue;
        }
    }

    /**
     * 使用递归判断二叉树是否是搜索树
     * 根节点的值大于等于左子树的最大值，小于等于右子树的最小值
     * 每次递归获取其左子树和右子树的最大及最小值，然后与根节点比较，三个值，求出该子树的最大值和最小值
     * 如果该子树属于右子树，使用该子树的根节点与该子树的最小值进行比较
     * 如果该子树属于左子树，使用该子树的根节点与该子树的最大值进行比较
     * @param head
     * @return
     */
    public static Info2 isBinarySearchTree3(Node head) {
        if (head == null) {
            return null;
        }

        Info2 left = isBinarySearchTree3(head.left); // 左子树最大值
        Info2 right = isBinarySearchTree3(head.right); // 右子树最小值

        boolean leftIsBalance = left == null ? true : left.isSearch && head.value >= left.maxValue;
        boolean rightIsBalance = right == null ? true : right.isSearch && head.value <= right.minValue;
        if (left == null && right == null) {
            return new Info2(head.value, head.value, leftIsBalance && rightIsBalance);
        } else if (left == null && right != null) {
            return new Info2(Math.max(head.value, right.maxValue), Math.min(head.value, right.minValue), leftIsBalance && rightIsBalance);
        } else if (left != null && right == null) {
            return new Info2(Math.max(head.value, left.maxValue), Math.min(head.value, left.minValue), leftIsBalance && rightIsBalance);
        } else {
            return new Info2(
                    Math.max(head.value, Math.max(left.maxValue, right.maxValue)),
                    Math.min(head.value, Math.min(left.minValue, right.minValue)),
                    leftIsBalance && rightIsBalance);
        }
    }


    /**
     * 判断是否是平衡二叉搜索树
     *
     * @param head
     * @return
     */
    public static boolean isBalanceBinarySearchTree(Node head) {
        return isBalanceTree(head) && isBinarySearchTree(head);
    }
}
