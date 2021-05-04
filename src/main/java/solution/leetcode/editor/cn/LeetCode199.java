package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode199 {
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
        //二叉树的右视图
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;
            //队列
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.poll();
                    //如果i==0 add
                    if (i == 0) res.add(cur.val);
                    //先加右,再加左
                    if (cur.right != null) queue.offer(cur.right);
                    if (cur.left != null) queue.offer(cur.left);
                }
            }
            return res;
        }

        //二叉树的右视图
        public List<Integer> rightSideViewDfs(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            dfs(res, root, 0);
            return res;
        }

        private void dfs(List<Integer> res, TreeNode node, int level) {
            if (node != null) {
                if (res.size() == level) {
                    res.add(node.val);
                }
                dfs(res, node.right, level + 1);
                dfs(res, node.left, level + 1);
            }
        }

        //二叉树的层次遍历
        List<List<Integer>> list = new ArrayList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null)
                return list;
            helper(root, 0);
            return list;
        }

        public void helper(TreeNode node, int level) {
            //base case
            if (list.size() == level)
                list.add(new ArrayList<>());
            //
            list.get(level).add(node.val);
            //左边不为空
            if (node.left != null) helper(node.left, level + 1);
            //右边不为空
            if (node.right != null) helper(node.right, level + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
