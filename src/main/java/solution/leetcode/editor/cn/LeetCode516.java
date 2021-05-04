package solution.leetcode.editor.cn;

public class LeetCode516 {
    class Solution1 {
        public int longestPalindromeSubseq(String s) {
            int n = s.length();
            int[][] f = new int[n][n];
            for (int i = n - 1; i >= 0; i--) {
                f[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        f[i][j] = f[i + 1][j - 1] + 2;
                    } else {
                        f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                    }
                }
            }
            return f[0][n - 1];
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //516. 最长回文子序列
        public int longestPalindromeSubseq(String s) {
            //dp[i,j] i到 j 表示 i j 范围内的最长子序列的长度
            //最短为1
            char[] p = s.toCharArray();
            int[][] dp = new int[p.length + 1][p.length + 1];
            int n = p.length;
            for (int i = 1; i <= n; ++i) dp[i][i] = 1;
            //填表的顺序
            for (int i = n - 1; i > 0; --i) {
                for (int j = i + 1; j <= n; ++j) {
                    //如果相等
                    if (p[i - 1] == p[j - 1]) {
                        //+2
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        //否则,取最大的值
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                    //else

                }
            }
            //右上角
            return dp[1][n];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
