package solution.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class LeetCode1684 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //统计一致字符串的数目.战胜了8%,朴素的解法
        public int countConsistentStrings(String allowed, String[] words) {
            int res = 0;
            for (String word : words) {
                if (allowed(allowed, word)) {
                    res++;
                }
            }
            return res;
        }

        private boolean allowed(String a, String b) {
            Set<Character> set = new HashSet<>();
            for (Character c : a.toCharArray()) {
                set.add(c);
            }
            for (Character ch : b.toCharArray()) {
                if (!set.contains(ch)) {
                    return false;
                }
            }
            return true;
        }

        public int countConsistentStringsBest(String allowed, String[] words) {
            int[] mark = new int[26];
            for (char c : allowed.toCharArray()) {
                mark[c - 'a'] = 1;
            }
            int res = 0;
            for (String word : words) {
                int len = word.length();
                int index = 0;
                while (index < len) {
                    if (mark[word.charAt(index) - 'a'] == 0)
                        break;
                    index++;
                }
                if (index == len) {
                    res += 1;
                }

            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
