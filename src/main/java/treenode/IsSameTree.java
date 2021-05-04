package treenode;

/**
 * @author lumac
 * @since 2020/7/23
 */
public class IsSameTree implements Tree {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        //如果一直能递归,说明没有不相等的情况,base case
        if (p == null && q == null) return true;
        //有一边为空
        else if (p != null && q == null) return false;
        //p == null || q != null
        else if (p == null) return false;
        else {
            //都不为空
            if (p.val == q.val) return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            else return false;
        }
    }
}
