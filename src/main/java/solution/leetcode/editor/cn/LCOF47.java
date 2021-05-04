package solution.leetcode.editor.cn;

//礼物的最大价值
public class LCOF47 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxValueTwo(int[][] grid) {
            int row = grid.length;
            int column = grid[0].length;
            //dp[i][j]表示从grid[0][0]到grid[i - 1][j - 1]时的最大价值
            int[][] dp = new int[row + 1][column + 1];
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= column; j++) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
                }
            }
            return dp[row][column];
        }

        /**
         * 输入:
         * [
         * [1,3,1],
         * [1,5,1],
         * [4,2,1]
         * ]
         * 输出: 12
         * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
         */
        public int maxValue(int[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
            int[] dp = new int[col + 1];
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    //grid的值 + dp[j-1],dp[j]的最大值
                    dp[j] = grid[i - 1][j - 1] + Math.max(dp[j - 1], dp[j]);
                }
            }
            return dp[col];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
