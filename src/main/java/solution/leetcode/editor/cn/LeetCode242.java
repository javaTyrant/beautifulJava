package solution.leetcode.editor.cn;

public class LeetCode242 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //虽然简单,还是妙的
        public boolean isAnagram(String s, String t) {
            int count[] = new int[26];
            for (char ch : s.toCharArray()) count[ch - 'a']++;
            for (char ch : t.toCharArray()) count[ch - 'a']--;
            for (int i = 0; i < 26; i++) {
                if (count[i] != 0) return false;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
