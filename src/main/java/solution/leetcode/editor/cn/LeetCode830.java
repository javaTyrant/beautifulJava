package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode830 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
        //输入：s = "abcdddeeeeaabbbcd"
        //输出：[[3,5],[6,9],[12,14]]
        public List<List<Integer>> largeGroupPositions(String s) {
            List<List<Integer>> res = new ArrayList<>();
            char[] c = s.toCharArray();
            int n = c.length;
            for (int i = 0, j = -1; i < n; i++) {
                //如果相等才加加,不然下一个循环
                while (i + 1 < n && c[i + 1] == c[i]) i++;
                //大于2才跟新res
                if (i - j > 2) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(j + 1);
                    tmp.add(i);
                    res.add(tmp);
                }
                //更新j的值
                j = i;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
