package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode474 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            String[] arr = {"10", "0001", "111001", "1", "0"};
            System.out.println(findMaxForm(arr, 5, 3));
        }

        //我们用 dp(i, j) 表示使用 i 个 0 和 j 个 1，最多能拼出的字符串数目
        public static int findMaxForm(String[] strs, int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            for (String s : strs) {
                int[] count = countzeroesones(s);
                for (int zeroes = m; zeroes >= count[0]; zeroes--)
                    for (int ones = n; ones >= count[1]; ones--) {
                        //选择当前的.当然要加1了,不用当前的.
                        dp[zeroes][ones] = Math.max(1 + dp[zeroes - count[0]][ones - count[1]], dp[zeroes][ones]);
                    }
                System.out.println(s);
                for (int[] d : dp) {
                    System.out.println(Arrays.toString(d));
                }
            }
            return dp[m][n];
        }
        //统计0和1的数量
        public static int[] countzeroesones(String s) {
            int[] c = new int[2];
            for (int i = 0; i < s.length(); i++) {
                c[s.charAt(i) - '0']++;
            }
            return c;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
