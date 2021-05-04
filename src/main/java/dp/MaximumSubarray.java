package dp;

/**
 * @author lumac
 * @since 2020-05-18
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(arr));
    }

    //53. 最大子序和
    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        int res = Integer.MIN_VALUE;
        int maxsum = 0;
        for (int num : nums) {
            //max和0比,如果之前的小于0可以舍弃
            maxsum = Math.max(0, maxsum) + num;
            //
            res = Math.max(res, maxsum);
        }
        return res;
    }

    //分治法
    public static int maxSubArray2(int[] nums) {
        return maxSubArrayHelper(nums, 0, nums.length - 1);

    }

    static int maxSubArrayHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) >>> 1;
        int leftNum = maxSubArrayHelper(nums, left, mid);
        int rightNum = maxSubArrayHelper(nums, mid + 1, right);
        int midNum = findMaxCrossingSubarray(nums, left, mid, right);
        int res = Math.max(leftNum, rightNum);
        return Math.max(res, midNum);

    }

    static int findMaxCrossingSubarray(int[] nums, int left, int mid, int right) {
        int leftNum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftNum = Math.max(sum, leftNum);
        }
        int rightNum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightNum = Math.max(sum, rightNum);
        }
        return (leftNum + rightNum);
    }
}
