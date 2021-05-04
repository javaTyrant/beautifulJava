package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode118 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ret = new ArrayList<>();
            for (int i = 0; i < numRows; ++i) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j <= i; ++j) {
                    if (j == 0 || j == i) {
                        row.add(1);
                    } else {
                        row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                    }
                }
                ret.add(row);
            }
            return ret;
        }

        public List<List<Integer>> generateMy(int numRows) {
            List<List<Integer>> res = new ArrayList<>();
            if (numRows == 0) {
                return res;
            }
            if (numRows > 1) {
                res.add(Arrays.asList(1));
            } else {
                res.add(Arrays.asList(1));
                return res;
            }
            if (numRows > 2) {
                res.add(Arrays.asList(1, 1));
            } else {
                res.add(Arrays.asList(1, 1));
                return res;
            }
            for (int i = 3; i <= numRows; i++) {
                List<Integer> inner = new ArrayList<>();
                inner.add(1);
                for (int j = 0; j < i - 2; j++) {
                    inner.add(res.get(i - 2).get(j) + res.get(i - 2).get(j + 1));
                }
                inner.add(1);
                res.add(inner);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
