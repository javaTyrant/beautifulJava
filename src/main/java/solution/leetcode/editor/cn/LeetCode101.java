package solution.leetcode.editor.cn;

import treenode.Tree;

public class LeetCode101 {
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
    static class Solution implements Tree {
        //给定一个二叉树，检查它是否是镜像对称的。
        public boolean isMirror(TreeNode root1, TreeNode root2) {
            //都是空返回true
            if (root1 == null && root2 == null)
                return true;
            //有一个空返回false
            else if (root1 == null || root2 == null)
                return false;
            //取left
            boolean left = isMirror(root1.left, root2.right);
            //取left
            boolean right = isMirror(root1.right, root2.left);
            //如果值都相等 且 left && right
            return root1.val == root2.val && left && right;
        }

        public boolean isSymmetric(TreeNode root) {
            return isMirror(root, root);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
