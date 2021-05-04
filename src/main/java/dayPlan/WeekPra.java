package dayPlan;

import java.util.Arrays;
import java.util.List;

/**
 * @author lumac
 * @since 2021/2/28
 */
public class WeekPra {
    private int closest = Integer.MAX_VALUE;

    public int closestCost2(int[] baseCosts, int[] toppingCosts, int target) {
        for (int base : baseCosts) {
            coinChange(toppingCosts, target - base);
        }
        return closest + baseCosts[0];
    }

    public int coinChange(int[] coins, int amount) {
        //先排序
        Arrays.sort(coins);
        //
        dfs(coins, coins.length - 1, amount);
        return closest;
    }

    /**
     * @param coins 硬币集合
     * @param ci    当前索引
     * @param rest  剩余的钱
     */
    private void dfs(int[] coins, int ci, int rest) {
        //终止条件
        if (ci < 0) return;
        for (int i = 2; i >= 0; i--) {
            //剩余的钱,i=0时需要0个,继续往下找
            int currRest = rest - i * coins[ci];
            //
            if (currRest > 0 && Math.abs(rest) < closest) {
                //ci要向前移动
                dfs(coins, ci - 1, currRest);
                closest = rest;
            }
        }
    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int res = 0;
        for (List<String> item : items) {
            if (ruleKey.equals("color")) {
                if (item.get(1).equals(ruleValue)) {
                    res++;
                }
            } else if (ruleKey.equals("type")) {
                if (item.get(0).equals(ruleValue)) {
                    res++;
                }
            } else {
                if (item.get(2).equals(ruleValue)) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        WeekPra pra = new WeekPra();
        int[] a = {3, 10};
        int[] b = {2, 5};
        int target = 9;
        System.out.println(pra.closestCost(a, b, target));
        System.out.println(pra.closestCost2(a, b, target));
    }


    //每种类型的配料 最多两份
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        Arrays.sort(baseCosts);
        Arrays.sort(toppingCosts);
        int[] dp = new int[baseCosts.length];
        int closet = Integer.MAX_VALUE;
        int max = 2;
        for (int i = 0; i < baseCosts.length; i++) {
            dp[i] = baseCosts[i];
            for (int j = 0; j < toppingCosts.length; j++) {
                for (int m = 0; m <= max; m++) {
                    int c = Math.abs(target - (m * toppingCosts[j] + baseCosts[i]));
                    if (c < closet) {
                        closet = c;
                        dp[i] = toppingCosts[i] + m * toppingCosts[j];
                    }
                }
            }
        }
        int cl = Integer.MAX_VALUE;
        int res = 0;
        for (int d : dp) {
            if (Math.abs(d - target) <= cl) {
                cl = Math.abs(d - target);
                res = d;
            }
        }
        return res;
    }

    public int minOperations(int[] nums1, int[] nums2) {
        return 0;
    }
}
