package solution.leetcode.editor.cn;

import java.util.Arrays;

//这题要好好吃透哦
public class LeetCode214 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            String s = "aabc";
            //输出cbaabc,也就是我们要找到bc
            System.out.println(solution.shortestPalindrome(s));
        }

        public String shortestPalindromeRobin(String s) {
            int n = s.length();
            int base = 131, mod = 1000000007;
            int left = 0, right = 0, mul = 1;
            int best = -1;
            for (int i = 0; i < n; ++i) {
                left = (int) (((long) left * base + s.charAt(i)) % mod);
                right = (int) ((right + (long) mul * s.charAt(i)) % mod);
                if (left == right) {
                    best = i;
                }
                mul = (int) ((long) mul * base % mod);
            }
            String add = (best == n - 1 ? "" : s.substring(best + 1));
            StringBuffer ans = new StringBuffer(add).reverse();
            ans.append(s);
            return ans.toString();
        }

        //214. 最短回文串 KMP 算法
        public String shortestPalindrome(String s) {
            int n = s.length();
            //fail的含义是什么呢?
            int[] fail = new int[n];
            Arrays.fill(fail, -1);
            for (int i = 1; i < n; ++i) {
                int j = fail[i - 1];
                //当不等时一直更新j的值
                while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                    j = fail[j];
                }
                //如果相等了更新fail[i]的值
                if (s.charAt(j + 1) == s.charAt(i)) {
                    fail[i] = j + 1;
                }
            }
            //找best
            int best = -1;
            //从后向前找
            for (int i = n - 1; i >= 0; --i) {
                //不等时一直跟新best
                while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                    best = fail[best];
                }
                //相等时best++
                if (s.charAt(best + 1) == s.charAt(i)) {
                    ++best;
                }
            }
            String add = (best == n - 1 ? "" : s.substring(best + 1));
            StringBuffer ans = new StringBuffer(add).reverse();
            ans.append(s);
            return ans.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
