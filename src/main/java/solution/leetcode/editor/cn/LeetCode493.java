package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode493 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {1, 3, 2, 3, 1};
            System.out.println(solution.reversePairs(arr));
        }

        /**
         * 给定一个数组nums，如果i < j且nums[i] > 2*nums[j]我们就将(i, j)称作一个重要翻转对。
         * 你需要返回给定数组中的重要翻转对的数量。
         * 输入: [1,3,2,3,1]
         * 输出: 2
         */
        public int reversePairs(int[] nums) {
            if (nums == null || nums.length < 2) return 0;
            int n = nums.length;
            return mergeSort(nums, Arrays.copyOf(nums, n), 0, n - 1);
        }

        private int mergeSort(int[] nums, int[] tmps, int lo, int hi) {
            if (lo >= hi) return 0;
            int mid = (lo + hi) >> 1;
            int lc = mergeSort(tmps, nums, lo, mid);
            int rc = mergeSort(tmps, nums, mid + 1, hi);
            int i = lo, j = mid + 1, cnt = 0;
            while (i <= mid && j <= hi) {
                if (tmps[i] > 2.0 * tmps[j]) {
                    cnt += mid - i + 1;
                    ++j;
                } else ++i;
            }
            merge(nums, tmps, lo, hi);
            return lc + rc + cnt;
        }

        private void merge(int[] nums, int[] tmps, int lo, int hi) {
            if (lo >= hi) return;
            int mid = (lo + hi) >> 1;
            int i = lo, j = mid + 1, pos = lo;
            while (i <= mid && j <= hi)
                nums[pos++] = tmps[i] < tmps[j] ? tmps[i++] : tmps[j++];
            while (i <= mid) nums[pos++] = tmps[i++];
            while (j <= hi) nums[pos++] = tmps[j++];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
