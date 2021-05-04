package solution.leetcode.editor.cn;

public class LeetCode27 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //给定 nums = [3,2,2,3], val = 3,还要原地修改数组
        public int removeElement(int[] nums, int val) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != val)
                    nums[res++] = nums[i];
            }
            return res;
        }

        public static void main(String[] args) {
            Solution so = new Solution();
            int[] arr = {3, 2, 2, 3};
            System.out.println(so.removeElement(arr, 2));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
