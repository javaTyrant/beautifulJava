package solution.leetcode.editor.cn;

import java.util.Random;

public class LeetCode470 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class SolBase {
        public int rand7() {
            return new Random().nextInt(7);
        }
    }

    /**
     * The rand7() API is already defined in the parent class SolBase.
     * public int rand7();
     *
     * @return a random integer in the range 1 to 7
     */
    static class Solution extends SolBase {
        public int rand10() {
            int random = (rand7() - 1) * 7;
            int ans = random + rand7();
            while (ans > 40) {
                ans = random + rand7();
            }
            return ans % 10 + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
