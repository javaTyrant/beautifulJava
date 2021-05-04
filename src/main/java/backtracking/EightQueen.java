package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题
 *
 * @author lumac
 * @since 2020/6/1
 */
public class EightQueen {
    private final List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] grid = new char[n][n];
        //初始化棋盘
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '.';
            }
        }
        boolean[] col = new boolean[n];
        boolean[] dg = new boolean[n + n];
        boolean[] udg = new boolean[n + n];
        dfs(0, n, grid, col, dg, udg);

        return res;
    }

    /**
     * @param h    行
     * @param n    皇后的数量,n行,n列
     * @param grid 棋盘
     * @param col
     * @param dg
     * @param udg
     */
    private void dfs(int h, int n, char[][] grid, boolean[] col, boolean[] dg, boolean[] udg) {
        //满足条件了
        if (h == n) {
            List<String> list = new ArrayList<>();
            for (char[] chars : grid) {
                list.add(new String(chars));
            }
            res.add(list);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!col[j] && !dg[n - h + j] && !udg[h + j]) {
                grid[h][j] = 'Q';
                col[j] = dg[n - h + j] = udg[h + j] = true;
                dfs(h + 1, n, grid, col, dg, udg);
                grid[h][j] = '.';
                col[j] = dg[n - h + j] = udg[h + j] = false;
            }
        }
    }

    public static void main(String[] args) {
        EightQueen queen = new EightQueen();
        System.out.println(queen.solveNQueens(4));
    }
}
