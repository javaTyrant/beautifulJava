package solution.leetcode.editor.cn;

public class LeetCode205 {
    //同构字符串
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static boolean isIsomorphic(String s, String t) {
            char[] chars = s.toCharArray();
            char[] chart = t.toCharArray();
            int[] preIndexOfs = new int[256];
            int[] preIndexOft = new int[256];
            for (int i = 0; i < chars.length; i++) {
                if (preIndexOfs[chars[i]] != preIndexOft[chart[i]]) {
                    return false;
                }
                preIndexOfs[chars[i]] = i + 1;
                preIndexOft[chart[i]] = i + 1;
            }
            return true;
        }

        public static void main(String[] args) {
            isIsomorphic("title", "paper");
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
