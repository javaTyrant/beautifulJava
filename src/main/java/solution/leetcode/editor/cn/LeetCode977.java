package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode977 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortedSquares(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                nums[i] = nums[i] * nums[i];
            }
            Arrays.sort(nums);
            return nums;
        }

        //[-4,-1,0,3,10]
        //牛逼啊
        public int[] sortedSquaresOpt(int[] A) {
            int N = A.length;
            //找到j>=0的数字,控制大于0的情况
            int j = 0;
            while (j < N && A[j] < 0)
                j++;
            //i控制小于0的情况
            int i = j - 1;
            //ans
            int[] ans = new int[N];
            //t,存放结果的索引
            int t = 0;

            while (i >= 0 && j < N) {
                if (A[i] * A[i] < A[j] * A[j]) {
                    //放入i
                    ans[t++] = A[i] * A[i];
                    //i--
                    i--;
                } else {
                    //放入j
                    ans[t++] = A[j] * A[j];
                    //j++
                    j++;
                }
            }

            //处理剩余的情况
            while (i >= 0) {
                ans[t++] = A[i] * A[i];
                i--;
            }
            while (j < N) {
                ans[t++] = A[j] * A[j];
                j++;
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
