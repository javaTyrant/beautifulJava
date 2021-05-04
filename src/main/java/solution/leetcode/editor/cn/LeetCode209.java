package solution.leetcode.editor.cn;

public class LeetCode209 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //牛逼的双指针
        public int minSubArrayLen(int s, int[] nums) {
            if (null == nums || nums.length == 0) {
                return 0;
            }
            //i j
            int i = 0, j = 0;
            int sum = 0, minLen = Integer.MAX_VALUE;
            while (j < nums.length) {
                sum += nums[j++];
                if (sum < s) {
                    continue;
                }
                while (sum >= s) {
                    sum -= nums[i];
                    i++;
                }
                minLen = Math.min(minLen, j - i + 1);
            }
            return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] nums = {2, 3, 1, 2, 4, 3};
            System.out.println(solution.minSubArrayLen(7, nums));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
