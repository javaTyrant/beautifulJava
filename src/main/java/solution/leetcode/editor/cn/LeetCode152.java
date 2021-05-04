package solution.leetcode.editor.cn;

public class LeetCode152 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //乘积最大子数组
        public int maxProduct(int[] nums) {
            //一个保存最大的，一个保存最小的。
            int res = Integer.MIN_VALUE, imax = 1, imin = 1;
            for (int i = 0; i < nums.length; i++) {
                //如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
                if (nums[i] < 0) {
                    int tmp = imax;
                    imax = imin;
                    imin = tmp;
                }
                //最大值如何取
                imax = Math.max(imax * nums[i], nums[i]);
                //最小值如何取
                imin = Math.min(imin * nums[i], nums[i]);
                //取res
                res = Math.max(res, imax);
            }
            return res;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {2, 3, -4, -8, -9};
            System.out.println(solution.maxProduct(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
