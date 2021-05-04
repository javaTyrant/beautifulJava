package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode877 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //see 预测赢家
        public static void main(String[] args) {
            int[] stones = {5, 3, 4, 5};
            System.out.println(stoneGame2(stones));
        }

        public static boolean stoneGame2(int[] piles) {
            int length = piles.length;
            //定义二维数组dp，其行数和列数都等于石子的堆数，
            //dp[i][j] 表示当剩下的石子堆为下标 i 到下标 j 时，
            // 当前玩家与另一个玩家的石子数量之差的最大值，注意当前玩家不一定是先手Alex。
            int[][] dp = new int[length][length];
            for (int i = 0; i < length; i++) {
                dp[i][i] = piles[i];
            }
            for (int i = length - 2; i >= 0; i--) {
                for (int j = i + 1; j < length; j++) {
                    dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
                }
            }
            for (int[] d : dp) {
                System.out.println(Arrays.toString(d));
            }
            return dp[0][length - 1] > 0;
        }

        //滚动数组优化
        public boolean stoneGame(int[] piles) {
            int length = piles.length;
            int[] dp = new int[length];
            for (int i = 0; i < length; i++) {
                dp[i] = piles[i];
            }
            for (int i = length - 2; i >= 0; i--) {
                for (int j = i + 1; j < length; j++) {
                    dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
                }
            }
            return dp[length - 1] > 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
