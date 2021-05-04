package binarysearch;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/5/26
 */
public class SortColour {
    public static void main(String[] args) {
        int[] colors = {0, 1, 0, 1, 2, 1, 2};
        sortColors(colors);
        System.out.println(Arrays.toString(colors));
    }

    //颜色分类,三色国旗问题
    public static void sortColors(int[] nums) {
        //双指针
        int low = 0, high = nums.length - 1;
        int i = 0;
        while (i <= high) {
            if (nums[i] == 0) {
                swap(nums, low, i);
                ++low;
                ++i;
            } else if (nums[i] == 1) {
                ++i;
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
}
