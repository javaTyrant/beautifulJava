package solution.leetcode.editor.cn;

public class LeetCode29 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            System.out.println(divide(10, 3));
        }

        public static int divide(int dividend, int divisor) {
            if (dividend == 0) {
                return 0;
            }
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            boolean negative;
            //用异或来计算是否符号相异
            negative = (dividend ^ divisor) < 0;
            long t = Math.abs((long) dividend);
            long d = Math.abs((long) divisor);
            int result = 0;
            for (int i = 31; i >= 0; i--) {
                if ((t >> i) >= d) {//找出足够大的数2^n*divisor
                    result += 1 << i;//将结果加上2^n
                    t -= d << i;//将被除数减去2^n*divisor
                }
            }
            return negative ? -result : result;//符号相异取反
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
