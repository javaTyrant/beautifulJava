package solution.leetcode.editor.cn;

public class LeetCode45 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //局部解->最优解
        public int jump(int[] nums) {
            int end = 0;
            int maxPosition = 0;
            int steps = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                //找能跳的最远的
                maxPosition = Math.max(maxPosition, nums[i] + i);
                if (i == end) { //遇到边界，就更新边界，并且步数加一
                    end = maxPosition;
                    steps++;
                }
            }
            return steps;
        }

        public static void main(String[] args) {
            int[] arr = {2, 3, 1, 1, 4};
            Solution solution = new Solution();
            System.out.println(solution.jump(arr));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
