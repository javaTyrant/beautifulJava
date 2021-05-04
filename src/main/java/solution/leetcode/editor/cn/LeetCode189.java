package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode189 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        //1,2,3,4,5 -> 1,5,4,3,2
        public static void reverse(int[] nums, int start, int end) {
            //<
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
            System.out.println(Arrays.toString(nums));
        }

        public static void main(String[] args) {
            //5,6,7,1,2,3,4
            int[] arr = {1, 2, 3, 4, 5, 6, 7};
            rotate(arr, 3);
            System.out.println(Arrays.toString(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
