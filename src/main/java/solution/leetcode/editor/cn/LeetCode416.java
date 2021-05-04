package solution.leetcode.editor.cn;

//背包问题
public class LeetCode416 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {1, 5, 11, 5};
            System.out.println(solution.canPartition(arr));
        }

        //给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
        //[1, 5, 11, 5] ->[1, 5, 5] 和 [11].
        public boolean canPartition(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return false;
            }
            int sum = 0, maxNum = 0;
            for (int num : nums) {
                sum += num;
                maxNum = Math.max(maxNum, num);
            }
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            if (maxNum > target) {
                return false;
            }
            //如果总数是22,那么我们只要找到这个数组里是否存在一个11,如果存在一个11,那么肯定就满足的
            //那么如何找到和是11的子数组呢,那么动态规划
            //其中dp[i][j] 表示从数组的 [0,i]下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取方案使得被选取的正整数的和等于 j。
            boolean[][] dp = new boolean[n][target + 1];
            //初始化
            for (int i = 0; i < n; i++) {
                dp[i][0] = true;
            }
            //dp[0][1]
            dp[0][nums[0]] = true;
            for (int i = 1; i < n; i++) {
                int num = nums[i];
                for (int j = 1; j <= target; j++) {
                    //如果可选可不选
                    if (j >= num) {
                        dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                    } else {
                        //无法选取当前的数字
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[n - 1][target];
        }

        //opt
        public boolean canPartitionOpt(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return false;
            }
            int sum = 0, maxNum = 0;
            for (int num : nums) {
                sum += num;
                maxNum = Math.max(maxNum, num);
            }
            if (sum % 2 != 0) {
                return false;
            }
            int target = sum / 2;
            if (maxNum > target) {
                return false;
            }
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;
            for (int i = 0; i < n; i++) {
                int num = nums[i];
                for (int j = target; j >= num; --j) {
                    dp[j] |= dp[j - num];
                }
            }
            return dp[target];
        }

        //dfs
        public boolean canPartitionDfs(int[] nums) {
            int n = nums.length;
            if (n == 0 || n == 1) return false;
            int sum = 0;
            for (int num : nums) sum += num;
            if (sum % 2 != 0) return false;

            return dfs(nums, sum / 2, 0);
        }

        public boolean dfs(int[] nums, int target, int begin) {
            if (target == 0) return true;
            if (target < 0) return false;
            for (int i = begin; i < nums.length; i++) {
                if (i > begin && nums[i] == nums[i - 1]) continue;
                if (dfs(nums, target - nums[i], i + 1)) return true;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
