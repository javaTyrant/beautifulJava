package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode97 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            String s = "aa";
            String s2 = "cc";
            String s3 = "aacc";
            Solution solution = new Solution();
            System.out.println(solution.isInterleave(s, s2, s3));
        }

        //交错字符串
        public boolean isInterleave(String s1, String s2, String s3) {
            int n = s1.length(), m = s2.length(), t = s3.length();
            if (n + m != t) {
                return false;
            }
            //dp数组,多给一个
            boolean[][] dp = new boolean[n + 1][m + 1];
            dp[0][0] = true;
            //经典的双循环
            for (int i = 0; i <= n; ++i) {
                for (int j = 0; j <= m; ++j) {
                    //s3
                    int p = i + j - 1;
                    //i > 0
                    if (i > 0) {
                        dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                    }
                    // j > 0
                    if (j > 0) {
                        dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                    }
                }
            }
            System.out.println(Arrays.deepToString(dp));
            return dp[n][m];
        }

        //滚动数组优化
        public boolean isInterleaveOpt(String s1, String s2, String s3) {
            int n = s1.length(), m = s2.length(), t = s3.length();

            if (n + m != t) {
                return false;
            }

            boolean[] f = new boolean[m + 1];

            f[0] = true;
            for (int i = 0; i <= n; ++i) {
                for (int j = 0; j <= m; ++j) {
                    int p = i + j - 1;
                    if (i > 0) {
                        f[j] = f[j] && s1.charAt(i - 1) == s3.charAt(p);
                    }
                    if (j > 0) {
                        f[j] = f[j] || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                    }
                }
            }

            return f[m];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
