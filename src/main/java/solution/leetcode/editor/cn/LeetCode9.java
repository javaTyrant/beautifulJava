package solution.leetcode.editor.cn;

public class LeetCode9 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //你能不将整数转为字符串来解决这个问题吗?
        public static boolean isPalindrome(int x) {
            char[] chars = String.valueOf(x).toCharArray();
            int i = 0;
            int j = chars.length - 1;
            while (i < j) {
                if (chars[i] != chars[j]) return false;
                i++;
                j--;
            }
            return true;
        }

        public static void main(String[] args) {
            System.out.println(isPalindrome(-121));
            System.out.println(isPalindromeAnother(121));
        }

        //反转一半数字
        public static boolean isPalindromeAnother(int x) {
            if (x < 0 || (x % 10 == 0 && x != 0)) {
                return false;
            }
            //反转整数
            int revert = 0;
            //如何找到一半
            while (x > revert) {
                //*10 + %10
                revert = revert * 10 + x % 10;
                x /= 10;
            }
            return x == revert || x == revert / 10;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
