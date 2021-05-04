package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode455 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] child = {1, 3, 4, 5};
            int[] cookies = {1, 2, 2, 5};
            System.out.println(solution.findContentChildren(child, cookies));
        }

        //
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);//孩子的胃口
            Arrays.sort(s);//饼干的大小
            int ans = 0;
            int i, j = g.length - 1;
            //从最大的开始匹配
            for (i = s.length - 1; i >= 0; i--, j--) {
                //边界 + 如果最大的饼干都不能满足最大孩子的口味,那么往前一个孩子找
                while (j >= 0 && s[i] < g[j]) {
                    j--;
                }
                if (j < 0) break;
                //满足加1
                ans++;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
