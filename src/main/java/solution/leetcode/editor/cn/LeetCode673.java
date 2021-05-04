package solution.leetcode.editor.cn;

public class LeetCode673 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            int[] arr = {1, 3, 5, 4, 7};
            int[] arr1 = {2, 2, 2, 2, 2};
            System.out.println(findNumberOfLIS(arr));
            System.out.println(findNumberOfLIS(arr1));
        }

        public static int findNumberOfLIS(int[] nums) {
            int[] dp = new int[nums.length];
            int[] count = new int[nums.length];
            int max = 0, result = 0;
            for (int i = 0; i < nums.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        if (dp[j] + 1 > dp[i]) {
                            count[i] = count[j];
                            dp[i] = dp[j] + 1;
                        } else if (dp[j] + 1 == dp[i]) {
                            count[i] += count[j];
                        }
                    }
                }
                count[i] = Math.max(count[i], 1);
                if (dp[i] > max) {
                    result = count[i];
                    max = dp[i];
                } else if (dp[i] == max) {
                    result += count[i];
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
