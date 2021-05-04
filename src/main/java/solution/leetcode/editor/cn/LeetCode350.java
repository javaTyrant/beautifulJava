package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode350 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int len1 = nums1.length;
            int len2 = nums2.length;
            int sec = Math.min(len1, len2);
            int i = 0, j = 0, p = 0;
            int[] res = new int[sec];
            while (i < len1 && j < len2) {
                if (nums1[i] < nums2[j]) {
                    i++;
                } else if (nums1[i] > nums2[j]) {
                    j++;
                } else {
                    res[p++] = nums1[i];
                    i++;
                    j++;
                }
            }
            return Arrays.copyOfRange(res, 0, i);
        }

        public static void main(String[] args) {
            int[] arr = {1, 2, 2, 3, 4};
            int[] arr1 = {2, 3, 2};
            System.out.println(Arrays.toString(intersect(arr1, arr)));
            System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, 2)));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
