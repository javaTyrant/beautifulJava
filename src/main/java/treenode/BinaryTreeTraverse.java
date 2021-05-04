package treenode;

import java.util.*;

/**
 * @author lumac
 * @since 2020/7/7
 */
public class BinaryTreeTraverse implements Tree {
    //二叉树的前序遍历
    public static List<Integer> preorderTraversal(TreeNode root) {
        //迭代
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode s = stack.pop();
            res.add(s.val);
            if (s.right != null) {
                stack.push(s.right);
            }
            if (s.left != null) {
                stack.push(s.left);
            }
        }
        return res;
    }

    //二叉树的中序遍历
    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        List<Integer> res = new ArrayList<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

    //二叉树的后续遍历
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root == null) {
            return res;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            res.add(0, node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return res;
    }

    //二叉树的层次遍历
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.left.left = new TreeNode(5);
        treeNode.left.right = new TreeNode(6);
        treeNode.right.left = new TreeNode(7);
        treeNode.right.right = new TreeNode(8);
        System.out.println(preorderTraversal(treeNode));
        System.out.println(inorderTraversal(treeNode));
        System.out.println(postorderTraversal(treeNode));
    }
}
