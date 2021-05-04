package treenode;

/**
 * @author lumac
 * @since 9/22/20
 */
public class MonitorBinaryTree implements Tree {
    public int minCameraCover(TreeNode root) {
        int[] array = dfs(root);
        return array[1];
    }

    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] leftArray = dfs(root.left);
        int[] rightArray = dfs(root.right);
        int[] array = new int[3];
        array[0] = leftArray[2] + rightArray[2] + 1;
        array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], rightArray[0] + leftArray[1]));
        array[2] = Math.min(array[0], leftArray[1] + rightArray[1]);
        return array;
    }

    public static void main(String[] args) {
        MonitorBinaryTree tree = new MonitorBinaryTree();
        Integer[] datas = {0, 0, null, 0, null, 0, null, null, 0};
        TreeNode tr = tree.of(datas);
        BTreePrinter.printNode(tr);
        tree.dfs(tr);
    }
}
