package treenode;

/**
 * @author lumac
 * @since 2020/7/3
 */

public class ConvertToTree implements Tree {
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return helper(nums, 0, nums.length - 1);
    }

    public static TreeNode helper(int[] nums, int low, int high) {
        if (high < low) return null;
        //取中间
        int mid = (low + high) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = helper(nums, low, mid - 1);
        treeNode.right = helper(nums, mid + 1, high);
        return treeNode;
    }

    public static void main(String[] args) {
        int[] arr = {-10, -3, 0, 5, 9};
        System.out.println(sortedArrayToBST(arr));
    }
}
