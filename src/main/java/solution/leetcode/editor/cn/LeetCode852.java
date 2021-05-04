package solution.leetcode.editor.cn;

public class LeetCode852 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int peakIndexInMountainArray(int[] arr) {
            int low = 0;
            int high = arr.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (arr[mid + 1] > arr[mid]) {
                    low = mid + 1;
                } else if (arr[mid - 1] > arr[mid]) {
                    high = mid - 1;
                } else {
                    return mid;
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
