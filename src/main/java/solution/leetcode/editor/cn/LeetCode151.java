package solution.leetcode.editor.cn;

import java.util.Stack;

public class LeetCode151 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static String reverseWords(String s) {
            //栈
            Stack<String> words = new Stack<>();
            StringBuilder sb = new StringBuilder();
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ' ' || i == chars.length - 1) {
                    if (i == chars.length - 1) {
                        sb.append(chars[i]);
                    }
                    if (sb.length() > 0) {
                        words.push(sb.toString());
                        sb = new StringBuilder();
                    }
                    continue;
                } else {
                    sb.append(chars[i]);
                }
            }
            sb = new StringBuilder();
            while (!words.isEmpty()) {
                sb.append(words.pop() + " ");
            }
            return sb.toString().trim();
        }

        public static void main(String[] args) {
            System.out.println(reverseWords(" hello   love world   "));
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}
