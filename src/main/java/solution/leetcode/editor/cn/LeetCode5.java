package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode5 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.longestPalindrome("ackffkcc"));
            System.out.println(solution.longestPalindromeMa("ackffkcc"));
        }

        //动态规划
        public String longestPalindromeDP(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            String ans = "";
            for (int l = 0; l < n; ++l) {
                for (int i = 0; i + l < n; ++i) {
                    int j = i + l;
                    if (l == 0) {
                        dp[i][j] = true;
                    } else if (l == 1) {
                        dp[i][j] = (s.charAt(i) == s.charAt(j));
                    } else {
                        dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                    }
                    if (dp[i][j] && l + 1 > ans.length()) {
                        ans = s.substring(i, i + l + 1);
                    }
                }
            }
            return ans;
        }

        //中心扩散法
        public String longestPalindrome(String s) {
            //双指针
            int start = 0, end = 0;
            //从0开始往两边扩散
            for (int i = 0; i < s.length(); i++) {
                //奇数偶数,要扩散两次
                int max = Math.max(expand(s, i, i), expand(s, i, i + 1));
                //大才更新
                if (max > end - start) {
                    //更新start,奇数偶数
                    start = i - (max - 1) / 2;
                    //更新end
                    end = i + max / 2;
                }
            }
            //end要加1
            return s.substring(start, end + 1);
        }

        //左右扩散
        private int expand(String s, int left, int right) {
            //左右边界判断,且左右相等
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return right - left - 1;
        }

        //Manacher算法
        public String longestPalindromeMa(String s) {
            int start = 0, end = -1;
            StringBuffer t = new StringBuffer("#");
            for (int i = 0; i < s.length(); ++i) {
                t.append(s.charAt(i));
                t.append('#');
            }
            t.append('#');
            s = t.toString();
            //armLen是什么意思
            List<Integer> armLen = new ArrayList<>();
            int right = -1, j = -1;
            for (int i = 0; i < s.length(); ++i) {
                int curArmLen;
                //right>=i是什么意思
                if (right >= i) {
                    int iSym = j * 2 - i;
                    int minArmLen = Math.min(armLen.get(iSym), right - i);
                    curArmLen = expandMa(s, i - minArmLen, i + minArmLen);
                } else {
                    //否则呢
                    curArmLen = expandMa(s, i, i);
                }
                //
                armLen.add(curArmLen);
                //why?
                if (i + curArmLen > right) {
                    j = i;
                    right = i + curArmLen;
                }
                //why
                if (curArmLen * 2 + 1 > end - start) {
                    start = i - curArmLen;
                    end = i + curArmLen;
                }
            }

            StringBuffer ans = new StringBuffer();
            for (int i = start; i <= end; ++i) {
                if (s.charAt(i) != '#') {
                    ans.append(s.charAt(i));
                }
            }
            return ans.toString();
        }

        //和中心扩散法的略有不同
        public int expandMa(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }
            return (right - left - 2) / 2;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
