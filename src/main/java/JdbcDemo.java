/**
 * @author lufengxiang
 * @since 2021/6/8
 **/
public class JdbcDemo {
    public static void main(String[] args) {

    }

    static final int Mod = 1000_000_007;

    public int profitableSchemes(int G, int P, int[] groups, int[] profits) {
        int n = groups.length;
        int[][] counts = new int[G + 1][P + 1];
        for (int i = 0; i <= G; i++) {
            counts[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            int group = groups[i], profit = profits[i];
            for (int j = G; j >= group; j--) {
                long count = counts[j][P];
                for (int k = Math.max(0, P - profit); k <= P; k++) {
                    count += counts[j - group][k];
                }
                counts[j][P] = (int) (count % Mod);
                for (int k = P - 1; k >= profit; k--) {
                    counts[j][k] = (counts[j][k] + counts[j - group][k - profit]) % Mod;
                }
            }
        }
        return counts[G][P];
    }
}
