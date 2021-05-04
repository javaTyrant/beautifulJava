package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lumac
 * @since 2020/6/25
 */
public class WordBreak {
    public static boolean breakWorld(String s, List<String> wordDict) {
         Set<String> wordDictSet = new HashSet<>(wordDict);
         boolean[] dp = new boolean[s.length() + 1];
         dp[0] = true;
         //leetcode leet code
         //
         for (int i = 1; i <= s.length(); i++) {
             for (int j = 0; j < i; j++) {
                 if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                     dp[i] = true;
                     break;
                 }
             }
         }
         return dp[s.length()];
    }
    //优化版
    public static boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length(), maxw = 0;
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
            maxw = Math.max(maxw, str.length());
        }
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
        System.out.println(wordBreak("Iamace", Arrays.asList("Iamc")));
    }
}
