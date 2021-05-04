package solution.leetcode.editor.cn;

public class LeetCode746 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        /**
         * 使用最小花费爬楼梯
         * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
         * 输出: 6
         * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
         */
        public int minCostClimbingStairs(int[] cost) {
            int len = cost.length;
            if (len == 0)
                return 0;
            if (len == 1)
                return cost[0];
            if (len == 2)
                return Math.min(cost[0], cost[1]);
            for (int i = 2; i < len; i++) {
                //选二者的最小
                cost[i] = Math.min(cost[i - 1] + cost[i], cost[i] + cost[i - 2]);
            }
            //
            return Math.min(cost[len - 1], cost[len - 2]);
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
            System.out.println(solution.minCostClimbingStairs(cost));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
