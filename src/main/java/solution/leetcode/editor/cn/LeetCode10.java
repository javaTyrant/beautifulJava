package solution.leetcode.editor.cn;

public class LeetCode10 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //'.' 匹配任意单个字符
        //'*' 匹配零个或多个前面的那一个元素
        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.isMatch("abc", "ab."));
            System.out.println(solution.isMatchO("mississippi", "mis*is*p*."));
        }

        //正则表达式匹配,这个解法也太牛逼了吧,时间复杂度O(mn)
        public boolean isMatch(String s, String p) {
            if (s.length() == 0 && p.length() == 0) return true;

            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;

            for (int i = 0; i < p.length(); ++i) {
                char c = p.charAt(i);
                boolean hasStar = false;
                if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
                    hasStar = true;
                    i++;
                }

                for (int j = dp.length - 1; j >= 0; j--) {
                    if (dp[j]) {
                        if (hasStar) {
                            for (int k = j + 1; k < dp.length; ++k) {
                                if (dp[k]) break;
                                else if (c == '.' || c == s.charAt(k - 1)) dp[k] = true;
                                else break;
                            }
                        } else {
                            // 如果j已经是s的最后一位
                            dp[j] = false;
                            if (j < dp.length - 1) {
                                if (c == '.' || c == s.charAt(j)) dp[j + 1] = true;
                            }
                        }
                    }
                }
            }

            return dp[dp.length - 1];
        }

        public boolean isMatchO(String s, String p) {
            int m = s.length();
            int n = p.length();

            boolean[][] f = new boolean[m + 1][n + 1];
            f[0][0] = true;
            for (int i = 0; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    //p的位置是否是*
                    if (p.charAt(j - 1) == '*') {
                        f[i][j] = f[i][j - 2];
                        if (matches(s, p, i, j - 1)) {
                            f[i][j] = f[i][j] || f[i - 1][j];
                        }
                    } else {//不是
                        if (matches(s, p, i, j)) {
                            f[i][j] = f[i - 1][j - 1];
                        }
                    }
                }
            }
            return f[m][n];
        }

        //s,p的i和j是否匹配,.只在这里面处理
        public boolean matches(String s, String p, int i, int j) {
            if (i == 0) {
                return false;
            }
            if (p.charAt(j - 1) == '.') {
                return true;
            }
            return s.charAt(i - 1) == p.charAt(j - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
