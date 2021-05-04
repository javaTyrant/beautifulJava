package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LeetCode105 {
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
        //记录元素和索引的关系
        Map<Integer, Integer> map = new HashMap<>();

        //你可以假设树中没有重复的元素。
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            //中序遍历入map,确定左右子树的大小
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            //
            return buildTree(preorder, 0, preorder.length - 1, 0);
        }

        //要搞懂两个排序的作用
        //前序遍历确定根
        //中序遍历确定左右子树的大小
        private TreeNode buildTree(int[] preorder, int preLeft, int preRight, int inLeft) {
            //递归的base case
            if (preLeft > preRight) return null;
            //获取根节点的位置:获取根节点在中序遍历中的位置
            int rootPosition = map.get(preorder[preLeft]);
            //获取左树的大小 根 - 左
            int leftTreeSize = rootPosition - inLeft;
            //确定根节点
            TreeNode curRoot = new TreeNode(preorder[preLeft]);
            //左+1,右->左+左树树的大小,inleft
            curRoot.left = buildTree(preorder, preLeft + 1, preLeft + leftTreeSize, inLeft);
            //左+树的大小+1,右,根+1
            curRoot.right = buildTree(preorder, preLeft + leftTreeSize + 1, preRight, rootPosition + 1);
            return curRoot;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] preorder = {1, 2, 4, 5, 3, 6, 7};
            int[] inorder = {4, 2, 5, 1, 6, 3, 7};
            solution.buildTree(preorder, inorder);
        }

        public TreeNode buildTreeDie(int[] preorder, int[] inorder) {
            if (preorder == null || preorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            int inorderIndex = 0;
            for (int i = 1; i < preorder.length; i++) {
                int preorderVal = preorder[i];
                TreeNode node = stack.peek();
                if (node.val != inorder[inorderIndex]) {
                    node.left = new TreeNode(preorderVal);
                    stack.push(node.left);
                } else {
                    while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                        node = stack.pop();
                        inorderIndex++;
                    }
                    node.right = new TreeNode(preorderVal);
                    stack.push(node.right);
                }
            }
            return root;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
