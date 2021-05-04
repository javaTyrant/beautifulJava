package treenode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lumac
 * @since 2020/7/30
 */
public class FindLeft implements Tree {
    //找树左下角的值
    //bfs
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) queue.offer(root.right);
            if (root.left != null) queue.offer(root.left);
        }
        return root.val;
    }

    //dfs
    int deep = -1;
    int ans = 0;

    public void dfs(TreeNode rt, int h) {
        if (rt == null) return;
        if (h > deep) {
            deep = h;
            ans = rt.val;
        }
        dfs(rt.left, h + 1);
        dfs(rt.right, h + 1);
    }

    public int findBottomLeftValue2(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    public static int translateNum(int num) {
        if (num <= 9) return 1;
        int ba = num % 100;
        return (ba <= 9 || ba >= 26) ? translateNum(num / 10) : translateNum(num / 10) + translateNum(num / 100);
    }

    //剑指 Offer 33. 二叉搜索树的后序遍历序列
    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0) {
            return true;
        }
        return verify(postorder, 0, postorder.length - 1);
    }

    private boolean verify(int[] seq, int start, int end) {
        if (start >= end) {
            return true;
        }
        int i = start, j = end - 1;
        while (i < end && seq[i] < seq[end]) {
            i++;
        }
        while (j >= start && seq[j] > seq[end]) {
            j--;
        }
        if (i < j) {
            return false;
        }
        return verify(seq, start, i - 1) && verify(seq, j + 1, end - 1);
    }
}

