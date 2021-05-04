package solution.leetcode.editor.cn;

public class LeetCode55 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //跳跃游戏
        public boolean canJump(int[] nums) {
            //最后索引
            int lastPos = nums.length - 1;
            for (int i = nums.length - 1; i >= 0; i--) {
                if (i + nums[i] >= lastPos) {
                    lastPos = i;
                }
            }
            return lastPos == 0;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {3, 2, 1, 0, 3};
            System.out.println(solution.canJump(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}