package solution.leetcode.editor.cn;

public class LeetCode5633 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //计算力扣银行的钱
        public int totalMoney(int n) {
            int total = 0, week, offset;
            for (int i = 0; i < n; i++) {
                week = i / 7;
                offset = i % 7 + 1;
                total += week + offset;
            }
            return total;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
