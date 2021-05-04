package solution.leetcode.editor.cn;

public class LeetCode509 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static int fib(int n) {
            if (n <= 1) return n;
            int a = 0;
            int b = 1;
            for (int i = 2; i <= n; i++) {
                int temp = b;
                b = a + b;
                a = temp;
            }
            return b;
        }

        public static void main(String[] args) {
            System.out.println(fib(2));
            System.out.println(fib(3));
            System.out.println(fib(4));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
