package solution.leetcode.editor.cn;

public class LeetCode994 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int orangesRotting(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 2) {
                        dfs(grid, i, j, 2);
                    }
                }
            }
            int max = 2;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == 1) {
                        return -1;
                    }
                    max = Math.max(max, grid[i][j]);
                }
            }
            return max - 2;
        }

        public void dfs(int[][] grid, int i, int j, int val) {
            int maxRow = grid.length;
            int maxCol = grid[0].length;
            grid[i][j] = val;
            if (i > 0 && (grid[i - 1][j] == 1 || grid[i - 1][j] - grid[i][j] > 1)) {
                dfs(grid, i - 1, j, val + 1);
            }
            if (j > 0 && (grid[i][j - 1] == 1 || grid[i][j - 1] - grid[i][j] > 1)) {
                dfs(grid, i, j - 1, val + 1);
            }
            if (i < maxRow - 1 && (grid[i + 1][j] == 1 || grid[i + 1][j] - grid[i][j] > 1)) {
                dfs(grid, i + 1, j, val + 1);
            }
            if (j < maxCol - 1 && (grid[i][j + 1] == 1 || grid[i][j + 1] - grid[i][j] > 1)) {
                dfs(grid, i, j + 1, val + 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
