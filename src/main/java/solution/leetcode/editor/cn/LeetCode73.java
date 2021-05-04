package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode73 {
    static class Solution2 {
        public void setZeroes(int[][] matrix) {
            int row = matrix.length;
            int col = matrix[0].length;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = 0;
                        matrix[0][j] = 0;
                    }
                }
            }

        }
    }

    static class Solution1 {
        public static void main(String[] args) {
            Solution1 solution = new Solution1();
            int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
            solution.setZeroes(matrix);
            System.out.println(Arrays.deepToString(matrix));
        }

        //记录下行号,然后全部更新成0
        public void setZeroes(int[][] matrix) {
            int R = matrix.length;
            int C = matrix[0].length;
            Set<Integer> rows = new HashSet<>();
            Set<Integer> cols = new HashSet<>();

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (matrix[i][j] == 0) {
                        rows.add(i);
                        cols.add(j);
                    }
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (rows.contains(i) || cols.contains(j)) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        public void setZeroesOpt1(int[][] matrix) {
            int MODIFIED = -1000000;
            int R = matrix.length;
            int C = matrix[0].length;

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (matrix[r][c] == 0) {
                        // We modify the corresponding rows and column elements in place.
                        // Note, we only change the non zeroes to MODIFIED
                        for (int k = 0; k < C; k++) {
                            if (matrix[r][k] != 0) {
                                matrix[r][k] = MODIFIED;
                            }
                        }
                        for (int k = 0; k < R; k++) {
                            if (matrix[k][c] != 0) {
                                matrix[k][c] = MODIFIED;
                            }
                        }
                    }
                }
            }

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    // Make a second pass and change all MODIFIED elements to 0 """
                    if (matrix[r][c] == MODIFIED) {
                        matrix[r][c] = 0;
                    }
                }
            }
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        /**
         * 给定一个 m x n 的矩阵，如果一个元素为 0，
         * 则将其所在行和列的所有元素都设为 0。请使用原地算法。
         * 输入:
         * [
         * [1,1,1],
         * [1,0,1],
         * [1,1,1]
         * ]
         * 输出:
         * [
         * [1,0,1],
         * [0,0,0],
         * [1,0,1]
         * ]
         */
        public void setZeroes(int[][] matrix) {
            boolean b = false;
            int row = matrix.length;
            int col = matrix[0].length;
            //两次遍历
            for (int i = 0; i < row; i++) {
                //只要第一列有一个为0就是true
                if (matrix[i][0] == 0) b = true;
                //j为什么从1开始,因为第一列是不需要处理的,有0就是0
                for (int j = 1; j < col; j++) {
                    if (matrix[i][j] == 0) {
                        matrix[i][0] = matrix[0][j] = 0;
                    }
                }
            }
            for (int[] m : matrix) {
                System.out.println(Arrays.toString(m));
            }
            System.out.println("----");
            //还一个思想是我们从左,上标记,那么从底部开始置零.
            for (int i = row - 1; i >= 0; i--) {
                for (int j = col - 1; j >= 1; j--) {
                    if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                        for (int[] m : matrix) {
                            System.out.println(Arrays.toString(m));
                        }
                        System.out.println("----");
                    }
                }
                if (b) {
                    matrix[i][0] = 0;
                    for (int[] m : matrix) {
                        System.out.println(Arrays.toString(m));
                    }
                    System.out.println("----");
                }
            }
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            //int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
            int[][] matrix = {{1, 2, 3, 4}, {5, 0, 7, 8}, {0, 10, 13, 12}, {13, 14, 15, 0}};
            solution.setZeroes(matrix);
            System.out.println(Arrays.deepToString(matrix));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
