package solution.leetcode.editor.cn;

public class LeetCode695 {
    class Solution {
        private Integer max = Integer.MIN_VALUE;

        public int maxAreaOfIsland(int[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 1) {
                        int res = dfs(grid, i, j, 0);
                        if (res > max) {
                            max = res;
                        }
                    }
                }
            }
            return max;
        }

        private int dfs(int[][] grid, int i, int j, int count) {
            int row = grid.length;
            int col = grid[0].length;
            if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0) {
                return 0;
            }
            grid[i][j] = 0;
            count += dfs(grid, i + 1, j, count)
                    + dfs(grid, i - 1, j, count)
                    + dfs(grid, i, j + 1, count)
                    + dfs(grid, i, j - 1, count)
                    + 1;
            return count;
        }
    }
}
