package solution.leetcode.editor.cn;

public class LeetCode168 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static String convertToTitle(int n) {
            if (n <= 0) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                n--;
                //妙
                sb.append((char) (n % 26 + 'A'));
                n = n / 26;
            }
            return sb.reverse().toString();
        }

        public static void main(String[] args) {
            System.out.println(convertToTitle(54));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
