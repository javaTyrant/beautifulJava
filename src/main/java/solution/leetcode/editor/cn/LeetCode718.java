package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode718 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            //int[] a = {1, 2, 3, 2, 1};
            //int[] b = {3, 2, 1, 4, 7};
            int[] a = {0, 0, 0, 0, 0};
            int[] b = {0, 0, 0, 0, 0};
            solution.findLength(a, b);
        }

        public int findLength(int[] A, int[] B) {
            if (A == null || B == null) return 0;
            int[][] dp = new int[A.length + 1][B.length + 1];
            for (int i = 1; i < A.length; i++) {
                for (int j = 1; j < B.length; j++) {
                    if (A[i] == B[j]) {
                        dp[i][j] = 1 + Math.max(dp[i - 1][j], dp[i][j - 1]);
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            for (int[] i : dp) {
                System.out.println(Arrays.toString(i));
            }
            return dp[A.length - 1][B.length - 1];
        }

        public int findLengthRight(int[] A, int[] B) {
            if (A == null || B == null) {
                return 0;
            }
            int res = 0;
            int[][] dp = new int[A.length + 1][B.length + 1];
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[i].length; j++) {
                    dp[i][j] = A[i - 1] == B[j - 1] ? dp[i - 1][j - 1] + 1 : 0;
                    res = Math.max(res, dp[i][j]);
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
