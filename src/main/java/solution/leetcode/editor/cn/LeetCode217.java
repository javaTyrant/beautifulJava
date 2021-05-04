package solution.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LeetCode217 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //每个人都会
        public static boolean containsDuplicate(int[] nums) {
            if (nums == null || nums.length == 0) return false;
            Map<Integer, Integer> cache = new HashMap<>();
            for (int n : nums) {
                if (cache.getOrDefault(n, 0) > 0) return true;
                cache.put(n, 1);
            }
            return false;
        }

        public static void main(String[] args) {
            int[] arr = {1, 2, 3};
            System.out.println(containsDuplicate(arr));
        }

        public boolean containsDuplicateBest(int[] nums) {
            if (nums.length < 2) {
                return false;
            }

            for (int i = 1; i < nums.length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] > nums[j] && j + 1 != i) {
                        int temp = nums[i];
                        nums[i] = nums[j + 1];
                        nums[j + 1] = temp;
                        break;
                    }
                    if (nums[i] == nums[j]) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
