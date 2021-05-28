package conquerdp;

/**
 * @author lufengxiang
 * @since 2021/5/28
 **/
public class CutRob {
    public static void main(String[] args) {
        CutRob solution = new CutRob();
        System.out.println(solution.integerBreak(25));
    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i - 1; j++) {
                //
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        return dp[n];
    }
}
