package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode322 {
    static class Mysolution {
        private Integer res = Integer.MAX_VALUE;

        public int coinChange(int[] coins, int amount) {
            Arrays.sort(coins);
            findCoins(coins, amount, coins.length - 1, 0);
            return res == Integer.MAX_VALUE ? 0 : res;
        }

        //硬币集合,剩余的钱,索引,硬币的数量
        private void findCoins(int[] coins, int amount, int len, int count) {
            //base case
            if (amount <= 0) {
                res = Math.min(res, count);
                return;
            }
            if (len < 0) return;
            for (int i = amount / coins[len]; i >= 0 && i + count < res; i++) {
                //
                findCoins(coins, amount - i * coins[len], len - 1, count + i);
            }
        }


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //给定不同面额的硬币 coins 和一个总金额 amount。
        // 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
        //你可以认为每种硬币的数量是无限的。
        //输入：coins = [1, 2, 5], amount = 11
        //输出：3
        //解释：11 = 5 + 5 + 1

        int res = Integer.MAX_VALUE;

        //时间复杂度
        public int coinChange(int[] coins, int amount) {
            if (amount == 0) return 0;
            Arrays.sort(coins);
            mincoin(coins, amount, coins.length - 1, 0);
            return res == Integer.MAX_VALUE ? -1 : res;
        }

        private void mincoin(int[] coins, int amount, int index, int count) {
            //base case一定是amount==0
            if (amount == 0) {
                //res和count每次都要比较小的
                res = Math.min(res, count);
                return;
            }
            //index不能小于0j
            if (index < 0) return;
            //i>=0 && i + count < res 这个剪枝的效果很明显哦
            //那么i + count是什么意思呢,当前需要i枚硬币+之前匹配的count枚硬币,如果大于了,说明res是最优解
            //无需进入
            for (int i = amount / coins[index]; i >= 0 && i + count < res; i--) {
                //找更小的硬币,count累加i
                mincoin(coins, amount - (i * coins[index]), index - 1, count + i);
            }
        }

        //动态规划:核心问题,我们该这么样利用subproblems
        public int coinChangeDp(int[] coins, int amount) {
            int max = amount + 1;
            //fill最大值
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, max);
            //0要处理下
            dp[0] = 0;
            //终于搞懂为啥要双层遍历了,第一层:处理每个金额
            for (int i = 1; i <= amount; i++) {
                //第二层:选择用哪个硬币的最优解
                for (int coin : coins) {
                    //如果硬币的值小于当前的索引,才计算,不然值就大了
                    if (coin <= i) {
                        //取最小,+1是加的当前金额的数量
                        dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                    }
                }
            }
            //大于说明还是amount+1没有处理过
            return dp[amount] > amount ? -1 : dp[amount];
        }

        //零钱兑换2:amount = 5, coins = [1, 2, 5]
        //用dp[x] 表示金额之和等于x的硬币组合数，目标是求dp[amount]。
        public static int change(int amount, int[] coins) {
            //dp的含义是?
            int[] dp = new int[amount + 1];
            //等于0的有一种方案.
            dp[0] = 1;
            //先选硬币
            for (int coin : coins) {
                //再判断.
                for (int i = 0; i + coin <= amount; i++) {
                    //
                    dp[i + coin] += dp[i];
                }
            }
            return dp[amount];
        }

        public static void main(String[] args) {
            int[] arr = {470, 18, 66, 301, 403, 112, 360};
            int[] coins = {1, 2, 5};
            Solution solution = new Solution();
            solution.coinChange(arr, 8235);
            solution.coinChange(coins, 4);
            solution.coinChangeDp(coins, 11);
            System.out.println(solution.coinChange(coins, 11));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
