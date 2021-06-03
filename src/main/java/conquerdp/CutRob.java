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
        //dp[i] 表示将正整数i拆分成至少两个正整数的和之后，这些正整数的最大乘积。
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
