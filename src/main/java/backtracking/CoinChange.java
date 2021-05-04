package backtracking;

import java.util.Arrays;

/**
 * 零钱兑换
 *
 * @author lumac
 * @since 2020-05-25
 */
public class CoinChange {
    private int min = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        //先排序
        Arrays.sort(coins);
        //
        dfs(coins, coins.length - 1, amount, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * @param coins 硬币集合
     * @param ci    当前索引
     * @param rest  剩余的钱
     * @param cnt   总数
     */
    private void dfs(int[] coins, int ci, int rest, int cnt) {
        //终止条件
        if (ci < 0) return;
        for (int i = rest / coins[ci]; i >= 0; i--) {
            //剩余的钱,i=0时需要0个,继续往下找
            int currRest = rest - i * coins[ci];
            //当前的硬币数量
            int currCnt = cnt + i;
            //
            if (currRest > 0 && currCnt + 1 < min) {
                //ci要向前移动
                dfs(coins, ci - 1, currRest, currCnt);
            } else {
                //currRest <= 0
                if (currRest == 0 && currCnt < min) {
                    min = currCnt;
                }
                break;
            }
        }
    }

    public int coinChange_(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    public static int coinChangeDp(int[] coins, int amount) {
        int max = amount + 1;
        //从底至上的dp
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    //这里出现了i - coin,所以必须确保 i - coin >= 0
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange change = new CoinChange();
        int[] coins = {1, 2, 5};
//        System.out.println(change.coinChange(coins, 8));
        System.out.println(coinChangeDp(coins, 5));
    }

    //合并两个有序数组
    //nums1 = [1,2,3,0,0,0], m = 3
    //nums2 = [2,5,6],       n = 3
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while ((p1 >= 0) && (p2 >= 0)) {
            nums1[p--] = (nums1[p1] < nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    //保存最小值
    int res = Integer.MAX_VALUE;

    public int coinChangeAn(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        minCoin(coins, amount, coins.length - 1, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void minCoin(int[] coins, int amount, int index, int count) {
        if (amount == 0) {
            res = Math.min(res, count);
            return;
        }
        if (index < 0) {
            return;
        }
        //
        for (int i = amount / coins[index]; i >= 0 && i + count < res; i--) {
            minCoin(coins, amount - (i * coins[index]), index - 1, count + i);
        }
    }
}
