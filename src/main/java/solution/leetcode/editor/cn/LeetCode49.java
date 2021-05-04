package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LeetCode49 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            HashMap<String, ArrayList<String>> map = new HashMap<>();
            for (String s : strs) {
                char[] ch = s.toCharArray();
                Arrays.sort(ch);
                String key = String.valueOf(ch);
                if (!map.containsKey(key)) map.put(key, new ArrayList<>());
                map.get(key).add(s);
            }
            return new ArrayList(map.values());
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
