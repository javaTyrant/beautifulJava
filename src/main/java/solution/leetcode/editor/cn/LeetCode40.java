package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode40 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //40. 组合总和 II
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(candidates);
            help(res, candidates, target, candidates.length, new ArrayList<>(), 0);
            return res;
        }

        private void help(List<List<Integer>> res, int[] candidates, int target, int len, List<Integer> list, int begin) {
            if (target == 0) {
                res.add(new ArrayList<>(list));
            }
            for (int i = begin; i < len; i++) {
                //判断够不够
                if (target - candidates[i] < 0) return;
                //去重,i > begin确保后面不会越界
                if (i > begin && candidates[i] == candidates[i - 1]) continue;
                list.add(candidates[i]);
                //用i,不能用begin哦
                help(res, candidates, target - candidates[i], len, list, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
