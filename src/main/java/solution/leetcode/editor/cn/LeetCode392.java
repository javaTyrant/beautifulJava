package solution.leetcode.editor.cn;

public class LeetCode392 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
        public static boolean isSubsequence(String s, String t) {
            //冗余了,冗余代码比较多
            if (s == null || s.length() == 0) return false;
            int i = 0;
            int j = 0;
            int m = s.length();
            int n = t.length();
            while (i < m && j < n) {
                while (i < m && j < n && s.charAt(i) == t.charAt(j)) {
                    i++;
                    j++;
                }
                if (i == s.length()) return true;
                j++;
            }
            return false;
        }

        //可以简化成这种 老练
        public boolean isSubsequenceBest(String s, String t) {
            int n = s.length(), m = t.length();
            int i = 0, j = 0;
            while (i < n && j < m) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                }
                j++;
            }
            return i == n;
        }

        public static void main(String[] args) {
            System.out.println(isSubsequence("abc", "acbc"));
            System.out.println(isSubsequence("abcd", "acbc"));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
