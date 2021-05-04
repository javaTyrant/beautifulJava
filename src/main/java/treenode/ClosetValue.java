package treenode;

/**
 * @author lumac
 * @since 2020/7/18
 */
public class ClosetValue implements Tree {
    public static int closestValue(TreeNode root, double target) {
        int val;
        int closest = root.val;
        while (root != null) {
            val = root.val;
            closest = Math.abs(val - target) < Math.abs(closest - target) ? val : closest;
            root = target < root.val ? root.left : root.right;
        }
        return closest;
    }
}
