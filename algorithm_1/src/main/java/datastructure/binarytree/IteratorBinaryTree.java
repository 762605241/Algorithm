package datastructure.binarytree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class IteratorBinaryTree {

    public static void main(String[] args) {
        /**
         * 二叉树遍历
         */
        Node node1 = new Node(32);
        Node node2 = new Node(5);
        Node node3 = new Node(3);
        Node node4 = new Node(1);
        Node node5 = new Node(55);
        Node node6 = new Node(13);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        System.out.println();
        System.out.println("二叉树前序遍历");
        pre(node1);
        System.out.println();
        pre2(node1);
        System.out.println();
        System.out.println("二叉树中序遍历");
        mid2(node1);
        System.out.println();
        mid2(node1);
        System.out.println();
        System.out.println("二叉树后序遍历");
        last(node1);
        System.out.println();
        last2(node1);
        System.out.println();
        System.out.println("二叉树层次遍历");
        level(node1);
    }


    /**
     * 二叉树前序遍历
     * 递归实现
     * @param head
     */
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + "\t");
        pre(head.left);
        pre(head.right);
    }

    /**
     * 二叉树前序遍历
     * 非递归
     * 思路：使用栈模拟递归
     *  先将所有根节点及其左子树加入栈中，知道没有左子树。在从栈中取出根节点，将其对应的右子树加入栈中。
     *  将所有根节点和左子树加入栈后，当前根节点是空，那么证明左子树左子树和根遍历完成，此时栈中依次保存树的最左节点，
     *  再依次遍历最左节点，将其右子树作为根继续入栈。
     * @param head
     */
    public static void pre2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                System.out.print(head.value + "\t");
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                head = head.right;
            }
        }
    }


    /**
     * 二叉树中序遍历
     * 非递归
     * @param head
     */
    public static void mid2(Node head) {
        {
            if (head == null) {
                return;
            }
            Stack<Node> stack = new Stack<>();
            while (head != null || !stack.isEmpty()) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + "\t");
                    head = head.right;
                }
            }
        }
    }
    /**
     * 二叉树中序遍历
     * 递归
     * @param head
     */
    public static void mid(Node head) {
        if (head == null) {
            return;
        }
        mid(head.left);
        System.out.print(head.value + "\t");
        mid(head.right);
    }



    /**
     * 后序非递归：
     * 后序遍历不同于先序和中序，它是要先处理完左右子树，
     * 然后再处理根(回溯)。
     * 对于任一结点P，将其入栈，然后沿其左子树一直往下搜索，直到搜索到没有左孩子的结点，
     * 此时该结点出现在栈顶，但是此时不能将其出栈并访问，因此其右孩子还为被访问。
     * 所以接下来按照相同的规则对其右子树进行相同的处理，当访问完其右孩子时，该结点又出现在栈顶，
     * 此时可以将其出栈并访问。这样就保证了正确的访问顺序。
     * 可以看出，在这个过程中，每个结点都两次出现在栈顶，只有在第二次出现在栈顶时，才能访问它。
     * 因此需要多设置一个变量标识该结点是否是第一次出现在栈顶，这里是在树结构里面加一个标记，然后合成一个新的TagNode。
     *
     * @param head
     */
    /**
     * 可以理解为中序遍历时，左子树遍历完成后，到达根节点，先不输出，重新放回栈中，并且将其右子树加入到栈中后，
     * 再输出根节点
     * @param head
     */
    public static void last2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node temp;
        // 记录每个节点出现的次数，次数为一证明只遍历左子树，不能输出根节点，次数为二证明右子树已经遍历完成。
        HashMap<Node, Boolean> hashMap = new HashMap<>();
        while (head != null || !stack.isEmpty()) {
            // 将根节点所有左子树入栈
            while (head != null) {
                hashMap.put(head, false);
                stack.push(head);
                head = head.left;
            }
            if (!stack.isEmpty()) {
                temp = stack.pop();
                if (hashMap.get(temp)) {
                    // 第二次入栈
                    System.out.print(temp.value + "\t");
                    head = null;
                } else {
                    // 第一次入栈
                    hashMap.put(temp, true);
                    stack.push(temp);
                    head = temp.right;
                }
            }
        }
    }

    /**
     * 二叉树后序遍历
     * 非递归
     * @param head
     */
    public static void last(Node head) {
        if (head == null) {
            return;
        }
        last(head.left);
        last(head.right);
        System.out.print(head.value + "\t");
    }

    /**
     * 二叉树层次遍历
     * @param head
     */
    public static void level(Node head) {
        if (head == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(head);
        Node node = null;
        int size = 0;
        while (!queue.isEmpty()) {
            // 每次循环时，当前队列中就是当前层的所有节点，将当前层输出后，将其所有子树节点加入到队列中。
            size = queue.size();
            while (size-- > 0) {
                node = queue.pop();
                System.out.print(node.value + "\t");
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

}
