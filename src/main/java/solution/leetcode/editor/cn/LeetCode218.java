package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode218 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //太难了吧
        public List<List<Integer>> getSkyline(int[][] buildings) {
            int len = buildings.length;
            if (len == 0) return new ArrayList<>();
            return segment(buildings, 0, len - 1);
        }

        private List<List<Integer>> segment(int[][] buildings, int l, int r) {
            // 创建返回值
            List<List<Integer>> res = new ArrayList<>();

            // 找到树底下的结束条件 -> 一个建筑物
            if (l == r) {
                res.add(Arrays.asList(buildings[l][0], buildings[l][2])); // 左上端坐标
                res.add(Arrays.asList(buildings[l][1], 0)); // 右下端坐标
                return res;
            }

            int mid = l + (r - l) / 2; // 取中间值

            // 左边递归
            List<List<Integer>> left = segment(buildings, l, mid);

            // 右边递归
            List<List<Integer>> right = segment(buildings, mid + 1, r);

            // 左右合并

            // 创建left 和 right 的索引位置
            int m = 0, n = 0;
            // 创建left 和 right 目前的高度
            int lpreH = 0, rpreH = 0;
            // 两个坐标
            int leftX, leftY, rightX, rightY;
            while (m < left.size() || n < right.size()) {

                // 当有一边完全加入到res时，则加入剩余的那部分
                if (m >= left.size()) res.add(right.get(n++));
                else if (n >= right.size()) res.add(left.get(m++));

                else { // 开始判断left 和 right
                    leftX = left.get(m).get(0); // 不会出现null，可以直接用int类型
                    leftY = left.get(m).get(1);
                    rightX = right.get(n).get(0);
                    rightY = right.get(n).get(1);

                    if (leftX < rightX) {
                        if (leftY > rpreH) res.add(left.get(m));
                        else if (lpreH > rpreH) res.add(Arrays.asList(leftX, rpreH));
                        lpreH = leftY;
                        m++;
                    } else if (leftX > rightX) {
                        if (rightY > lpreH) res.add(right.get(n));
                        else if (rpreH > lpreH) res.add(Arrays.asList(rightX, lpreH));
                        rpreH = rightY;
                        n++;
                    } else { // left 和 right 的横坐标相等
                        if (leftY >= rightY && leftY != (lpreH > rpreH ? lpreH : rpreH)) res.add(left.get(m));
                        else if (leftY <= rightY && rightY != (lpreH > rpreH ? lpreH : rpreH)) res.add(right.get(n));
                        lpreH = leftY;
                        rpreH = rightY;
                        m++;
                        n++;
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
