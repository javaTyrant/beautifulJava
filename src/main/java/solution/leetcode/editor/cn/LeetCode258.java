package solution.leetcode.editor.cn;

public class LeetCode258 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //258. 各位相加
        public static void main(String[] args) {

        }

        public int addDigits(int num) {
            return (num - 1) % 9 + 1;
        }

        public int addDigits1(int num) {
            while (num >= 10) {
                int next = 0;
                while (num != 0) {
                    next = next + num % 10;
                    num /= 10;
                }
                num = next;
            }
            return num;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
