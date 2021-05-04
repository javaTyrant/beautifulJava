package array;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/6/4
 */
public class ProductofArrayExceptSelf {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(arr)));
    }

    /**
     * 左右法
     * 1,2,3,4
     * 从左往右
     * [1,1,2,6]
     * 从右往左
     * 最右边的其实已经是答案
     * right = 1
     */
    public static int[] productExceptSelf(int[] nums) {
        int left = 1;
        int right = 1;
        int len = nums.length;
        int[] output = new int[len];
        for (int i = 0; i < len; i++) {
            output[i] = left;
            left *= nums[i];
        }
        //注意j>=0,如果j > 0那么j=0的情况就不会考虑进去
        for (int j = len - 1; j >= 0; j--) {
            output[j] *= right;
            right *= nums[j];
        }
        return output;
    }
}
