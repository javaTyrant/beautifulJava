package solution.leetcode.editor.cn;

public class LeetCode329 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //矩阵中的最长递增路径
        //输入
        //[
        //  [9,9,4],
        //  [6,6,8],
        //  [2,1,1]
        //]
        //输出: 4
        //解释: 最长递增路径为[1, 2, 6, 9]
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
            int len = matrix.length;
            int wide = matrix[0].length;
            int[][] cache = new int[len][wide];
            int max = 0;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < wide; j++) {
                    int length = dfs(matrix, i, j, cache, -1);
                    max = Math.max(length, max);
                }
            }
            return max;

        }

        public int dfs(int[][] matrix, int i, int j, int[][] cache, int pre) {
            if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length || matrix[i][j] <= pre) {
                return 0;
            }
            if (cache[i][j] > 0) {
                return cache[i][j];
            }
            int temp = 0;
            int cur = matrix[i][j];
            temp = Math.max(temp, dfs(matrix, i - 1, j, cache, cur));
            temp = Math.max(temp, dfs(matrix, i, j - 1, cache, cur));
            temp = Math.max(temp, dfs(matrix, i, j + 1, cache, cur));
            temp = Math.max(temp, dfs(matrix, i + 1, j, cache, cur));
            temp++;
            //
            cache[i][j] += temp;
            return temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
