package dfs;

/**
 * @author lumac
 * @since 2020-05-10
 */
public class WordSearch {
    public static void main(String[] args) {

    }

    //79. 单词搜索
    public boolean exist(char[][] board, String word) {
        //两层for循环
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 只需要一个k要追踪匹配的次数
     */
    boolean search(char[][] board, String word, int i, int j, int k) {
        //base case
        if (k >= word.length()) return true;
        //i j不越界 如果不相等
        if (i < 0 || i >= board.length
                || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(k))
            return false;
        //boadr[i][j] == word.charAt(k),改变值,相当于已访问过
        board[i][j] += 256;
        //上下左右
        boolean result = search(board, word, i - 1, j, k + 1)
                || search(board, word, i + 1, j, k + 1)
                || search(board, word, i, j - 1, k + 1)
                || search(board, word, i, j + 1, k + 1);
        //恢复
        board[i][j] -= 256;
        return result;
    }
}
