package solution.leetcode.editor.cn;

import treenode.Tree;

public class LeetCode337 {
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
        //337. 打家劫舍 III
        public int rob(TreeNode root) {
            int[] rootStatus = dfs(root);
            return Math.max(rootStatus[0], rootStatus[1]);
        }

        //时间空间复杂度都是O(n)
        public int[] dfs(TreeNode node) {
            if (node == null) {
                return new int[]{0, 0};
            }
            //
            int[] l = dfs(node.left);
            int[] r = dfs(node.right);
            //选择跟节点,子节点就不能选了
            int selected = node.val + l[1] + r[1];
            //
            int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
            return new int[]{selected, notSelected};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
