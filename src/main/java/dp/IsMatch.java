package dp;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lumac
 * @since 2020/7/28
 */
public class IsMatch {
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                //如果p的前一个字符是*
                if (p.charAt(j - 1) == '*') {
                    //f[i][j - 2]
                    f[i][j] = f[i][j - 2];
                    //如果前面的字符串相等
                    if (matches(s, p, i, j - 1)) {
                        //f[i][j] 或者 f[i - 1][j]
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                    //如果p的前一个字符不是是*
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    //s的第i-1个值和p的第j-1个值是否匹配
    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {


        System.out.println(isMatch("abbb", "ab*"));
        System.out.println(isMatch("abc", "a*c"));
        System.out.println(isMatch("aaab", "a*b"));
        System.out.println(isMatch("aaannc", ".*"));
    }
}
