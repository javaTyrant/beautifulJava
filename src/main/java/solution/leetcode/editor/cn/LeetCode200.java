package solution.leetcode.editor.cn;

public  class LeetCode200{
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        void dfs(char[][] grid, int r, int c) {
            int nr = grid.length;
            int nc = grid[0].length;
            //剪枝,仔细分析一下就出来了,就是几个边界值的判断嘛
            if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
                return;
            }
            //置零
            grid[r][c] = '0';
            //上下左右,顺序不影响
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }

        public int numIslands(char[][] grid) {
            //先判空
            if (grid == null || grid.length == 0) {
                return 0;
            }
            //row
            int nr = grid.length;
            //column
            int nc = grid[0].length;
            //res
            int numIslands = 0;
            //经典的两层遍历
            for (int r = 0; r < nr; ++r) {
                for (int c = 0; c < nc; ++c) {
                    //==1再dfs
                    if (grid[r][c] == '1') {
                        //++
                        ++numIslands;
                        dfs(grid, r, c);
                    }
                }
            }

            return numIslands;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
