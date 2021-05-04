package solution.leetcode.editor.cn;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/12/13
 */
public class Week {
    public int numberOfMatches(int n) {
        return n - 1;
    }

    //n = "435"
    public static int minPartitions(String n) {
        //先构造最多的1
        if (n.length() <= 0 || n.equals("0")) return 0;
        int len = n.length();
        int i = 0;
        int count = 0;
        StringBuilder greedy = new StringBuilder();
        while (i < len) {
            if (n.charAt(i) - '0' >= 1) {
                greedy.append(1);
            } else {
                greedy.append(0);
            }
            i++;
        }
        int min = findMin(n);
        if (min < n.charAt(0) - '0') {
            count += min;
        } else {
            min = n.charAt(0) - '0';
            count += n.charAt(0) - '0';
        }
        String sub = sub(n, greedy.toString(), min);
        return count + minPartitions(sub);
    }

    private static String sub(String s1, String s2, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s1.length(); i++) {
            int s = (s1.charAt(i) - '0') - (s2.charAt(i) - '0') * count;
            sb.append(s);
        }
        int i = 0;
        if (s1.length() <= 1) return "0";
        while (i <= sb.length() + 1) {
            if (sb.charAt(0) == '0') {
                sb.delete(0, 1);
            }
            i++;
        }
        return sb.toString();
    }

    private static int findMin(String n) {
        int min = Integer.MAX_VALUE;
        for (char c : n.toCharArray()) {
            if (c - '0' < min && c - '0' > 0) {
                min = c - '0';
            }
        }
        return min;
    }

    //石子游戏 VII
    public static int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[][] dp = new int[n][n];
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + stones[i];
        }
        return dfs(0, n - 1, pre, dp);
    }

    private static int dfs(int l, int r, int[] pre, int[][] dp) {
        if (l == r) {
            return 0;
        }
        if (dp[l][r] != 0) {
            return dp[l][r];
        }
        return dp[l][r] = Math.max(pre[r + 1] - pre[l + 1] - dfs(l + 1, r, pre, dp), pre[r] - pre[l] - dfs(l, r - 1, pre, dp));
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 2, 4};
        System.out.println(stoneGameVII(arr));
    }
}
