package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode93 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> ans = new ArrayList<>();
            back(s, ans, new ArrayList<>(), 0);
            return ans;
        }

        private void back(String s, List<String> ans, List<String> cur, int pos) {
            //base case
            if (cur.size() == 4) {
                if (s.length() == pos) {
                    ans.add(String.join(".", cur));
                }
                //没有用完
                return;
            }
            for (int i = 1; i <= 3; i++) {
                //不能越界哦
                if (pos + i > s.length()) break;
                //截取当前的元素
                String seg = s.substring(pos, pos + i);
                //第一个元素是0,长度不能超过1,长度为3不能大于255
                if (seg.startsWith("0") && seg.length() > 1
                        || (i == 3 && Integer.parseInt(seg) > 255))
                    continue;
                //添加
                cur.add(seg);
                //注意是+i 不是加1
                back(s, ans, cur, pos + i);
                //回溯
                cur.remove(cur.size() - 1);
            }
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            solution.restoreIpAddresses("25525511135");
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
