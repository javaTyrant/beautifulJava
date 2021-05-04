package solution.leetcode.editor.cn;

import java.util.*;

public class LeetCode301 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //
        public List<String> removeInvalidParentheses(String s) {
            Set<String> set = new HashSet<>();
            List<String> res = new ArrayList<>();
            Queue<String> queue = new LinkedList<>();
            queue.offer(s);
            set.add(s);
            boolean exist = false;
            while (!queue.isEmpty()) {
                String currentString = queue.poll();
                if (isValid(currentString)) {
                    res.add(currentString);
                    exist = true;
                }
                if (exist) {
                    continue;
                }
                for (int i = 0; i < currentString.length(); i++) {
                    if (!Character.isLetter(currentString.charAt(i))) {
                        String newString = currentString.substring(0, i) + currentString.substring(i + 1);
                        if (!set.contains(newString)) {
                            queue.offer(newString);
                            set.add(newString);
                        }
                    }
                }
            }
            return res;
        }

        private boolean isValid(String str) {
            int count = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    count++;
                } else if (str.charAt(i) == ')') {
                    if (count == 0) {
                        return false;
                    } else {
                        count--;
                    }
                }
            }
            return count == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
