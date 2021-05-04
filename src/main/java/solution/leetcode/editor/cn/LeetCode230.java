package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.LinkedList;

public class LeetCode230 {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    static class Solution implements Tree {
        //二叉搜索树中第K小的元素
        //BST 的中序遍历是升序序列
        public int kthSmallest(TreeNode root, int k) {
            LinkedList<TreeNode> stack = new LinkedList<>();
            while (true) {
                while (root != null) {
                    //先加左边
                    stack.add(root);
                    root = root.left;
                }
                //删除最后一个
                root = stack.removeLast();
                if (--k == 0) return root.val;
                //否则加右边
                root = root.right;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
