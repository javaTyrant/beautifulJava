package dp;

/**
 * @author lumac
 * @since 2020/6/2
 */
public class RegularExpressionMatching {
    private boolean ans;
    private boolean[][] memo;
    private int m, n;

    private void backTracking(int sId, int pId, String s, String p) {
        if (ans)
            return;
        if (sId >= m) {
            while (pId + 1 < n && p.charAt(pId + 1) == '*')
                pId += 2;
            if (pId >= n) {
                ans = true;
            }
            return;
        }
        if (pId >= n) {
            return;
        }
        if (memo[sId][pId])
            return;
        memo[sId][pId] = true;
        if (pId + 1 < n && p.charAt(pId + 1) == '*') {
            if (s.charAt(sId) == p.charAt(pId) || p.charAt(pId) == '.')
                backTracking(sId + 1, pId, s, p);
            backTracking(sId, pId + 2, s, p);
        } else if (s.charAt(sId) == p.charAt(pId) || p.charAt(pId) == '.')
            backTracking(sId + 1, pId + 1, s, p);
    }

    public static boolean isMatchDp(String s, String p) {
        if (s == null || p == null)
            return false;
        int m = s.length(), n = p.length();
        boolean[][] res = new boolean[m + 1][n + 1];
        res[0][0] = true;
        //s.charAt(i)与p.charAt(j)的结果是存储在res[i+1][j+1]中
        //所以res[0][i]其实是存储p中与0个字符(s.charAt(-1)不存在)的匹配结果
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*' && res[0][i - 1])
                res[0][i + 1] = true;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (p.charAt(j) == '.')
                    res[i + 1][j + 1] = res[i][j];
                if (p.charAt(j) == s.charAt(i))
                    res[i + 1][j + 1] = res[i][j];
                if (p.charAt(j) == '*') {
                    if (s.charAt(i) != p.charAt(j - 1) && p.charAt(j - 1) != '.')
                        res[i + 1][j + 1] = res[i + 1][j - 1];
                    else {
                        //res[i + 1][j - 1] 表示*一个都不匹配;
                        //res[i + 1][j]表示匹配一个
                        //res[i][j + 1]表示匹配多个
                        res[i + 1][j + 1] = res[i + 1][j - 1] || res[i + 1][j] || res[i][j + 1];
                    }
                }
            }
        }
        return res[m][n];
    }

    public boolean isMatch(String s, String p) {
        m = s.length();
        n = p.length();
        memo = new boolean[m][n];
        backTracking(0, 0, s, p);
        return ans;
    }
}
