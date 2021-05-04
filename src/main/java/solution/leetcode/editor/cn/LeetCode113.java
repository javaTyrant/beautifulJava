package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.ArrayList;
import java.util.List;

public class LeetCode113 implements Tree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    static class Solution {
        /**
         * 路径总和 II
         */
        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> res = new ArrayList<>();
            findPaths(root, sum, res, new ArrayList<>());
            return res;
        }

        private void findPaths(TreeNode node, int sum, List<List<Integer>> paths, List<Integer> current) {
            if (node == null) {
                return;
            }
            current.add(node.val);
            if (node.val == sum && node.left == null && node.right == null) {
                //深拷贝
                paths.add(new ArrayList<>(current));
            } else {
                //左右开弓 sum要减去node
                findPaths(node.left, sum - node.val, paths, current);
                findPaths(node.right, sum - node.val, paths, current);
            }
            //remove
            current.remove(current.size() - 1);
        }
    }
}
