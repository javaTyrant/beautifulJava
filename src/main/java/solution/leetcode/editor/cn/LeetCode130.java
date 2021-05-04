package solution.leetcode.editor.cn;

public class LeetCode130 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        static char[][] board;

        public void solve(char[][] board) {
            this.board = board;
            if (board == null || board.length == 0) return;//首先判断是否为null
            //先把边界的'O'找到,然后递归与边界'O'相邻的点
            int row = board.length - 1, col = board[0].length - 1;
            for (int i = 0; i <= row; i++) {
                if (board[i][0] == 'O') dfs(i, 0);//查找第一列
                if (board[i][col] == 'O') dfs(i, col);//查找最后一列
            }
            for (int j = 0; j <= col; j++) {
                if (board[0][j] == 'O') dfs(0, j);//查找第一行
                if (board[row][j] == 'O') dfs(row, j);//查找最后一行
            }//剩下的'O'都是被'X'包围的，只要遇到'O'转变成‘X‘，遇到'A'，变回原来的'O'
            for (int i = 0; i <= row; i++) {
                for (int j = 0; j <= col; j++) {
                    if (board[i][j] == 'O') board[i][j] = 'X';
                    else if (board[i][j] == 'A') board[i][j] = 'O';
                }
            }
        }//dfs查找与边界'O'相邻的'O‘, 判断是否等于A，说明之前已经替换过了，不用再考虑了

        private void dfs(int row, int col) {
            if (row < 0 || col < 0 || row >= board.length || col >= board[0].length ||
                    board[row][col] == 'X' || board[row][col] == 'A') return;
            board[row][col] = 'A';
            dfs(row - 1, col);
            dfs(row + 1, col);
            dfs(row, col + 1);
            dfs(row, col - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
