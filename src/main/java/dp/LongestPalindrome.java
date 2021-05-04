package dp;

/**
 * 最长回文子串
 *
 * @author lumac
 * @since 2020-05-21
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(expandAroundCenter("abba", 1, 2));
        System.out.println(expandAroundCenter("ababa", 2, 2));
//        System.out.println(longestPalindrome("bb"));
        //偶数dabbcc
//        System.out.println(2 - (4 - 1) / 2);
//        System.out.println(2 - 4 / 2 + 1);
//        //奇数dabac,起点应该是1,所以应该选择2
//        System.out.println(2 - (3 - 1) / 2);
//        System.out.println(2 - 3 / 2 + 1);
    }

    //中心扩展法,搞定
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        //需要记录起点和终点
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            //abba,aba中心点必定有两个或者一个,再多的情况都可以规约成一个或者两个
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            //取最大的
            int len = Math.max(len1, len2);
            //新的长度大于原来保存的长度
            if (len > end - start) {
                //起点,如何推导出公式呢
                start = i - (len - 1) / 2;
                //终点,
                end = i + len / 2;
            }
        }
        //end+1
        return s.substring(start, end + 1);
    }

    /**
     * 扩展
     *
     * @param s     string
     * @param left  左指针
     * @param right 右指针
     * @return 回文的长度
     */
    private static int expandAroundCenter(String s, int left, int right) {
        //如果l可以等于0,那边如果下面相等
        //为什么left必须要等于0
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    //dp.今晚回去搞定
    public static String longestPalindromeDp(String s) {
        if (s == null || s.length() < 2) return s;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int maxLen = 1;
        int start = 0;
        //如何记录,先j后i
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    if (maxLen < j - i + 1) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        //思考下为什么是maxlen + start
        return s.substring(start, maxLen + start);
    }
}
