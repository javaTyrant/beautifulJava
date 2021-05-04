package solution.leetcode.editor.cn;

public class LeetCode48 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        /**
         * 给定 matrix =
         * [
         * [1,2,3],
         * [4,5,6],
         * [7,8,9]
         * ],
         * <p>
         * 原地旋转输入矩阵，使其变为:
         * [
         * [7,4,1],
         * [8,5,2],
         * [9,6,3]
         * ]
         */
        //旋转图像
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            // transpose matrix
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int tmp = matrix[j][i];
                    matrix[j][i] = matrix[i][j];
                    matrix[i][j] = tmp;
                }
            }
            // reverse each row
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n / 2; j++) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[i][n - j - 1];
                    matrix[i][n - j - 1] = tmp;
                }
            }
        }

        public static void main(String[] args) {
            int[][] matrix = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9}
            };
            Solution solution = new Solution();
            solution.rotate(matrix);
            System.out.println(matrix);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
