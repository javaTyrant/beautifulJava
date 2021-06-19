import java.util.Scanner;

/**
 * @author lumac
 * @since 2021/6/12
 */
public class Pick {
    static int N = 110;
    static int T;
    static int n, m;
    //
    static int[][] w = new int[N][N];
    //
    static int[][] f = new int[N][N];

    //最多能够摘到多少颗花生
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //
        T = sc.nextInt();
        while (T-- > 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            //
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) w[i][j] = sc.nextInt();
            }
            //
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    //
                    f[i][j] = Math.max(f[i - 1][j] + w[i][j], f[i][j - 1] + w[i][j]);
                }
            }

            System.out.println(f[n][m]);

        }
    }

    public int deleteAndEarn(int[] nums) {
        int[] trans = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            trans[nums[i]] += nums[i];
        }

        int[] dp = new int[10001];

        dp[0] = 0;
        dp[1] = trans[1];
        for (int i = 2; i < trans.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + trans[i]);
        }

        return dp[dp.length - 1];
    }
}
