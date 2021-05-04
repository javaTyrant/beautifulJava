package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.ArrayList;
import java.util.List;

public class LeetCode863 {
    class Solution1 implements Tree {
        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            List<Integer> res = new ArrayList<>();
            dfs(res, root, target, K, -1);
            return res;
        }

        private int dfs(List<Integer> res, TreeNode root, TreeNode target, int K, int dis) {
            if (root == null || target == null) {
                return -1;
            }
            if (dis == K) {
                res.add(root.val);
                return -1;
            }
            if (root == target) {
                dis = 1;
            } else if (dis > 0) {
                ++dis;
            }
            int left = dfs(res, root.left, target, K, dis);
            int right = dfs(res, root.right, target, K, dis);
            if (root == target) {
                if (K == 0) {
                    res.add(root.val);
                    return -1;
                }
                return 1;
            }
            if (left == K || right == K) {
                res.add(root.val);
                return -1;
            }
            if (left >= 0) {
                dfs(res, root.right, target, K, left + 1);
                return left + 1;
            }
            if (right >= 0) {
                dfs(res, root.left, target, K, right + 1);
                return right + 1;
            }
            return -1;
        }
    }
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
        List<Integer> li;

        public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
            li = new ArrayList<>();
            distanceExec(root, target, K, -1, -1);
            return li;
        }

        private int distanceExec(TreeNode node, TreeNode target, int K, int toBot, int toTop) {
            // node != null
            if (node == null) return -1;
            if (node == target) {
                // 可以向下、可以向上
                // 向下:
                if (node.left != null) distanceExec(node.left, target, K, 0, toTop);
                if (node.right != null) distanceExec(node.right, target, K, 0, toTop);
                // 向上:
                if (K == 0) li.add(node.val);
                return 0;
            }

            // 尝试向下走
            if (toBot != -1) {
                if (++toBot == K) {
                    li.add(node.val);
                    return -1;
                }
                if (node.left != null) distanceExec(node.left, target, K, toBot, toTop);
                if (node.right != null) distanceExec(node.right, target, K, toBot, toTop);
            } else {
                // 还没遇到 向下试探
                int l = distanceExec(node.left, target, K, toBot, toTop);
                int r = distanceExec(node.right, target, K, toBot, toTop);
                int res = -1;
                if (l != -1) {
                    if (++l == K) {
                        li.add(node.val);
                        return -1;
                    }
                    distanceExec(node.right, target, K, l, toTop);
                    return l;
                }

                if (r != -1) {
                    if (++r == K) {
                        li.add(node.val);
                        return -1;
                    }

                    distanceExec(node.left, target, K, r, toTop);
                    return r;
                }
            }

            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
