package solution.leetcode.editor.cn;

public class LeetCode279 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //完全平方数
        //时间复杂度O(n*genhao)
        public static int numSquares(int n) {
            int[] dp = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                dp[i] = Integer.MAX_VALUE;
            }
            for (int i = 1; i <= n; i++) {
                //
                for (int j = 1; j * j <= i; j++) {
                    if (i >= j * j) {
                        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                    }
                }
            }
            return dp[n];
        }

        public static void main(String[] args) {
            System.out.println(numSquares(13));
        }

        protected boolean isSquare(int n) {
            int sq = (int) Math.sqrt(n);
            return n == sq * sq;
        }

        public int numSquaresMath(int n) {
            // four-square and three-square theorems.
            while (n % 4 == 0)
                n /= 4;
            if (n % 8 == 7)
                return 4;

            if (this.isSquare(n))
                return 1;
            // enumeration to check if the number can be decomposed into sum of two squares.
            for (int i = 1; i * i <= n; ++i) {
                if (this.isSquare(n - i * i))
                    return 2;
            }
            // bottom case of three-square theorem.
            return 3;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
