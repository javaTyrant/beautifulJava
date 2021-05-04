package solution.leetcode.editor.cn;

public class LeetCode28 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //实现str
        //accdb  cdb
        //pn = 4 n = 3 暴力法,kmp
        public static int strStr(String haystack, String needle) {
            int m = haystack.length();
            int n = needle.length();
            if (n == 0) return 0;
            int pn = 0;
            //双指针
            while (pn < m - n + 1) {
                while (pn < m - n + 1 && haystack.charAt(pn) != needle.charAt(0)) {
                    pn++;
                }
                //记录最大匹配长度
                int maxLen = 0;
                int pl = 0;
                while (pl < n && pn < m && haystack.charAt(pn) == needle.charAt(pl)) {
                    maxLen++;
                    pl++;
                    pn++;
                }
                //pn实际上会多加一次,长度满足
                if (maxLen == n) return pn - n;
                //回溯,kmp优化
                pn = pn - maxLen + 1;
            }
            return -1;
        }

        public static void main(String[] args) {
            System.out.println(strStr("accdb", "cdb"));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
