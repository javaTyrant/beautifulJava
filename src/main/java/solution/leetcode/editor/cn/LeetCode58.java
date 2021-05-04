package solution.leetcode.editor.cn;

public class LeetCode58 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public int lengthOfLastWord(String s) {
            s = s.trim();
            int i = s.length() - 1;
            int j = 0;
            int res = 0;
            while (i >= j) {
                if (s.charAt(i) == ' ') {
                    return res;
                }
                if (s.charAt(i) != ' ') {
                    i--;
                    res++;
                }
            }
            return res;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.lengthOfLastWord("hello world"));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
