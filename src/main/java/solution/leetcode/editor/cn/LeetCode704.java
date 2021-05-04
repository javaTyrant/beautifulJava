package solution.leetcode.editor.cn;

public class LeetCode704 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //二分查找
        public static int search(int[] nums, int target) {
            int i = 0;
            int j = nums.length;
            while (i < j) {
                int mid = (j - i) / 2 + i;
                if (nums[mid] > target) {
                    j = mid;
                } else if (nums[mid] < target) {
                    i = mid + 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5, 8};
            System.out.println(search(arr, 5));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
