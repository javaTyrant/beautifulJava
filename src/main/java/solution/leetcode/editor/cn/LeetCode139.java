package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode139 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //单词拆分
        public static boolean wordBreak(String s, List<String> wordDict) {
            int len = s.length(), maxw = 0;
            //多一个
            boolean[] dp = new boolean[len + 1];
            dp[0] = true;
            Set<String> set = new HashSet();
            //去除重复的数据,找出最长的单词长度
            for (String str : wordDict) {
                set.add(str);
                maxw = Math.max(maxw, str.length());
            }
            //两次循环
            for (int i = 1; i < len + 1; i++) {
                for (int j = i; j >= 0 && j >= i - maxw; j--) {
                    if (dp[j] && set.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[len];
        }

        public static void main(String[] args) {
            System.out.println(wordBreak("aboutdick", Arrays.asList("about", "eat", "dick")));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
