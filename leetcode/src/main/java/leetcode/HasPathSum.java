package leetcode;

import java.util.LinkedList;

//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
// targetSum 。
//
// 叶子节点 是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
//
//
// 示例 2：
//
//
//输入：root = [1,2,3], targetSum = 5
//输出：false
//
//
// 示例 3：
//
//
//输入：root = [1,2], targetSum = 0
//输出：false
//
//
//
//
// 提示：
//
//
// 树中节点的数目在范围 [0, 5000] 内
// -1000 <= Node.val <= 1000
// -1000 <= targetSum <= 1000
//
// Related Topics 树 深度优先搜索
// 👍 513 👎 0
public class HasPathSum {

    /**
     * 2021/2/22
     * 17:37	info
     * 			解答成功:
     * 			执行耗时:0 ms,击败了100.00% 的Java用户
     * 			内存消耗:38.6 MB,击败了13.36% 的Java用户
     * @param args
     */

    public static void main(String[] args) {
//        root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node11 = new TreeNode(11);
        TreeNode node13 = new TreeNode(13);
        TreeNode node4_2 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        node5.left = node4;
        node5.right = node8;
        node4.left = node11;
        node8.left = node13;
        node8.right = node4_2;
        node11.left = node7;
        node11.right = node2;
        node4_2.right = node1;

        /**
         * 思路
         * 总体思路：使用递归，遍历整棵树，直至叶子结点，并记录路径和。再与目标进行比较。
         * 具体思路：
         *     1、如果当前节点有左子树，将当前节点值加入到路径和中。并递归其左子树，计算路径和。
         *     2、如果当前节点有右子树，将当前节点值加入到路径和中。并递归其右子树，计算路径和。
         *     3、如果当前节点无左右子树，即当前节点为叶子节点。此时将当前节点加入到路径和中。得到一条二叉树路径和。
         *     4、判断该二叉树路径和是否等于目标值，相等返回true，否则返回false。
         */

        System.out.println(hasPathSum(node5, 27));
    }
    /**
     * 二叉树层次遍历
     * @param head
     */
    public static void level(TreeNode head) {
        if (head == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        TreeNode node = null;
        int size = 0;
        while (!queue.isEmpty()) {
            // 每次循环时，当前队列中就是当前层的所有节点，将当前层输出后，将其所有子树节点加入到队列中。
            size = queue.size();
            while (size-- > 0) {
                node = queue.pop();
                System.out.print(node.val + "\t");
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            System.out.println();
        }
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        // 空树直接返回false
        if (root == null) {
            return false;
        }
        // 如果根节点左右子树都是空，直接比较根节点的值是否为targetSum
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        // 递归判断
        return hasPathSum(root, targetSum, 0);
    }

    /**
     * 递归累加，直至叶子节点，判断加和是否为targetSum
     * @param root
     * @param targetSum
     * @param sum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int targetSum, int sum) {
        if (root.left == null && root.right == null) {
            // 当递归到叶子节点时，将叶子节点的值加上之前加和即一条路径的总和，在与targetSum比较
            return (sum + root.val) == targetSum;
        }
        boolean left = false;
        boolean right = false;
        if (root.left != null) {
            // 将当前节点值加入路径总和中，并向其左子树递归
            left = hasPathSum(root.left, targetSum, sum + root.val);
        }
        if (root.right != null) {
            // 将当前节点值加入路径总和中，并向其右子树递归
            right = hasPathSum(root.right, targetSum, sum + root.val);
        }
        // 只要有一条路径和为targetSum即返回true
        return left || right;
    }


}
