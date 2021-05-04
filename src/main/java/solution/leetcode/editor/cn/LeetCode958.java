package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode958 {
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
    class Solution implements Tree {
        //层序遍历，设置一个停止标志，遇到空节点，停止标志为真，
        //如果停止标志为真，再遍历遇到非空节点，则非完全二叉树。
        public boolean isCompleteTree(TreeNode root) {
            if (root == null) return true;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            TreeNode temp;
            boolean flag = false;
            while (!queue.isEmpty()) {
                temp = queue.remove();
                if (temp == null){
                    flag = true;
                    continue;
                }
                if (flag) return false;
                queue.add(temp.left);
                queue.add(temp.right);
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
