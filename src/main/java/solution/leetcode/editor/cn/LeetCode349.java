package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode349 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //时间复杂度 O(mlogm+nlogn)
        public static int[] intersection(int[] nums1, int[] nums2) {
            //排序 + 双指针
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int length1 = nums1.length, length2 = nums2.length;
            int[] intersection = new int[length1 + length2];
            //index保存数组
            int index = 0, index1 = 0, index2 = 0;
            while (index1 < length1 && index2 < length2) {
                int num1 = nums1[index1], num2 = nums2[index2];
                if (num1 == num2) {
                    //去重,通过||让index-1不会越界
                    if (index == 0 || num1 != intersection[index - 1]) {
                        intersection[index++] = num1;
                    }
                    index1++;
                    index2++;
                } else if (num1 < num2) {
                    index1++;
                } else {
                    index2++;
                }
            }
            //截取空余
            return Arrays.copyOfRange(intersection, 0, index);
        }

        public static void main(String[] args) {
            int[] arr = {2, 2, 4};
            int[] arr1 = {2, 2, 5};
            System.out.println(Arrays.toString(intersection(arr, arr1)));
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
