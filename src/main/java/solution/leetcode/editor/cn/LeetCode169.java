package solution.leetcode.editor.cn;

public class LeetCode169 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //思路:
        public static int majorityElement(int[] nums) {
            int count = 0;
            Integer candidate = null;

            for (int num : nums) {
                //如果count == 0,candidate = num
                if (count == 0) {
                    candidate = num;
                }
                //相等+1,不等-1
                count += (num == candidate) ? 1 : -1;
            }

            return candidate;
        }

        public static void main(String[] args) {

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
