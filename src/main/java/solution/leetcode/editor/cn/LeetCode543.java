package solution.leetcode.editor.cn;

import treenode.Tree;

public class LeetCode543 {
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
        public static void main(String[] args) {
            Solution solution = new Solution();
            TreeNode treeNode = new TreeNode(1);
            System.out.println(solution.diameterOfBinaryTree(treeNode));
        }

        int max = 0;

        public int diameterOfBinaryTree(TreeNode root) {
            if (root != null) {
                //遍历每一个节点,求出此节点作为根的树的深度,那么,左子树深度加右子树深度的最大值即是答案
                setDepth(root);
                return max;
            }
            return 0;
        }

        public int setDepth(TreeNode root) {
            if (root == null) return 0;
            int right = setDepth(root.right);
            int left = setDepth(root.left);
            //取到左右的子节点,大于max则更新
            max = Math.max(max, left + right);
            //左右的最大值+1
            return Math.max(right, left) + 1;
        }
    }

    public static void main(String[] args) {

    }
//leetcode submit region end(Prohibit modification and deletion)

}
