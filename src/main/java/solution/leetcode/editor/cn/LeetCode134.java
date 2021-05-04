package solution.leetcode.editor.cn;

public class LeetCode134 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int cur = 0;
            int total = 0;
            int start = 0;
            for (int i = 0; i < gas.length; i++) {
                total += (gas[i] - cost[i]);
                cur += (gas[i] - cost[i]);
                if (cur < 0) {
                    cur = 0;
                    start = i + 1;
                }
            }
            return total < 0 ? -1 : start;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
