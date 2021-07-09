package current;

import treenode.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lufengxiang
 * @since 2021/6/30
 **/
public class CodeC implements Tree {
    //剑指 Offer 37. 序列化二叉树
    public List<Integer> serialize(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }

    private void dfs(List<Integer> res, TreeNode root) {
        if (root == null) {
            res.add(null);
        } else {
            res.add(root.val);
            dfs(res, root.left);
            dfs(res, root.right);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(List<Integer> data) {
        int index[] = {0};
        TreeNode root = build(index, data);
        return root;
    }

    private TreeNode build(int[] index, List<Integer> data) {
        Integer val = data.get(index[0]);
        index[0] = index[0] + 1;
        if (val == null) {
            return null;
        } else {
            TreeNode node = new TreeNode(val);
            node.left = build(index, data);
            node.right = build(index, data);
            return node;
        }
    }

    //删除有序数组中的重复项
    //输入：nums = [0,0,1,1,1,2,2,3,3,4]
    //输出：5, nums = [0,1,2,3,4]
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        //判断无输入
        int number = 0;//标记计数
        for (int i = 0; i < nums.length; i++) {
            //不相等才操作
            if (nums[i] != nums[number]) {
                //number++
                number++;
                nums[number] = nums[i];
            }
        }
        number += 1; //标记+1即为数字个数
        System.out.println(Arrays.toString(nums));
        return number;
    }

    //75. 颜色分类
    public static void sortColors(int[] nums) {
        //双指针
        int low = 0, high = nums.length - 1;
        int i = 0;
        while (i <= high) {
            //如果等于0
            if (nums[i] == 0) {
                swap(nums, low, i);
                ++low;
                ++i;
                //如果==1
            } else if (nums[i] == 1) {
                ++i;
                //如果==2
            } else if (nums[i] == 2) {
                swap(nums, high, i);
                --high;
            }
        }
    }

    private static void swap(int[] nums, int low, int i) {
        int tmp = nums[i];
        nums[i] = nums[low];
        nums[low] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(arr));
        int[] arr1 = {0, 1, 0, 2, 1, 0};
        sortColors(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
