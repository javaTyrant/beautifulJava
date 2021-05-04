package solution.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode84 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            int[] arr = {2, 1, 5, 6, 2, 3};
            System.out.println(largestRectangleArea(arr));
        }

        public static int largestRectangleArea(int[] heights) {
            //栈
            Deque<Integer> stack = new ArrayDeque<>();
            //先放一个-1
            stack.push(-1);
            //保存最大的值
            int maxarea = 0;
            for (int i = 0; i < heights.length; ++i) {
                //stack.peek()是啥,当前一个比当前的矮,为什么当前的大于之前的就不用进入循环呢?
                //我们先考虑两种极端的情况吧,如果是递减的和是递增的
                //这个处理的是递减的
                while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                    //最大值,保存的和
                    //和下面的 只是 i 和 heights.length的区别 高 * 宽
                    int height = heights[stack.pop()];
                    int width = i - stack.peek() - 1;
                    maxarea = Math.max(maxarea, height * width);
                }
                //然后把索引push进去
                stack.push(i);
            }
            //再计算一次,相当于把递增的改成递减的,然后计算,牛逼啊
            while (stack.peek() != -1) {
                int height = heights[stack.pop()];
                //这个很妙
                int width = heights.length - stack.peek() - 1;
                maxarea = Math.max(maxarea, height * width);
            }
            return maxarea;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
