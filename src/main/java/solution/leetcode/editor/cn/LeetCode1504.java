package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LeetCode1504 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //统计全 1 子矩形,有点难啊,干
        public static int numSubmat(int[][] mat) {
            int n = mat.length;
            int m = mat[0].length;
            int[][] row = new int[n][m];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (j == 0) {
                        row[i][j] = mat[i][j];
                    } else if (mat[i][j] != 0) {
                        row[i][j] = row[i][j - 1] + 1;
                    } else {
                        row[i][j] = 0;
                    }
                }
            }
            System.out.println(Arrays.deepToString(row));
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    int col = row[i][j];
                    for (int k = i; k >= 0 && col != 0; --k) {
                        col = Math.min(col, row[k][j]);
                        ans += col;
                    }
                }
            }
            return ans;
        }

        public static void main(String[] args) {
            int[][] mat = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
            System.out.println(numSubmat(mat));
        }

        //更难的单调栈
        public int numSubmatBest(int[][] mat) {
            int n = mat.length;
            int m = mat[0].length;
            int[][] row = new int[n][m];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (j == 0) {
                        row[i][j] = mat[i][j];
                    } else if (mat[i][j] != 0) {
                        row[i][j] = row[i][j - 1] + 1;
                    } else {
                        row[i][j] = 0;
                    }
                }
            }
            int ans = 0;
            for (int j = 0; j < m; ++j) {
                int i = 0;
                Deque<int[]> Q = new LinkedList<>();
                int sum = 0;
                while (i <= n - 1) {
                    int height = 1;
                    while (!Q.isEmpty() && Q.peekFirst()[0] > row[i][j]) {
                        // 弹出的时候要减去多于的答案
                        sum -= Q.peekFirst()[1] * (Q.peekFirst()[0] - row[i][j]);
                        height += Q.peekFirst()[1];
                        Q.pollFirst();
                    }
                    sum += row[i][j];
                    ans += sum;
                    Q.offerFirst(new int[]{row[i][j], height});
                    i++;
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
