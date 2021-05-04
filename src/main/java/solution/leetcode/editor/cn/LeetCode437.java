package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.HashMap;
import java.util.Map;

public class LeetCode437 {
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
    //路径总和 III
    static class Solution implements Tree {

        public int pathSum(TreeNode root, int sum) {
            if (root == null) return 0;
            return getSum(root, sum, new int[1000], 0);
        }

        public int getSum(TreeNode root, int sum, int[] array, int curr) {
            //base case
            if (root == null) return 0;
            //
            array[curr] = root.val;
            int count = 0, thisSum = sum;
            for (int i = curr; i >= 0; i--) {
                thisSum -= array[i];
                if (thisSum == 0) count++;
            }
            return count + getSum(root.left, sum, array, curr + 1)
                    + getSum(root.right, sum, array, curr + 1);
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            Integer[] arr = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
            TreeNode of = solution.of(arr);
            System.out.println(solution.pathSum(of, 8));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
