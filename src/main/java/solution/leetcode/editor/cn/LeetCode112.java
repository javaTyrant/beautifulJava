package solution.leetcode.editor.cn;

import treenode.Tree;

public class LeetCode112 {
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
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) return false;
            if (sum == 0 && root.left == null && root.right == null) return false;
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
