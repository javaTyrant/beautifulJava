package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.ArrayList;
import java.util.List;

public class LeetCode173 {
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
    class BSTIterator implements Tree {
        List<Integer> data;
        int i;

        public BSTIterator(TreeNode root) {
            data = new ArrayList<>();
            i = 0;
            dfs(data, root);
        }

        private void dfs(List<Integer> data, TreeNode root) {
            if (root == null) return;
            dfs(data, root.left);
            data.add(root.val);
            dfs(data, root.right);

        }

        public int next() {
            return data.get(i++);
        }

        public boolean hasNext() {
            return i < data.size();
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
