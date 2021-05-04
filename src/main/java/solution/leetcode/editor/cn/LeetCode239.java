package solution.leetcode.editor.cn;

public class LeetCode239 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int numLen = nums.length;
            if (numLen == 0) return new int[0];
            int[] ans = new int[numLen - k + 1]; // 保存结果
            int left = 0; // 左指针
            int right = k - 1; // 右指针
            int max = -1; // 最大值指针

            while (right < numLen) {
                if (max < left) { // 更新最大值
                    max = left;
                    for (int i = left; i <= right; i++) {
                        max = nums[max] < nums[i] ? i : max;
                    }
                } else
                    max = nums[max] < nums[right] ? right : max; // 更新最大值
                ans[left] = nums[max];
                left++;
                right++;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
