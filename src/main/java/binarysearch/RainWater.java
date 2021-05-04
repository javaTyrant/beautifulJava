package binarysearch;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lumac
 * @since 2020/7/6
 */
public class RainWater {
    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trapbf(arr));
        int[] arr1 = {4, 3, 1, 0, 2, 4};
        System.out.println(trapStack(arr1));
        int[][] grids = {{1, 2}, {5, 6}, {1, 1}};
        System.out.println(maxValueOpt(grids));
        System.out.println(maxValue(grids));
    }

    //暴力法,暴力法的问题在哪里
    public static int trapbf(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int maxLeft = 0, maxRight = 0;
            //对于每个i,都需要找两次左右的边界,那么可不可以在外面就找到呢
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                maxLeft = Math.max(maxLeft, height[j]);
            }
            //
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                maxRight = Math.max(maxRight, height[j]);
            }
            ans += Math.min(maxLeft, maxRight) - height[i];
        }
        return ans;
    }

    //dp
    public static int trapdp(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        //用两个数组保存,空间换时间
        int[] leftMax = new int[size];
        int[] rightMax = new int[size];
        leftMax[0] = height[0];
        for (int i = 1; i < size; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }
        rightMax[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    //必须要懂这边的执行流程哦,不然不可能理解的
    public static int trapStack(int[] height) {
        int ans = 0, current = 0;
        Deque<Integer> stack = new LinkedList<>();
        while (current < height.length) {
            //如果当前的比栈顶的大,栈顶的必定是最小的
            //弹到当前小于栈顶的为止
            while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
                //弹出最小的
                int top = stack.pop();
                //弹出为空就可以不用处理了
                if (stack.isEmpty())
                    break;
                //长
                int distance = current - stack.peek() - 1;
                //高 为什么要peek呢,因为能接多少水取决于左右的大小
                int boundedHeight = Math.min(height[current], height[stack.peek()]) - height[top];
                ans += distance * boundedHeight;
            }
            stack.push(current++);
        }
        return ans;
    }

    //接雨水
    public static int trap(int[] height) {
        //双指针
        int left = 0, right = height.length - 1;
        //三个临时变量,左边最大值,右边最大值,答案
        int maxLeft = 0, maxRight = 0, res = 0;
        while (left < right) {
            //如果左边的高度小于右边
            if (height[left] < height[right]) {
                //如果左边的高度大于左边的最大值
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    //当左边的高度小于
                    res += (maxLeft - height[left]);
                }
                left++;
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    res += (maxRight - height[right]);
                }
                right--;
            }
        }
        return res;
    }

    //A.length == n + m
    public void merge(int[] A, int m, int[] B, int n) {

    }

    //     更简洁的写法
    //     int row = grid.length;
    //        int column = grid[0].length;
    //        //dp[i][j]表示从grid[0][0]到grid[i - 1][j - 1]时的最大价值
    //        int[][] dp = new int[row + 1][column + 1];
    //        for (int i = 1; i <= row; i++) {
    //            for (int j = 1; j <= column; j++) {
    //                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
    //            }
    //        }
    //        return dp[row][column];
    //    }
    //如何优化空间呢
    public static int maxValue(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = grid[i][0] + dp[i - 1][0];
                } else {
                    dp[i][j] = grid[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    //  更优雅的解法
    //  int m = grid.length, n = grid[0].length;
    //        int[] dp = new int[n + 1];
    //        for (int i = 1; i <= m; i++) {
    //            for (int j = 1; j <= n; j++) {
    //                dp[j] = Math.max(dp[j], dp[j - 1]) + grid[i - 1][j - 1];
    //            }
    //        }
    //
    //        return dp[n];
    public static int maxValueOpt(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int[] dp = new int[col + 1];
        dp[0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[j] = grid[i - 1][j - 1] + Math.max(dp[j - 1], dp[j]);
            }
        }
        return dp[col];
    }
}