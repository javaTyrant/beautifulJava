package solution.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

public class LeetCode1278 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
        //返回 s 所有可能的分割方案。
        public List<List<String>> partition(String s) {
            List<List<String>> res = new LinkedList<>();
            if (s == null || s.isEmpty()) {
                return res;
            }
            LinkedList<String> cur = new LinkedList<>();


            char[] chars = s.toCharArray();
            partitionUtilFaster(chars, 0, chars.length, res, cur);
            return res;
        }

        private void partitionUtilFaster(char[] chars, int start, int end, List<List<String>> res, LinkedList<String> cur) {
            if (start == end) {
                res.add(new LinkedList<>(cur));
                return;
            }
            for (int i = start; i < end; i++) {
                if (!check(chars, start, i)) {
                    continue;
                }
                cur.addLast(new String(chars, start, i - start + 1));
                partitionUtilFaster(chars, i + 1, end, res, cur);
                cur.removeLast();
            }
        }

        private boolean check(char[] chars, int start, int end) {
            while (start < end) {
                if (chars[start] != chars[end]) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
