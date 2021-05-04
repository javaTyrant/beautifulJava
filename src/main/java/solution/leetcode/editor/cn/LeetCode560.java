package solution.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LeetCode560 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //560. 和为K的子数组 O(n^2)
        public int subarraySum(int[] nums, int k) {
            //先排序
            int count = 0;
            for (int start = 0; start < nums.length; ++start) {
                int sum = 0;
                for (int end = start; end < nums.length; end++) {
                    sum += nums[end];
                    if (sum == k) {
                        count++;
                    }
                }
            }
            return count;
        }

        //前缀和 + 哈希表优化 O(n)
        //还是没怎么get到 连续
        public int subarraySumOpt(int[] nums, int k) {
            int count = 0, pre = 0;
            Map<Integer, Integer> mp = new HashMap<>();
            mp.put(0, 1);
            for (int num : nums) {
                pre += num;
                //是否包含pre - k
                if (mp.containsKey(pre - k)) {
                    count += mp.get(pre - k);
                }
                mp.put(pre, mp.getOrDefault(pre, 0) + 1);
            }
            return count;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] nums = {2, 6, 3, -3, 2, 5, 8};
            System.out.println(solution.subarraySumOpt(nums, 8));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
