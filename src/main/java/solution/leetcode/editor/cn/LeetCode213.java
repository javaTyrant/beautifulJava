package solution.leetcode.editor.cn;

public class LeetCode213 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //打家劫舍 II
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            //两次dp
            return Math.max(dp(nums, 0, n - 1), dp(nums, 1, n));
        }

        private int dp(int[] nums, int from, int to) {
            int prev2 = 0, prev1 = 0, curr;
            for (int i = from; i < to; i++) {
                curr = Math.max(prev1, prev2 + nums[i]);
                prev2 = prev1;
                prev1 = curr;
            }
            return prev1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
