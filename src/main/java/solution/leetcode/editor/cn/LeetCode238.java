package solution.leetcode.editor.cn;

public class LeetCode238 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int left = 1;
            int right = 1;
            int len = nums.length;
            int[] dp = new int[len];
            for (int i = 0; i < len; i++) {
                dp[i] = left;
                left *= nums[i];
            }
            for (int i = len - 1; i >= 0; i--) {
                dp[i] *= right;
                right *= nums[i];
            }
            return dp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
