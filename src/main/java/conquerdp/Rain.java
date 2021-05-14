package conquerdp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lufengxiang
 * @since 2021/5/14
 **/
public class Rain {
    static class MySolution {
        public int trap(int[] height) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Rain rain = new Rain();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(rain.trap(height));
    }

    //接雨水
    //输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
    //输出：6
    //解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，
    //在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
    public int trap(int[] height) {
        //木桶原理
        int res = 0, maxLeft = 0, maxRight = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            if (maxLeft < height[right]) {
                if (maxLeft < height[left]) {
                    maxLeft = height[left];
                } else {
                    res += maxLeft - height[left];
                    left++;
                }
            } else {
                if (maxRight < height[right]) {
                    maxRight = height[right];
                } else {
                    res += maxRight - height[right];
                    right--;
                }
            }
        }
        return res;
    }

    public int trapStack(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    //时间复杂度：O(n)
    public int trapDp(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
}
