package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeetCode120 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //120. 三角形最小路径和
        public int minimumTotal(List<List<Integer>> triangle) {
            //获取二维数组的长度
            int n = triangle.size();
            //构造一个数组
            int[] f = new int[n];
            //获取第0个元素
            f[0] = triangle.get(0).get(0);
            //从1开始
            for (int i = 1; i < n; ++i) {
                //f[i] = f[i-1] + 第i行的第i个元素
                //首尾只能累加上一层的,所以需要单独累加两次
                f[i] = f[i - 1] + triangle.get(i).get(i);
                //中间节点需要上层较小的和当前的值累加
                for (int j = i - 1; j > 0; --j) {
                    f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
                }
                //为什么还要单独更新f[0]呢,每层都要更新一次
                f[0] += triangle.get(i).get(0);
            }
            int minTotal = f[0];
            //取f[n]中最小的值
            for (int i = 1; i < n; ++i) {
                minTotal = Math.min(minTotal, f[i]);
            }
            return minTotal;
        }

        public static void main(String[] args) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(Collections.singletonList(2));
            res.add(Arrays.asList(3, 4));
            res.add(Arrays.asList(6, 5, 7));
            res.add(Arrays.asList(4, 1, 8, 3));
            Solution solution = new Solution();
            System.out.println(solution.minimumTotal(res));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
