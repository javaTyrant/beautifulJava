import java.util.ArrayList;
import java.util.List;

/**
 * @author lumac
 * @since 2020/6/8
 */
public class TeemoAttacking {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length;
        if (n == 0) return 0;
        int total = 0;
        for (int i = 0; i < n - 1; ++i)
            total += Math.min(timeSeries[i + 1] - timeSeries[i], duration);
        return total + duration;
    }

    public static void main(String[] args) {
        TeemoAttacking attacking = new TeemoAttacking();
        int[] arr = {1, 2, 5, 6};
        System.out.println(attacking.findPoisonedDuration(arr, 2));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(1, 0, k, n, res, cur);
        return res;
    }

    private void dfs(int i, int sum, int n, int k, List<List<Integer>> res, List<Integer> cur) {
        //base case
        if (k == 0 && sum == n) {
            res.add(new ArrayList<>(cur));
        }
        if (sum > n) return;
        for (int j = i; j <= (Math.min(9, n)); j++) {
            cur.add(j);
            dfs(j + 1, sum + j, n, k - 1, res, cur);
            cur.remove(cur.size() - 1);
        }
    }
}
