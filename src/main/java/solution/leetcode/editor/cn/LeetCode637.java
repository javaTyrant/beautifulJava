package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.ArrayList;
import java.util.List;

public class LeetCode637 {
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
        public List<Double> averageOfLevels(TreeNode root) {
            List<double[]> sumCountList = new ArrayList<>();
            levelOrderBottom(root, 0, sumCountList);
            List<Double> res = new ArrayList<>();
            for (double[] sumCount : sumCountList) {
                res.add(sumCount[0] * 1.0d / sumCount[1]);
            }
            return res;
        }

        private void levelOrderBottom(TreeNode root, int level, List<double[]> res) {
            if (root == null) return;
            if (res.size() <= level) res.add(new double[2]);
            double[] sumCount = res.get(level);
            sumCount[0] += root.val;
            sumCount[1]++;
            levelOrderBottom(root.left, level + 1, res);
            levelOrderBottom(root.right, level + 1, res);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
