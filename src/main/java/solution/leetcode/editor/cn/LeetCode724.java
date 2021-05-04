package solution.leetcode.editor.cn;

public class LeetCode724 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public String maximumTime(String time) {
            return "";
        }

        public static int pivotIndex(int[] nums) {
            //输入：nums = [1, 7, 3, 6, 5, 6]
            //输出：3
            //前缀和
            int[] pre = new int[nums.length];
            pre[0] = 0;
            for (int i = 1; i < nums.length; i++) {
                pre[i] = pre[i - 1] + nums[i - 1];
            }
            int s = 0;
            for (int n : nums) {
                s += n;
            }
            for (int i = 0; i < nums.length; i++) {
                if (pre[i] == (s - nums[i] - pre[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            Thread thread = new Thread(() -> System.out.println("hello"));
            while (true) {
                thread.start();
            }
//            int[] nums = {0, 0, 0, 0, 1};
//            System.out.println(pivotIndex(nums));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
