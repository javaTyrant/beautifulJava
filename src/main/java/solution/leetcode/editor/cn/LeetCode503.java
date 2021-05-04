package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Stack;

public class LeetCode503 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //单调栈
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Arrays.fill(res, -1);
            Stack<Integer> stack = new Stack<>();
            //循环数组,所以n * 2
            for (int i = 0; i < n * 2; i++) {
                //真实的数字
                int num = nums[i % n];
                //如果当前的数比栈顶的要大,num就是下一个元素
                while (!stack.isEmpty() && num > nums[stack.peek()]) {
                    //赋值k
                    res[stack.pop()] = num;
                }
                //否则入栈
                if (i < n) stack.add(i);
            }
            return res;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {3, 8, 4, 1, 2};
            System.out.println(Arrays.toString(solution.nextGreaterElements(arr)));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
