package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode216 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
        //
        //说明：
        //
        //所有数字都是正整数。
        //解集不能包含重复的组合。 
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> res = new ArrayList<>();
            dfs(1, 0, k, n, res, new ArrayList<>());
            return res;
        }

        private void dfs(int i, int sum, int k, int n, List<List<Integer>> res, List<Integer> cur) {
            //base case k个,sum==n
            if (k == 0 && sum == n) {
                res.add(new ArrayList<>(cur));
            }
            if (sum > n) return;
            for (int j = i; j <= (Math.min(9, n)); j++) {
                cur.add(j);
                dfs(j + 1, sum + j, k - 1, n, res, cur);
                cur.remove(cur.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
