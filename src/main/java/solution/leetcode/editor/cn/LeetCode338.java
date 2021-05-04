package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode338 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，
        // 计算其二进制数中的 1 的数目并将它们作为数组返回。
        public static int[] countBits(int num) {
            int dp[] = new int[num + 1];
            for (int i = 0; i <= num / 2; i++) {
                //相差两倍1一定相同,相差1,差1.so
                dp[i * 2] = dp[i];
                if (i * 2 + 1 <= num)
                    dp[i * 2 + 1] = dp[i] + 1;
            }
            return dp;
        }

        public static void main(String[] args) {
            System.out.println(Arrays.toString(countBits(5)));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
