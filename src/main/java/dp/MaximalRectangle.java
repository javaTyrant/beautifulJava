package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lumac
 * @since 2020/7/18
 */
public class MaximalRectangle {
    //最大矩形
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix[0].length;
        int res = 0;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        for (char[] chars : matrix) {
            int curLeft = 0, curRight = n;

            for (int j = 0; j < n; j++) {
                if (chars[j] == '1') height[j]++;
                else height[j] = 0;
            }

            for (int j = 0; j < n; j++) {
                if (chars[j] == '1') {
                    left[j] = Math.max(curLeft, left[j]);
                } else {
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }

            for (int j = n - 1; j >= 0; j--) {
                if (chars[j] == '1') {
                    right[j] = Math.min(curRight, right[j]);
                } else {
                    right[j] = n;
                    curRight = j;
                }
            }

            for (int j = 0; j < n; j++) {
                res = Math.max(res, height[j] * (right[j] - left[j]));
            }
        }
        return res;
    }

    public int maximalSquareRaw(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        //如何优化成一维数组呢
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 0;
                    } else {
                        //其他三个值的最小值 + 1
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    //保存最大值
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    //最大正方形
    //0 1 1 1 0
    //1 1 1 1 0
    //0 1 1 1 1
    //0 1 1 1 1
    //0 0 1 1 1
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        //一维数组
        int[] dp = new int[cols + 1];
        //额外多一个prev
        int maxsqlen = 0, prev = 0;
        //开始遍历
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                //取出temp
                int temp = dp[j];
                //same same
                if (matrix[i - 1][j - 1] == '1') {
                    //dp[j-1] prev dp[j] 选最小
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    //same
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    //0
                    dp[j] = 0;
                }
                //保存prev
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }

    //股票二
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                max += prices[i] - prices[i - 1];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        List<Integer> b = new ArrayList<>();
        b.add(3);
        b.add(4);
        a.retainAll(b);
        System.out.println(a.size());
    }
}
