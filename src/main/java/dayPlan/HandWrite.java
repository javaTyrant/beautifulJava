package dayPlan;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lumac
 * @since 2021/3/5
 */
public class HandWrite {
    //暴力法 okay
    public int largestRectangleAreaBruteForce(int[] heights) {
        int max = 0;
        if (heights == null) return max;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int curHeight = heights[i];
            while (left >= 0 && heights[left] >= curHeight) {
                left--;
            }
            int right = i;
            while (right < heights.length && heights[right] >= curHeight) {
                right++;
            }
            max = Math.max(max, (right - left - 1) * curHeight);
        }
        return max;
    }

    //单调栈
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < tmp.length; i++) {
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
                //获取弹出元素的高度
                int h = tmp[stack.pop()];
                //peek
                max = Math.max(max, (i - stack.peek() - 1) * h);
            }
            stack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {
        HandWrite pra = new HandWrite();
        int[] height = {2, 1, 5, 6, 2, 3};
        System.out.println(pra.largestRectangleAreaBruteForce(height));
        System.out.println(pra.largestRectangleArea(height));
    }
}
