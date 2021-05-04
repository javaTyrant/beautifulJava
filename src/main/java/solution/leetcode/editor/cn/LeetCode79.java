package solution.leetcode.editor.cn;

public class LeetCode79 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {

        }

        public boolean exist(char[][] board, String word) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (search(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        //k统计匹配好的长度
        boolean search(char[][] board, String word, int i, int j, int k) {
            //base case
            if (k >= word.length()) return true;
            //四个边界判断 字符不相等 返回false
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                    || board[i][j] != word.charAt(k))
                return false;
            //修改
            board[i][j] += 256;
            //上下左右四次递归
            boolean result = search(board, word, i - 1, j, k + 1)
                    || search(board, word, i + 1, j, k + 1)
                    || search(board, word, i, j - 1, k + 1)
                    || search(board, word, i, j + 1, k + 1);
            //回溯
            board[i][j] -= 256;
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
