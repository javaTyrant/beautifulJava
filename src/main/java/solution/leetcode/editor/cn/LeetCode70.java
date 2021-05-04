package solution.leetcode.editor.cn;

public class LeetCode70 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //斐波那契,虽短小,但是真的经典
        public static int climbStairs(int n) {
            int[] memo = {1, 1};
            for (int i = 1; i < n; i++) {
                int temp = memo[1];
                memo[1] += memo[0];
                memo[0] = temp;
            }
            return memo[1];
        }

        public static void main(String[] args) {
            System.out.println(climbStairs(0));
            System.out.println(climbStairs(1));
            System.out.println(climbStairs(2));
            System.out.println(climbStairs(3));
            System.out.println(climbStairs(4));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
