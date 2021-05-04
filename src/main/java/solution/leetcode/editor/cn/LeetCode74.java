package solution.leetcode.editor.cn;

public class LeetCode74 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static boolean searchMatrix(int[][] matrix, int target) {
            //if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
            if (matrix.length == 0)
                return false;
            //i往下走
            int i = 0;
            //j往左走
            int j = matrix[0].length - 1;
            //先找右上角
            while (i < matrix.length && j >= 0) {
                if (matrix[i][j] < target) {
                    i++;
                } else if (matrix[i][j] > target) {
                    j--;
                } else {
                    return true;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            int[][] arr = {{1, 1}, {2, 2}};
            System.out.println(searchMatrix(arr, 3));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
