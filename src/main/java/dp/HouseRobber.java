package dp;

/**
 * @author lumac
 * @since 2020/6/24
 */
public class HouseRobber {
    //打家劫舍,特殊情况太多了,没有统一处理,而且,还有数组也是没有必要的
    public static int rob_raw(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    public static int rob(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            int c = Math.max(b, a + num);
            a = b;
            b = c;
        }
        return b;
    }

    //买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        //需要几个变量,两个,一个记录最小的买入值,一个记录差价
        int res = 0;
        int buy = Integer.MAX_VALUE;
        for (int price : prices) {
            //buy取最低
            buy = Math.min(buy, price);
            //差价取最大
            res = Math.max(res, price - buy);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
