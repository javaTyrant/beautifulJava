package solution.leetcode.editor.cn;

public class LeetCode494 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        void g(double d) {
            System.out.println(d + "1");
        }

        void g(Integer i) {
            System.out.println(i + "2");
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            solution.g(1);
            int[] arr = {1, 1, 1, 1, 1};
            System.out.println(findTargetSumWays(arr, 3));
        }

        public static int findTargetSumWays(int[] nums, int S) {
            int[][] dp = new int[nums.length][2001];
            dp[0][nums[0] + 1000] = 1;
            dp[0][-nums[0] + 1000] += 1;
            for (int i = 1; i < nums.length; i++) {
                for (int sum = -1000; sum <= 1000; sum++) {
                    if (dp[i - 1][sum + 1000] > 0) {
                        dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                        dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                    }
                }
            }
            return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
