package dayPlan;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2021/2/28
 */
public class Solution {
    static class Test {
        private Integer value;

        public Test(Integer value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value % 2 == 0 ? 1 : 0;
        }
    }

    public static void main(String[] args) {
        Map<Test, Integer> map = new HashMap<>(128);
        for (int i = 0; i < 63; i++) {
            map.put(new Test(i), i);
        }
    }

    int best = -(int) 1e9;
    int target;


    void dfs(int[] a, int i, int total) {
        if (i == a.length) {
            int sign = Math.abs(total - target) - Math.abs(best - target);
            if (sign < 0) {
                best = total;
            } else if (total < best && sign == 0) {
                best = total;
            }
            return;
        }
        for (int j = 0; j <= 2; j++) {
            dfs(a, i + 1, total + j * a[i]);
        }
    }

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        this.target = target;
        for (int x : baseCosts) {
            dfs(toppingCosts, 0, x);
        }
        return best;
    }
}
