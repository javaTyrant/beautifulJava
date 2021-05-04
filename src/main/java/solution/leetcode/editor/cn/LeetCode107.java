package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.*;

public class LeetCode107 {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution implements Tree {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ret = new ArrayList<>();
            if (root == null) {
                return ret;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<>();
                int currentLevelSize = queue.size();
                for (int i = 1; i <= currentLevelSize; ++i) {
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                ret.add(level);
            }

            return ret;
        }

        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> levelOrder = new LinkedList<>();
            if (root == null) {
                return levelOrder;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    TreeNode left = node.left, right = node.right;
                    if (left != null) {
                        queue.offer(left);
                    }
                    if (right != null) {
                        queue.offer(right);
                    }
                }
                //从头插
                levelOrder.add(0, level);
            }
            return levelOrder;
        }

        //后续遍历
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> stack = new LinkedList<>();
            //这个指针是干啥的
            TreeNode prev = null;
            while (root != null || !stack.isEmpty()) {
                //先把left全部加进去
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                //弹出来
                root = stack.pop();
                //right为空,或者right == prev
                // root.right == prev是什么意思
                // it means当前的节点是不是root的右节点,如果是的,说明已经遍历完了
                if (root.right == null || root.right == prev) {
                    res.add(root.val);
                    prev = root;
                    root = null;
                } else {
                    //push回去
                    stack.push(root);
                    //指针往右转移
                    root = root.right;
                }
            }
            return res;
        }

        //输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
        public boolean verifyPostorder(int[] postorder) {
            if (postorder.length == 0) {
                return true;
            }
            return verify(postorder, 0, postorder.length - 1);
        }

        private boolean verify(int[] seq, int start, int end) {
            if (start >= end) {
                return true;
            }
            int i = start, j = end - 1;
            while (i < end && seq[i] < seq[end]) {
                i++;
            }
            while (j >= start && seq[j] > seq[end]) {
                j--;
            }
            if (i < j) {
                return false;
            }
            return verify(seq, start, i - 1) && verify(seq, j + 1, end - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
