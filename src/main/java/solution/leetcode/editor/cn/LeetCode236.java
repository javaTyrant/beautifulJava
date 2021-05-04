package solution.leetcode.editor.cn;

import treenode.Tree;

public class LeetCode236 {
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
        // 二叉树的最近公共祖先
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            //base case有一个为空,就返回root
            if (root == null || root == p || root == q) {
                return root;
            }
            //left
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            //right
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            //都不为空返回root
            if (left != null && right != null) {
                return root;
            }
            //返回不为空的
            return left == null ? right : left;
        }

        //二叉搜索树的最近公共祖先
        public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
            //返回==
            if (p.val == root.val) {
                return p;
            }
            if (q.val == root.val) {
                return q;
            }
            //都大于找右边
            if (p.val > root.val && q.val > root.val) {
                return lowestCommonAncestorBST(root.right, p, q);
                //都小于找左边
            } else if (p.val < root.val && q.val < root.val) {
                return lowestCommonAncestorBST(root.left, p, q);
            } else {
                //返回root
                return root;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
