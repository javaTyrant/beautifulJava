package dfs;

import treenode.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author lumac
 * @since 2020/7/7
 */
public class PathSum implements Tree {
    //bfs
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        //存放node
        Queue<TreeNode> queNode = new LinkedList<>();
        //存放值
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            Integer temp = queVal.poll();
            //都为空了
            if (now.left == null && now.right == null) {
                //相等了
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            //先左后右
            if (now.left != null) {
                //放入left
                queNode.offer(now.left);
                //放入和
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }

    //dfs
    public boolean hasPathSumD(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSumD(root.left, sum - root.val) || hasPathSumD(root.right, sum - root.val);
    }

    //路径总和二
    //给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        hasPathSum2(root, sum, ans, path);
        return ans;
    }

    /**
     *
     * @param node 树
     * @param sum 和,剩余的和
     * @param ans 结果
     * @param path 路径
     */
    public void hasPathSum2(TreeNode node, int sum, List<List<Integer>> ans, List<Integer> path) {
        if (node == null) {
            return;
        }
        //add
        path.add(node.val);
        //成立条件
        if (node.left == null && node.right == null && node.val == sum) {
            ans.add(new ArrayList<>(path));
        } else {
            //
            hasPathSum2(node.left, sum - node.val, ans, path);
            hasPathSum2(node.right, sum - node.val, ans, path);
        }
        //remove最后一个值
        path.remove(path.size() - 1);
    }
}
