package solution.leetcode.editor.cn;

public class LeetCode123 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //要学习下多种方法吧,学习优化的过程,比学习最优的解法更重要呢.
        public static int maxProfit(int[] prices) {
            int fstBuy = Integer.MIN_VALUE, fstSell = 0;
            int secBuy = Integer.MIN_VALUE, secSell = 0;
            for (int p : prices) {
                fstBuy = Math.max(fstBuy, -p);
                fstSell = Math.max(fstSell, fstBuy + p);
                secBuy = Math.max(secBuy, fstSell - p);
                secSell = Math.max(secSell, secBuy + p);
            }
            return secSell;
        }

        /**
         * 对于任意一天考虑四个变量:
         * fstBuy: 在该天第一次买入股票可获得的最大收益
         * fstSell: 在该天第一次卖出股票可获得的最大收益
         * secBuy: 在该天第二次买入股票可获得的最大收益
         * secSell: 在该天第二次卖出股票可获得的最大收益
         * 分别对四个变量进行相应的更新, 最后secSell就是最大
         * 收益值(secSell >= fstSell)
         */
        public static void main(String[] args) {
            int[] arr = {7, 1, 5, 3, 6, 4};
            System.out.println(maxProfit1(arr));
        }

        public static int maxProfit1(int[] prices) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int p : prices) {
                min = Math.min(min, p);
                max = Math.max(max, p - min);
            }
            return max == Integer.MIN_VALUE ? 0 : max;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
