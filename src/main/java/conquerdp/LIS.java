package conquerdp;

/**
 * @author lufengxiang
 * @since 2021/5/25
 **/
public class LIS {
    //具体地，若长度为n 的数组nums的所有元素二进制的第 i 位共有c个1，
    //n-c个0，则些元素在二进制的第 i 位上的汉明距离之和为
    //c⋅(n−c)
    public int totalHammingDistance(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < 30; ++i) {
            int c = 0;
            //判断每个数字第i位的一的个数.
            for (int val : nums) {
                //一共有多少个1
                c += (val >> i) & 1;
            }
            //
            ans += c * (n - c);
        }
        return ans;
    }

    //300. 最长递增子序列 [10,9,2,5,3,7,101,18]
    public static void main(String[] args) {
        LIS solution = new LIS();
        int[] arr0 = {4, 14, 2};
        System.out.println(solution.totalHammingDistance(arr0));
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(solution.lengthOfLIS2(arr));
    }

    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            //i位至少有一.
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //每一轮要给最大的赋值.为什么不能用dp保存最大值呢
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    public int lengthOfLIS(int[] nums) {
        //以i结尾的最长上升子串的数量.
        int length = nums.length;
        if (length < 2) return 1;
        int[] dp = new int[length];
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return dp[length - 1];
    }

    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        int max = 0, result = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        count[i] = count[j];
                        dp[i] = dp[j] + 1;
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
            count[i] = Math.max(count[i], 1);
            if (dp[i] > max) {
                result = count[i];
                max = dp[i];
            } else if (dp[i] == max) {
                result += count[i];
            }
        }
        return result;
    }
}
