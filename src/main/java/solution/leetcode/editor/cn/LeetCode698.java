package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode698 {
    static class My {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            if (nums == null || nums.length == 0) return false;
            int sum = Arrays.stream(nums).sum();
            if (sum % k > 0) return false;
            int target = sum / k;
            int len = nums.length - 1;
            //排序勿忘
            Arrays.sort(nums);
            if (nums[len] > target) return false;
            //>=0
            while (len >= 0 && nums[len] == target) {
                k--;
                len--;
            }
            return findSubset(nums, len, target, new int[k]);
        }

        private boolean findSubset(int[] nums, int k, int target, int[] group) {
            //base case
            if (k < 0) return true;
            int v = nums[k--];
            for (int i = 0; i < group.length; i++) {
                //<=
                if (group[i] + v <= target) {
                    group[i] += v;
                    if (findSubset(nums, k, target, group)) return true;
                    group[i] -= v;
                }
                if (group[i] == 0) break;
            }
            return false;
        }

        public static void main(String[] args) {
            My me = new My();
            int[] arr = {4,3,2,3,5,2,1};
            System.out.println(me.canPartitionKSubsets(arr, 4));
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            //[4, 3, 2, 3, 5, 2, 1], k = 4
            Solution solution = new Solution();
            int[] arr = {4, 3, 2, 3, 5, 2, 1};
            //[1,2,2,3,3,4,5]
            System.out.println(solution.canPartitionKSubsetsBt(arr, 4));
//            solution.canPartitionKSubsetsBt(arr, 4);
        }

        //回溯法,groups的作用
        //row控制的数字的取用
        public boolean search(int[] groups, int row, int[] nums, int target) {
            System.out.println(row);
            //base case row < 0 说明每个子集都有数字
            if (row < 0) return true;
            //row往前减一
            int v = nums[row--];
            //循环k次,每个槽都要等于,i决定往哪个位置放
            for (int i = 0; i < groups.length; i++) {
                if (groups[i] + v <= target) {
                    //
                    groups[i] += v;
                    if (search(groups, row, nums, target)) {
                        return true;
                    }
                    //回溯
                    groups[i] -= v;
                }
                //如果groups[i] == 0说明没有填满,已经用光了所有的数字
                if (groups[i] == 0) break;
            }
            return false;
        }

        public boolean canPartitionKSubsetsBt(int[] nums, int k) {
            //求和
            int sum = Arrays.stream(nums).sum();
            //如果不能整除k,直接返回
            if (sum % k > 0) return false;
            //每个子集的目标和为target
            int target = sum / k;
            //排序
            Arrays.sort(nums);
            //
            int row = nums.length - 1;
            if (nums[row] > target) return false;
            //当row>=0 且最后一个数等于target,说明有满足的,再找k-1的集合了
            while (row >= 0 && nums[row] == target) {
                row--;
                k--;
            }
            //此时k那个位置的值已经小于target了,找到减少过的k的
            //new int[k],保存k个子集
            return search(new int[k], row, nums, target);
        }

        //动态规划
        enum Result {TRUE, FALSE}

        public boolean canPartitionKSubsets(int[] nums, int k) {
            int sum = Arrays.stream(nums).sum();
            if (sum % k > 0) return false;

            Result[] memo = new Result[nums.length * 2];
            memo[(2 * nums.length) - 1] = Result.TRUE;
            return search(0, sum, memo, nums, sum / k);
        }

        boolean search(int used, int todo, Result[] memo, int[] nums, int target) {
            if (memo[used] == null) {
                memo[used] = Result.FALSE;
                int targ = (todo - 1) % target + 1;
                for (int i = 0; i < nums.length; i++) {
                    if ((((used >> i) & 1) == 0) && nums[i] <= targ) {
                        if (search(used | (1 << i), todo - nums[i], memo, nums, target)) {
                            memo[used] = Result.TRUE;
                            break;
                        }
                    }
                }
            }
            return memo[used] == Result.TRUE;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
