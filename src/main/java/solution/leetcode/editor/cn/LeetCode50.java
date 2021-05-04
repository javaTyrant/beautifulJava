package solution.leetcode.editor.cn;

public class LeetCode50 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        static public double myPow(double x, int n) {
            double res = 1.0;
            for (int i = n; i != 0; i /= 2) {
                //奇数 = 乘一次
                if ((i & 1) != 0) {
                    res *= x;
                }
                x *= x;
            }
            return n < 0 ? 1 / res : res;
        }

        public static void main(String[] args) {
            System.out.println(myPow(2, 3));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
