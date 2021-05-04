package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode283 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void moveZeroes(int[] nums) {
            int n = nums.length, left = 0, right = 0;
            while (right < n) {
                //right位置不为0,左右交换,left++
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                //一次之后right++
                right++;
            }
        }

        public static void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

        public static void main(String[] args) {
            int[] arr = {1, 0, 2, 3, 0};
            moveZeroes(arr);
            System.out.println(Arrays.toString(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
