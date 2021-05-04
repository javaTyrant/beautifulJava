package solution.leetcode.editor.cn;

public class LeetCode162 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static int findPeakElement(int[] nums) {
            int low = 0;
            int high = nums.length - 1;
            //当两者相等时，
            while (low < high) {
                int mid = (low + high) / 2;
                if (nums[mid] < nums[mid + 1])
                    //注意为什么要+1，因为mid位置一定不是峰，而low=mid+1有可能是峰
                    low = mid + 1;
                else {
                    //注意这里为什么不加+，因为high=mid有可能是峰
                    high = mid;
                }
            }
            return low;
        }

        public static void main(String[] args) {
            int[] arr = {5, 3, 2, 1, 6, 4};
            System.out.println(findPeakElement(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
