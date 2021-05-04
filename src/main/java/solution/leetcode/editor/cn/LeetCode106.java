import treenode.Tree;

import java.util.Deque;
import java.util.LinkedList;

public class LeetCode106 {
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
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return helper(inorder, postorder, postorder.length - 1, 0, inorder.length - 1);
        }

        public TreeNode helper(int[] inorder, int[] postorder, int postEnd, int inStart, int inEnd) {
            if (inStart > inEnd) {
                return null;
            }

            int currentVal = postorder[postEnd];
            TreeNode current = new TreeNode(currentVal);

            int inIndex = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == currentVal) {
                    inIndex = i;
                }
            }
            TreeNode left = helper(inorder, postorder, postEnd - (inEnd - inIndex) - 1, inStart, inIndex - 1);
            TreeNode right = helper(inorder, postorder, postEnd - 1, inIndex + 1, inEnd);
            current.left = left;
            current.right = right;
            return current;
        }

        public TreeNode buildTreeStack(int[] inorder, int[] postorder) {
            if (postorder == null || postorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[postorder.length - 1]);
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            int inorderIndex = inorder.length - 1;
            for (int i = postorder.length - 2; i >= 0; i--) {
                int postorderVal = postorder[i];
                TreeNode node = stack.peek();
                if (node.val != inorder[inorderIndex]) {
                    node.right = new TreeNode(postorderVal);
                    stack.push(node.right);
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex--;
                    }
                    node.left = new TreeNode(postorderVal);
                    stack.push(node.left);
                }
            }
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
