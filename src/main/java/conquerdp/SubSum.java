package conquerdp;

/**
 * @author lufengxiang
 * @since 2021/5/13
 **/
public class SubSum {
    //剑指 Offer 42. 连续子数组的最大和
    //输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
    //要求时间复杂度为O(n)。
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //如果前一个数小于0,那么肯定是不选的.
            nums[i] += Math.max(nums[i - 1], 0);
            //res选最大的/
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        SubSum solution = new SubSum();
        System.out.println(solution.maxSubArray(nums));
    }
}
