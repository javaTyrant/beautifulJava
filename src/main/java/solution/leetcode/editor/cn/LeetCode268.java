package solution.leetcode.editor.cn;

public class LeetCode268 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
        public static void main(String[] args) {
            int[] arr = {9, 6, 4, 2, 3, 5, 7, 0, 1};
            System.out.println(missingNumber(arr));
        }

        public static int missingNumber(int[] nums) {
            int res = nums.length;
            for (int i = 0; i < nums.length; ++i) {
                res ^= nums[i];
                res ^= i;
            }
            return res;
        }

        public int missingNumberGaosi(int[] nums) {
            int expectedSum = nums.length * (nums.length + 1) / 2;
            int actualSum = 0;
            for (int num : nums) actualSum += num;
            return expectedSum - actualSum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
