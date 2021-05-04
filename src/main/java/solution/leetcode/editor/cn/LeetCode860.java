package solution.leetcode.editor.cn;

public class LeetCode860 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public boolean lemonadeChange(int[] bills) {
            int five = 0;
            int ten = 0;
            for (int i : bills) {
                if (i == 5) {
                    //只收不找零
                    five++;
                } else if (i == 10) {
                    //找一个五,多一个十
                    five--;
                    ten++;
                    //如果还有十的话,减去一个10,减去一个五
                } else if (ten > 0) {
                    //找一个十,找一个五
                    five--;
                    ten--;
                } else {
                    //如果没有十了,减去3个五,get
                    five -= 3;
                }
                if (five < 0) {
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] coins = {5, 5, 10, 20, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 5, 5, 20, 5, 20, 5};
            System.out.println(solution.lemonadeChange(coins));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
