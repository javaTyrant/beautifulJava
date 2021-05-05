package conquerdp;

/**
 * @author lumac
 * @since 2021/5/5
 */
public class RobHouse {
    //你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
    //影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    public int rob(int[] nums) {
        //如果数组长度为0返回0
        if (nums.length == 0) return 0;
        //小于等于1返回第一个元素
        if (nums.length <= 1) return nums[0];
        //dp数组
        int[] dp = new int[nums.length];
        //
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            //选最大的.
            dp[i] = Math.max((nums[i] + dp[i - 2]), dp[i - 1]);
        }
        //返回dp
        return dp[nums.length - 1];
    }
    //这题该如何思考呢?

}
