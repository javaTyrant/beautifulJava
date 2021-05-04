package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode131 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //aab -> [a,a,b],[aa,b]
        public List<List<String>> partition(String str) {
            List<List<String>> result = new ArrayList<>();
            if (str == null || str.isEmpty()) {
                return result;
            }

            List<String> curResult = new ArrayList<>();
            char[] source = str.toCharArray();
            traverse(source, 0, result, curResult);
            return result;
        }

        public void traverse(char[] source, int curIndex, List<List<String>> result, List<String> curResult) {
            if (curIndex == source.length) {
                result.add(new ArrayList<>(curResult));
                return;
            }
            for (int i = curIndex; i < source.length; i++) {
                if (isMatch(source, curIndex, i)) {
                    curResult.add(new String(source, curIndex, i - curIndex + 1));
                    traverse(source, i + 1, result, curResult);
                    curResult.remove(curResult.size() - 1);
                }
            }
        }

        public boolean isMatch(char[] source, int start, int end) {
            if (start == end) {
                return true;
            }

            for (int i = start, k = end; i < k; i++, k--) {
                if (source[i] != source[k]) {
                    return false;
                }
            }
            return true;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
