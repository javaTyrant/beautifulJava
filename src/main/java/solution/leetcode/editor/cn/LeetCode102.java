package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode102 {
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
        //二叉树的层次遍历
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int level = 0;
            while (!queue.isEmpty()) {
                res.add(new ArrayList<>());
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = queue.remove();
                    //和递归一样的
                    res.get(level).add(cur.val);
                    //先左后右
                    if (cur.left != null) {
                        queue.offer(cur.left);
                    }
                    if (cur.right != null) {
                        queue.offer(cur.right);
                    }
                }
                //++
                level++;
            }
            return res;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            TreeNode node = solution.of(arr);
            System.out.println(solution.levelOrder(node));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
