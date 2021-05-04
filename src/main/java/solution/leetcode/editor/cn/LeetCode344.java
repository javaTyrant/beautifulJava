package solution.leetcode.editor.cn;

public class LeetCode344 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static String reverseString(String s) {
            if (s == null || s == "") {
                return null;
            }
            char[] chars = s.toCharArray();
            //走到一半就可以了
            for (int i = 0; i < chars.length / 2; i++) {
                //temp
                char t = chars[i];
                //交换
                chars[i] = chars[chars.length - 1 - i];
                chars[chars.length - 1 - i] = t;
            }
            return String.valueOf(chars);
        }

        public static void main(String[] args) {
            //简单
            String s = "abcd";
            String s1 = "abcde";
            reverseString(s);
            reverseString(s1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
