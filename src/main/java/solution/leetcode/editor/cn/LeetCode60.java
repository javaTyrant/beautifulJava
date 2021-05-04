package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode60 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
        //给定 n 和 k，返回第 k 个排列。
        public String getPermutation(int n, int k) {
            StringBuilder sb = new StringBuilder();
            // 候选数字
            List<Integer> candidates = new ArrayList<>();
            // 分母的阶乘数
            int[] factorials = new int[n + 1];
            factorials[0] = 1;
            int fact = 1;
            for (int i = 1; i <= n; ++i) {
                candidates.add(i);
                fact *= i;
                factorials[i] = fact;
            }
            k -= 1;
            for (int i = n - 1; i >= 0; --i) {
                // 计算候选数字的index
                int index = k / factorials[i];
                sb.append(candidates.remove(index));
                k -= index * factorials[i];
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
