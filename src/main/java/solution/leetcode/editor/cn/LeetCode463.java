package solution.leetcode.editor.cn;

public class LeetCode463 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            int[][] arr = {
                    {0, 1, 0, 0},
                    {1, 1, 1, 0},
                    {0, 1, 0, 0},
                    {1, 1, 0, 0}};
            islandPerimeter(arr);
        }

        public static int islandPerimeter(int[][] grid) {
            //二维数组的宽度
            int len1 = grid.length;
            //二维数组的长度
            int len2 = grid[0].length;
            //周长初始化为0
            int perimeter = 0;
            //开始遍历
            for (int i = 0; i < len1; i++) {
                //只用考虑四种边界,优秀啊
                for (int j = 0; j < len2; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    //当前格子上面是水
                    if (i - 1 < 0 || grid[i - 1][j] == 0) {
                        perimeter++;
                    }
                    //当前格子左边是是
                    if (j - 1 < 0 || grid[i][j - 1] == 0) {
                        perimeter++;
                    }
                    //如果走到右边或者不在最右边且右边的格子是水
                    if (i + 1 == len1 || i + 1 < len1 && grid[i + 1][j] == 0) {
                        perimeter++;
                    }
                    //如果走到最下面或者不在最右边且下面的格子是水
                    if (j + 1 == len2 || j + 1 < len2 && grid[i][j + 1] == 0) {
                        perimeter++;
                    }
                }
            }
            return perimeter;
        }

        public static int islandPerimeterDfs(int[][] grid) {
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    if (grid[r][c] == 1) {
                        // 题目限制只有一个岛屿，计算一个即可
                        return dfs(grid, r, c);
                    }
                }
            }
            return 0;
        }

        static int dfs(int[][] grid, int r, int c) {
            if (!(0 <= r && r < grid.length && 0 <= c && c < grid[0].length)) {
                return 1;
            }
            if (grid[r][c] == 0) {
                return 1;
            }
            if (grid[r][c] != 1) {
                return 0;
            }
            grid[r][c] = 2;
            return dfs(grid, r - 1, c)
                    + dfs(grid, r + 1, c)
                    + dfs(grid, r, c - 1)
                    + dfs(grid, r, c + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
