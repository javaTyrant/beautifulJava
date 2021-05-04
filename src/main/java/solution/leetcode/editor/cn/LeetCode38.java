package solution.leetcode.editor.cn;

import org.jetbrains.annotations.NotNull;

//简单题哈哈
public class LeetCode38 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static String countAndSay(int n) {
            //base case
            if (n == 1) return "1";
            //减一
            String num = countAndSay(n - 1);
            //sb
            return countString(num);
        }

        @NotNull
        private static String countString(String num) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < num.length(); i++) {
                int count = 1;
                //i不能越界,统计相等的数字
                while (i < num.length() - 1 && num.charAt(i) == num.charAt(i + 1)) {
                    count++;
                    i++;
                }
                //直接append
                s.append(count);
                s.append(num.charAt(i));
            }
            return s.toString();
        }

        public static void main(String[] args) {
            System.out.println(countString("112"));
            System.out.println(countAndSay(3));
            System.out.println(countAndSay(4));
            System.out.println(countAndSay(5));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
