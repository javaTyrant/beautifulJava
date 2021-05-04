package solution.leetcode.editor.cn;

public class LeetCode856 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            //4
            System.out.println(scoreOfParentheses("(()())"));
        }

        //括号的分数
        public static int scoreOfParentheses(String S) {
            int length = S.length();
            int sum = 0;
            int n = 0;
            for (int i = 0; i < length; i++) {
                if (S.charAt(i) == '(') {
                    if (n == 0) {
                        n = 1;
                    } else {
                        n = n * 2;
                    }
                } else if (S.charAt(i) == ')') {
                    if (S.charAt(i - 1) == '(') {
                        sum += n;
                    }
                    n = n * 2;

                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
