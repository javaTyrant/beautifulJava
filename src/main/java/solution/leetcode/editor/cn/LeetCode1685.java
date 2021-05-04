package solution.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode1685 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            int[] arr = {1, 4, 6, 8, 10};
            System.out.println(Arrays.toString(getSumAbsoluteDifferences(arr)));
        }
        //自己推理一遍
        public static int[] getSumAbsoluteDifferences(int[] nums) {
            int n = nums.length;
            //结果
            int[] res = new int[n];
            //构造前缀和数组
            int[] front = new int[n + 1];
            for (int i = 1; i <= nums.length; ++i) {
                front[i] = front[i - 1] + nums[i - 1];
            }
            //
            for (int i = 1; i <= n; ++i) {
                //左右两次计算,左边比当前的小,所以-右边比当前的大,所以是被减
                int left = i * nums[i - 1] - front[i];
                //
                int right = front[n] - front[i] - (n - i) * nums[i - 1];
                res[i - 1] = left + right;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
