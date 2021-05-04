package solution.leetcode.editor.cn;

public class LeetCode887 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //鸡蛋掉落
        public int superEggDrop(int K, int N) {
            int[] dp = new int[K + 1];
            int m = 0;
            while (dp[K] < N) {
                for (int i = K; i >= 1; i--) {
                    dp[i] += dp[i - 1] + 1;
                }
                m++;
            }
            return m;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
