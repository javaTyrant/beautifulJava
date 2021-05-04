package treenode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lumac
 * @since 2020/7/21
 */
public class AllTrees implements Tree {
    public List<TreeNode> generateTrees(int n) {
        //n==0
        if (n == 0) {
            return Collections.emptyList();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    //根
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

    public static void main(String[] args) {
        AllTrees all = new AllTrees();
        System.out.println(all.generateTrees(3));
    }
}
