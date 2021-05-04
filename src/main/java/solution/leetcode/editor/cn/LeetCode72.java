package solution.leetcode.editor.cn;

public class LeetCode72 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.minDistance("rose", "raie"));
        }

        public int minDistance(String word1, String word2) {
            int i = word1.length();
            int j = word2.length();
            if (i * j == 0) return i + j;
            //多加1好处理边界值
            int[][] dp = new int[i + 1][j + 1];
            //边界赋值
            for (int k = 0; k < i + 1; k++) {
                dp[k][0] = k;
            }
            for (int k = 0; k < j + 1; k++) {
                dp[0][k] = k;
            }
            for (int row = 1; row < i + 1; row++) {
                for (int col = 1; col < j + 1; col++) {
                    //为什么要加1呢
                    int left = dp[row - 1][col] + 1;
                    int down = dp[row][col - 1] + 1;
                    int leftDown = dp[row - 1][col - 1];
                    if (word1.charAt(row - 1) != word2.charAt(col - 1)) {
                        leftDown += 1;
                    }
                    dp[row][col] = Math.min(left, Math.min(down, leftDown));
                }
            }
            return dp[i][j];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
