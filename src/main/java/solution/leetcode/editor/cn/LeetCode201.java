package solution.leetcode.editor.cn;

public class LeetCode201 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rangeBitwiseAnd(8, 9));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，
        // 返回此范围内所有数字的按位与（包含 m, n 两端点）。
        //010111 + 1 = 011000，则010111 & 011000 = 010000。那么，x & (x+1) 后几位相反数的“与操作”，结果总为0。
        public int rangeBitwiseAnd(int m, int n) {
            int offset = 0;
            for (; m != n; ++offset) {
                m >>= 1;
                n >>= 1;
            }
            return n << offset;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
