package solution.leetcode.editor.cn;

import treenode.Tree;

public class LeetCode108 {
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
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            return help(nums, 0, nums.length - 1);
        }

        private TreeNode help(int[] nums, int low, int high) {
            if (low > high) return null;
            int mid = (high + low) / 2;
            TreeNode tree = new TreeNode(nums[mid]);
            tree.left = help(nums, low, mid - 1);
            tree.right = help(nums, mid + 1, high);
            return tree;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
