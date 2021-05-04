package solution.leetcode.editor.cn;

import treenode.Tree;

public class LeetCode889 {
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
        public TreeNode constructFromPrePost(int[] pre, int[] post) {
            return helper(pre, post, 0, pre.length - 1, 0, post.length - 1);
        }

        public TreeNode helper(int[] pre, int[] post, int preStart, int preEnd, int postStart, int postEnd) {
            if (preStart > preEnd || postStart > postEnd) return null;
            TreeNode root = new TreeNode(pre[preStart]);
            if (preStart == preEnd)
                return root;
            int index = 0;
            //定位到index:数字都是不重复的
            while (post[index] != pre[preStart + 1]) {
                index++;
            }
            //举个例子来定位索引,不用背
            root.left = helper(pre, post, preStart + 1, preStart + 1 + index - postStart, postStart, index);
            //
            root.right = helper(pre, post, preStart + 2 + index - postStart, preEnd, index + 1, preEnd - 1);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
