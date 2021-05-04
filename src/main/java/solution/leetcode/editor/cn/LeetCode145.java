package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.*;

public class LeetCode145 {
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
    static class Solution implements Tree {
        //二叉树的后序遍历
        public List<Integer> postorderTraversal(TreeNode root) {
            //栈
            List<Integer> res = new ArrayList<>();
            find(root, res);
            return res;
        }

        private void find(TreeNode node, List<Integer> res) {
            if (node == null) return;
            find(node.left, res);
            find(node.right, res);
            res.add(node.val);
        }

        /**
         * 策略
         * 1.
         */
        public List<Integer> postorderTraversal_(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            TreeNode prev = null;
            while (root != null || !stack.isEmpty()) {
                //先放入left
                while (root != null) {
                    stack.push(root);
                    //移动指针
                    root = root.left;
                }
                //pop
                root = stack.pop();
                //
                if (root.right == null || root.right == prev) {
                    res.add(root.val);
                    //
                    prev = root;
                    root = null;
                } else {
                    stack.push(root);
                    root = root.right;
                }
            }
            return res;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            TreeNode node = new TreeNode(2);
            node.left = new TreeNode(3);
            node.right = new TreeNode(4);
            node.right.right = new TreeNode(7);
            System.out.println(solution.postorderTraversal_(node));
        }

        //莫里斯遍历
        //Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其后序遍历规则总结如下：
        //新建临时节点，令该节点为 root；
        //如果当前节点的左子节点为空，则遍历当前节点的右子节点；
        //如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点；
        //如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点，当前节点更新为当前节点的左子节点。
        //如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。
        //倒序输出从当前节点的左子节点到该前驱节点这条路径上的所有节点。当前节点更新为当前节点的右子节点。
        //重复步骤 2 和步骤 3，直到遍历结束。
        //这样我们利用 Morris 遍历的方法，后序遍历该二叉搜索树，即可实现线性时间与常数空间的遍历。
        public List<Integer> postorderTraversalMorris(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            TreeNode p1 = root, p2;

            while (p1 != null) {
                p2 = p1.left;
                if (p2 != null) {
                    while (p2.right != null && p2.right != p1) {
                        p2 = p2.right;
                    }
                    if (p2.right == null) {
                        p2.right = p1;
                        p1 = p1.left;
                        continue;
                    } else {
                        p2.right = null;
                        addPath(res, p1.left);
                    }
                }
                p1 = p1.right;
            }
            addPath(res, root);
            return res;
        }

        public void addPath(List<Integer> res, TreeNode node) {
            int count = 0;
            while (node != null) {
                ++count;
                res.add(node.val);
                node = node.right;
            }
            int left = res.size() - count, right = res.size() - 1;
            while (left < right) {
                int temp = res.get(left);
                res.set(left, res.get(right));
                res.set(right, temp);
                left++;
                right--;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
