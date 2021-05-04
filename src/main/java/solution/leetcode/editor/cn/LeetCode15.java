package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode15 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            if (nums == null || nums.length < 2) return list;
            Arrays.sort(nums);
            //不能大于等于0哦,000是满足条件的
            if (nums[0] > 0) return list;
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i - 1] == nums[i]) continue;
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum > 0) {
                        k--;
                    } else if (sum < 0) {
                        j++;
                    } else {
                        list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        while (j < k && nums[k - 1] == nums[k]) k--;
                        while (j < k && nums[j] == nums[j + 1]) j++;
                        j++;
                        k--;
                    }
                }
            }
            return list;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {-2, 1, 1, 2, 0};
            int[] arr1 = {0, 0, 0};
            System.out.println(solution.threeSum(arr1));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
