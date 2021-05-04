package solution.leetcode.editor.cn;

import treenode.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lumac
 * @since 2020/12/28
 */
public class LCOF54 implements Tree {
    public static void main(String[] args) {
        LCOF54 solution = new LCOF54();
        TreeNode tree = solution.of(new Integer[]{3, 1, 4, null, 2});
        System.out.println(solution.kthLargest(tree, 1));
        System.out.println(solution.kthLargestBetter(tree, 1));
    }

    // 二叉搜索树的第k大节点 中序遍历 全部排序了,多做了很多事情
    //其实不需要全部都排序的
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = inOrder(root);
        return list.get(list.size() - k);
    }

    private List<Integer> inOrder(TreeNode node) {
        List<Integer> res = new ArrayList<>();
        dfs(res, node);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode node) {
        if (node == null) return;
        dfs(res, node.left);
        res.add(node.val);
        dfs(res, node.right);
    }

    private int ans = 0, cnt = 0;

    public int kthLargestBetter(TreeNode root, int k) {
        dfs(root, k);
        return ans;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) return;
        //先找右边
        dfs(root.right, k);
        //如果等于k
        if (++cnt == k) {
            ans = root.val;
            return;
        }
        dfs(root.left, k);
    }
}
