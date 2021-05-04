package solution.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class LeetCode3 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int rk = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符
                    occ.remove(s.charAt(i - 1));
                }
                while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                    // 不断地移动右指针,rk初始值是-1,所以要+1
                    occ.add(s.charAt(rk + 1));
                    ++rk;
                }//这边结束的时候,occ肯定包含了右指针的值
                // 第 i 到 rk 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, rk - i + 1);
            }
            return ans;
        }

        public static void main(String[] args) {
            System.out.println(lengthOfLongestSubstring("abba"));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
