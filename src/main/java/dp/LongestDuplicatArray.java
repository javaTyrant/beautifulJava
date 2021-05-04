package dp;

/**
 * @author lumac
 * @since 2020-05-15
 */
public class LongestDuplicatArray {
    public static int findLength(int[] A, int[] B) {
        int[] dp = new int[B.length + 1];
        int res = 0;
        for (int i = 0; i < A.length; ++i) {
            for (int j = B.length - 1; j >= 0; --j) {
                if (A[i] == B[j]) {
                    dp[j + 1] = dp[j] + 1;
                    if (res < dp[j + 1]) {
                        res = dp[j + 1];
                    }
                } else {
                    dp[j + 1] = 0;
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {

    }
}
