package solution.leetcode.editor.cn;

//又是dp
public class LeetCode91 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static int numDecodings(String s) {
            //dp[i]用于记录字符串至第i-1位前的解码方法的总数
            int[] dp = new int[s.length() + 1];
            dp[0] = 1;

            for (int i = 1; i < s.length() + 1; i++) {
                //当前数字不为0时，dp[i] += dp[i - 1]表示当前组合数量包括前一位数字前的组合总数
                char c = s.charAt(i - 1);
                if (c != '0') {
                    dp[i] += dp[i - 1];
                }
                //当前数字与前一位组合的数字处于10-26时，dp[i] += dp[i - 2]表示当前组合数量包括前两位数字前的组合总数
                if (i > 1) {
                    int num = (s.charAt(i - 2) - '0') * 10 + (c - '0');
                    //提前剪枝：连续两个0没有对应解码方式
                    if (num == 0) {
                        return 0;
                    }
                    if (num > 9 && num < 27) {
                        dp[i] += dp[i - 2];
                    }
                }
            }
            //
            return s.length() == 0 ? 0 : dp[s.length()];
        }

        public static void main(String[] args) {
            System.out.println(numDecodings("226"));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
