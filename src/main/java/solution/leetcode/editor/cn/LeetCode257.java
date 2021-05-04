package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.ArrayList;
import java.util.List;

public class LeetCode257 {
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
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> ret = new ArrayList<>();
            dfs(root, new StringBuilder(), ret);
            return ret;
        }

        private void dfs(TreeNode root, StringBuilder tmp, List<String> ret) {
            //base case
            if (root == null) {
                return;
            }
            //
            String s = tmp.length() == 0 ? "" : "->";
            tmp.append(s);
            tmp.append(root.val);
            //
            if (root.left == null && root.right == null) {
                ret.add(tmp.toString());
                return;
            }
            dfs(root.left, new StringBuilder(tmp), ret);
            dfs(root.right, new StringBuilder(tmp), ret);
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            Integer[] arr = {1, 2, 3};
            TreeNode tree = solution.of(arr);
            solution.binaryTreePaths(tree);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
