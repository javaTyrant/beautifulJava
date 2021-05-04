package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode77 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
        //
        //示例:
        //
        //输入n = 4, k = 2
        //输出:
        //[
        //  [2,4],
        //  [3,4],
        //  [2,3],
        //  [1,2],
        //  [1,3],
        //  [1,4],
        //]
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> res = new ArrayList<>();
            findAll(res, new ArrayList<>(), 0, n, k);
            return res;
        }

        private void findAll(List<List<Integer>> res, ArrayList<Integer> cur, int i, int n, int k) {
            if (cur.size() == k) {
                res.add(new ArrayList<>(cur));
            }
            for (int j = i; j <= n; j++) {
                cur.add(j);
                findAll(res, cur, j + 1, n, k);
                cur.remove(cur.size() - 1);
            }
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.combine(4, 2));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
