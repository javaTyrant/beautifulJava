package solution.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LCOF48 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> window = new HashMap<>();
            char[] chars = s.toCharArray();
            int ans = 0;
            for (int end = 0, start = 0; end < s.length(); end++) {
                char c = s.charAt(end);
                if (window.containsKey(c)) {
                    start = Math.max(start, window.get(c));
                }
                ans = Math.max(ans, end - start + 1);
                window.put(c, end + 1);
            }
            return ans;
        }

        public String replaceSpace(String s) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (c == ' ') {
                    sb.append("%20".toCharArray());
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

        public static void main(String[] args) {
            System.out.println(lengthOfLongestSubstring("abcdeccjkliesdj"));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
