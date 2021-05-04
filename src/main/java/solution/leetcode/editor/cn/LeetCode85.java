package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode85 {
    //单调栈
    class SolutionStack {
        public int maximalRectangle(char[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }
            int[] heights = new int[matrix[0].length];
            int maxArea = 0;
            for (int row = 0; row < matrix.length; row++) {
                //遍历每一列，更新高度
                for (int col = 0; col < matrix[0].length; col++) {
                    if (matrix[row][col] == '1') {
                        heights[col] += 1;
                    } else {
                        heights[col] = 0;
                    }
                }
                //调用上一题的解法，更新函数
                maxArea = Math.max(maxArea, largestRectangleArea(heights));
            }
            return maxArea;
        }

        public int largestRectangleArea(int[] heights) {
            if (heights.length == 0) {
                return 0;
            }
            int[] leftLessMin = new int[heights.length];
            leftLessMin[0] = -1;
            for (int i = 1; i < heights.length; i++) {
                int l = i - 1;
                while (l >= 0 && heights[l] >= heights[i]) {
                    l = leftLessMin[l];
                }
                leftLessMin[i] = l;
            }

            int[] rightLessMin = new int[heights.length];
            rightLessMin[heights.length - 1] = heights.length;
            for (int i = heights.length - 2; i >= 0; i--) {
                int r = i + 1;
                while (r <= heights.length - 1 && heights[r] >= heights[i]) {
                    r = rightLessMin[r];
                }
                rightLessMin[i] = r;
            }
            int maxArea = 0;
            for (int i = 0; i < heights.length; i++) {
                int area = (rightLessMin[i] - leftLessMin[i] - 1) * heights[i];
                maxArea = Math.max(area, maxArea);
            }
            return maxArea;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0) return 0;
            int n = matrix[0].length;
            int res = 0;
            int[] height = new int[n];
            int[] left = new int[n];
            int[] right = new int[n];
            Arrays.fill(right, n);

            for (char[] chars : matrix) {
                int curLeft = 0, curRight = n;

                for (int j = 0; j < n; j++) {
                    if (chars[j] == '1') height[j]++;
                    else height[j] = 0;
                }

                for (int j = 0; j < n; j++) {
                    if (chars[j] == '1') {
                        left[j] = Math.max(curLeft, left[j]);
                    } else {
                        left[j] = 0;
                        curLeft = j + 1;
                    }
                }

                for (int j = n - 1; j >= 0; j--) {
                    if (chars[j] == '1') {
                        right[j] = Math.min(curRight, right[j]);
                    } else {
                        right[j] = n;
                        curRight = j;
                    }
                }

                for (int j = 0; j < n; j++) {
                    res = Math.max(res, height[j] * (right[j] - left[j]));
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
