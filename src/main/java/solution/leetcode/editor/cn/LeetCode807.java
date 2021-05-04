package solution.leetcode.editor.cn;

public class LeetCode807 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //牛逼
        public static int maxIncreaseKeepingSkyline(int[][] grid) {
            int N = grid.length;
            int[] rowMaxes = new int[N];
            int[] colMaxes = new int[N];

            for (int r = 0; r < N; ++r)
                for (int c = 0; c < N; ++c) {
                    rowMaxes[r] = Math.max(rowMaxes[r], grid[r][c]);
                    colMaxes[c] = Math.max(colMaxes[c], grid[r][c]);
                }

            int ans = 0;
            for (int r = 0; r < N; ++r)
                for (int c = 0; c < N; ++c)
                    ans += Math.min(rowMaxes[r], colMaxes[c]) - grid[r][c];

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
