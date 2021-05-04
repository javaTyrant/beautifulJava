package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode486 {
    class Solution1 {
        //时间复杂度 2的n次方
        public boolean PredictTheWinner(int[] nums) {
            return total(nums, 0, nums.length - 1, 1) >= 0;
        }

        public int total(int[] nums, int start, int end, int turn) {
            if (start == end) {
                return nums[start] * turn;
            }
            int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
            int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
            return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            int[] arr = {3, 9, 1, 2};
            PredictTheWinner(arr);
        }

        public static boolean PredictTheWinner(int[] nums) {
            int sum = 0;
            for (int n : nums)
                sum += n;
            int len = nums.length;
            int[][] dp = new int[len][len];
            for (int i = 0; i < len; i++)
                dp[i][i] = nums[i];
            for (int j = 1; j < len; j++)
                dp[j - 1][j] = Math.max(dp[j - 1][j - 1], dp[j][j]);
            // 按照对角线来递推
            for (int i = 2; i < len; i++)
                for (int row = 0; i + row < len; row++)
                    dp[row][row + i] = Math.max(nums[row] + Math.min(dp[row + 1][i + row - 1], dp[row + 2][i + row]),
                            nums[i + row] + Math.min(dp[row][i + row - 2], dp[row + 1][i + row - 1]));
            for (int[] d : dp) {
                System.out.println(Arrays.toString(d));
            }
            return dp[0][len - 1] >= (sum - dp[0][len - 1]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
