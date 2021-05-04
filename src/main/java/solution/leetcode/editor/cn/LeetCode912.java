package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode912 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static int[] sortArray(int[] nums) {
            qSort(nums, 0, nums.length - 1);
            return nums;
        }

        private static void qSort(int[] nums, int left, int right) {
            if (left >= right) {
                return;
            }
            int p = partition(nums, left, right);
            qSort(nums, left, p - 1);
            qSort(nums, p + 1, right);
        }

        private static int partition(int[] nums, int left, int right) {
            int temp = nums[left];
            while (left < right) {
                while (left < right && nums[right] >= temp) right--;
                if (left < right) {
                    nums[left] = nums[right];
                }
                while (left < right && nums[left] <= temp) left++;
                if (left < right) {
                    nums[right] = nums[left];
                }
            }
            nums[left] = temp;
            return left;
        }

        public static void main(String[] args) {
            int[] num = {8, 9, 5, 3, 9, 8, 2};
            sortArray(num);
            System.out.println(Arrays.toString(num));
            //partition(num, 0, num.length - 1);
            //System.out.println(Arrays.toString(num));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
