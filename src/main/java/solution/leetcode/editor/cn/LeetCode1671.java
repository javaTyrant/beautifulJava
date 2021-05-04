package solution.leetcode.editor.cn;

public class LeetCode1671 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumMountainRemovals(int[] nums) {
            int len = nums.length;
            int[] dp1 = new int[nums.length];
            int[] dp2 = new int[nums.length];
            dp1[0] = 1;
            dp1[len - 1] = 1;
            for (int i = 1; i < len; i++) {
                int tempMax1 = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] > nums[j]) {
                        tempMax1 = Math.max(tempMax1, dp1[j]);
                    }
                }
                dp1[i] = tempMax1 + 1;
            }
            for (int i = len - 1; i >= 0; i--) {
                int tmp = 0;
                for (int j = i + 1; j < len; j++) {
                    if (nums[i] > nums[j]) {
                        tmp = Math.max(tmp, dp2[j]);
                    }
                }
                dp2[i] = tmp + 1;
            }
            int maxLen = 0;
            for (int i = 1; i < len - 1; i++) maxLen = Math.max(dp1[i] + dp2[i] - 1, maxLen);
            return len - maxLen;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
