package solution.leetcode.editor.cn;

public class LeetCode547 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public void dfs(int[][] M, int[] visited, int i) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && visited[j] == 0) {
                    visited[j] = 1;
                    dfs(M, visited, j);
                }
            }
        }

        public int findCircleNum(int[][] M) {
            int[] visited = new int[M.length];
            int count = 0;
            for (int i = 0; i < M.length; i++) {
                if (visited[i] == 0) {
                    dfs(M, visited, i);
                    count++;
                }
            }
            return count;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[][] arrs = {
                    {1, 1, 0},
                    {1, 1, 0},
                    {0, 0, 1}};
            solution.findCircleNum(arrs);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
