package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode88 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p = m + n - 1;
            int p1 = m - 1;
            int p2 = n - 1;
            while (p1 >= 0 && p2 >= 0) {
                nums1[p--] = (nums2[p2] > nums1[p1]) ? nums2[p2--] : nums1[p1--];
            }
            while (p2 >= 0) {
                nums1[p--] = nums2[p2--];
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {0};
        int[] arr1 = {1};
        s.merge(arr, 0, arr1, 1);
        System.out.println(Arrays.toString(arr));
    }
}