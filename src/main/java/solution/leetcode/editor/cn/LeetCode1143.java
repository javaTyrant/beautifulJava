package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode1143 {
    //leetcode submit region begin(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonSubsequence("abcde", "bde"));
    }

    //最长公共子序列
    static class Solution {
        //滚动数组的思路如何而来呢?
        public int longestCommonSubsequence(String text1, String text2) {
            int len1 = text1.length();
            int len2 = text2.length();
            if (len1 == 0 || len2 == 0) {
                return 0;
            }
            char[] t1 = text1.toCharArray();
            char[] t2 = text2.toCharArray();
            //一维数组
            int[] score = new int[len1 + 1];
            int last;
            // System.out.println(score);
            // 初始化
            for (int i = 0; i < len2; i++) {
                last = 0;
                for (int j = 1; j < len1 + 1; j++) {
                    // 状态转移函数
                    int temp = score[j];
                    //当两个位置的字符串相等
                    if (t1[j - 1] == t2[i]) {
                        score[j] = last + 1;
                        //
                    } else if (score[j] < score[j - 1]) {
                        score[j] = score[j - 1];
                    }
                    //跟新last
                    last = temp;
                }
                //System.out.println(score);
            }
            return score[len1];
        }

        //二维数组
        public int longestCommonSubsequenceTwo(String text1, String text2) {
            //
            if (text1.length() == 0 || text2.length() == 0) {
                return 0;
            }
            int[][] dp = new int[text1.length() + 1][text2.length() + 1];
            for (int i = 0; i <= text1.length(); i++) {
                for (int j = 0; j <= text2.length(); j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 0;
                        //如果相等+1
                    } else if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        //左下加1
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        //左,下的最大值
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[text1.length()][text2.length()];
        }

        //可见dp(i,j)是依赖于上一行的2个值，当前行的1个值；
        //由于直接变成一维数组，会导致i-1,j-1被当前行替换，因此只需提前保存上一行的i-1,j-1值，便可归纳为一维滚动数组。
        public int longestCommonSubsequenceOpt(String text1, String text2) {
            int raw = text1.length(), column = text2.length();
            //多用一个空间
            int[] dp = new int[column + 1];
            //全部赋值为0
            Arrays.fill(dp, 0);
            //为什么需要一个临时变量
            int pre;
            for (int i = 1; i <= raw; i++) {
                //
                pre = 0;
                for (int j = 1; j <= column; j++) {
                    //保存dp[i]最后赋值给pre
                    int temp = dp[j];//dp[j]会被修改,所以要存一下
                    if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                        //相当于保存了dp[i - 1][j - 1]
                        dp[j] = pre + 1;
                    } else {
                        // Math.max(dp[i - 1][j], dp[i][j - 1]);
                        dp[j] = Math.max(dp[j], dp[j - 1]);
                    }
                    //pre接着保存
                    pre = temp;
                }
            }
            return dp[column];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
