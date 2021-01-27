package datastructure.binarytree;

import java.util.Stack;

public class BuildBinaryTree {
    /**
     * 根据二叉树的前中后序遍历，构建二叉树
     */
    public static void main(String[] args) {
        int[] pre = {32, 5, 1, 55, 3};
        int[] mid = {1, 5, 55, 32, 3};
        int[] last = {1, 55, 5, 3, 32};
//        Node node = buildBinaryTreeByPreAndMid(pre, mid);
        Node node = buildBinaryTreeByPreAndLast(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
//        Node node = buildBinaryTreeByPreAndLast(new int[]{1, 2, 4, 5, 3, 6}, new int[]{4, 5, 2, 6, 3, 1});
//        Node node = buildBinaryTreeByMidAndLast(mid, last);
        IteratorBinaryTree.pre(node);
        System.out.println();
        IteratorBinaryTree.mid(node);
        System.out.println();
        IteratorBinaryTree.last(node);
        System.out.println();
        IteratorBinaryTree.level(node);
    }

    /**
     * 根据前中序遍历构建二叉树
     * 思路：
     * 前序遍历中，第一个节点即是根节点，在中序遍历中，该节点前的是左子树，该节点后的是右子树
     *
     * @return
     */
    public static Node buildBinaryTreeByPreAndMid(int[] pre, int[] mid) {
        if (pre == null && mid == null) {
            return null;
        }
        if (pre.length == 0 && mid.length == 0) {
            return null;
        }
        Node head = new Node(pre[0]);
        int rootIndex = getIndex(mid, pre[0]);

        head.left = buildBinaryTreeByPreAndMid(
                subArray(pre, 1, rootIndex),
                subArray(mid, 0, rootIndex));
        head.right = buildBinaryTreeByPreAndMid(
                subArray(pre, rootIndex + 1, pre.length - rootIndex - 1),
                subArray(mid, rootIndex + 1, pre.length - rootIndex - 1));
        return head;
    }

    /**
     * 截取数组
     *
     * @param array
     * @param startIndex
     * @param length
     * @return
     */
    public static int[] subArray(int[] array, int startIndex, int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = array[startIndex + i];
        }
        return result;
    }

    /**
     * 获取元素下标位置
     *
     * @param array
     * @param num
     * @return
     */
    public static int getIndex(int[] array, int num) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据前后序遍历构建二叉树
     * 只能通过完全二叉树或满二叉树的前后序，来构建二叉树。
     * 若不是完全二叉树或满二叉树，无法确定某个子树是否为空，所以不能构建二叉树
     * 而完全二叉树或满二叉树，要么左子树和右子树都为空，要么左子树和右子树都不为空，可以保证能找到左右子树节点
     *
     * @return
     */
    public static Node buildBinaryTreeByPreAndLast(int[] pre, int[] last) {
        if (pre == null && last == null) {
            return null;
        }
        if (pre.length == 0 && last.length == 0) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node head = new Node(pre[0]);
        stack.push(head);
        Node cur = head;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            Node left = null;
            Node right = null;
            if (getIndex(pre, cur.value) + 1 < pre.length// 首先判断前序遍历数组中是否存在后一位，即是否向后寻找一位越界
                    && getIndex(last, cur.value) > getIndex(last, pre[getIndex(pre, cur.value) + 1])
                // 如果存在,继续判断。后一位节点与当前根节点在后序遍历数组中的位置关系，
                // 若后一节点位置在当前节点位置前，则是其左子树，否则当前根节点无左子树
            ) {
                left = new Node(pre[getIndex(pre, cur.value) + 1]);
                cur.left = left;
                stack.push(left);
            }
            if (getIndex(last, cur.value) - 1 >= 0// 首先判断后序遍历数组中是否存在前一位，即是否向前寻找一位越界
                    && getIndex(pre, cur.value) < getIndex(pre, last[getIndex(last, cur.value) - 1])
                // 如果存在,继续判断。前一位节点与当前根节点在前序遍历数组中的
                    // 位置关系，
                // 若上一节点位置在当前节点位置后，则是其右子树，否则当前根节点无右子树
                    && getIndex(pre, cur.value) + 1 != getIndex(pre, last[getIndex(last, cur.value) - 1])
            ) {
                right = new Node(last[getIndex(last, cur.value) - 1]);
                cur.right = right;
                stack.push(right);
            }
        }
        return head;
    }

    /**
     * 根据中后序遍历构建二叉树
     * 思路：
     * 与 通过前中序遍历 类似
     * 后续遍历的最后一个节点是根节点
     *
     * @return
     */
    public static Node buildBinaryTreeByMidAndLast(int[] mid, int[] last) {
        if (mid == null && last == null) {
            return null;
        }
        if (mid.length == 0 && last.length == 0) {
            return null;
        }
        Node head = new Node(last[last.length - 1]);
        int rootIndex = getIndex(mid, head.value);
        head.left = buildBinaryTreeByMidAndLast(
                subArray(mid, 0, rootIndex),
                subArray(last, 0, rootIndex));
        head.right = buildBinaryTreeByMidAndLast(
                subArray(mid, rootIndex + 1, mid.length - rootIndex - 1),
                subArray(last, rootIndex, last.length - rootIndex - 1));
        return head;
    }
}
