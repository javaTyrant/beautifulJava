package dp;

import edu.princeton.cs.algs4.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lumac
 * @since 2020/7/24
 */
public class LargestRectangleArea {
    public static void main(String[] args) {
//        int[] arr = {2, 1, 3, 4, 5, 2};
        int[] arr = {6, 7, 5, 2, 4, 5, 9, 3};
//        int[] arr = {3,2,1};
        System.out.println(largestRectangleArea(arr));
    }

    //84. 柱状图中最大的矩形 太优雅了吧,如何思考,think,think

    /**
     * 为什么这题要用到栈
     * 算法总结:
     * 1.
     * 2.
     * 3.
     */
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
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            //然后把索引push进去
            stack.push(i);
        }
        //再计算一次,相当于把递增的改成递减的,然后计算,牛逼啊
        while (stack.peek() != -1)
            //如果是2,1这种情况,-1刚好把-1给抵消掉了
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        return maxarea;
    }

    //
    public int largestRectangleAreaCad(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int[] lessFromRight = new int[heights.length];
        int[] lessFromLeft = new int[heights.length];
        lessFromRight[heights.length - 1] = heights.length;
        lessFromLeft[0] = -1;

        //找左边界
        for (int i = 1; i < heights.length; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }
        //找右边界
        for (int i = heights.length - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < heights.length && heights[p] >= heights[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }
        //计算面积
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }
        return maxArea;
    }

    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[m - 1][n - 1];
        while (lo <= hi) {
            //mid
            int cnt = 0, mid = lo + (hi - lo) / 2;
            int i = m - 1, j = 0;
            while (i >= 0 && j < n) {
                //小于等于mid
                if (matrix[i][j] <= mid) {
                    cnt += i + 1;
                    j++;
                } else i--;
            }
            if (cnt < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }
}
