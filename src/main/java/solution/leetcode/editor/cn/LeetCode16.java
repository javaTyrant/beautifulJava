package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode16 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            //维护一个最近的
            int closest = Integer.MAX_VALUE;
            int res = 0;
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    //min
                    int min = sum - target;
                    if (sum < target) {
                        j++;
                    } else if (sum > target) {
                        k--;
                    } else {
                        return sum;
                    }
                    //和三数之和不同的,更新min,更新res
                    if (Math.abs(min) < closest) {
                        closest = Math.abs(min);
                        res = sum;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
