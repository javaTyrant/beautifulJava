package solution.leetcode.editor.cn;

import java.util.Stack;

public class LeetCode946 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            int[] pushed = {1, 2, 3, 4, 5}, popped = {4, 5, 3, 2, 1};
            Solution solution = new Solution();
            System.out.println(solution.validateStackSequences1(pushed, popped));
        }

        //给定pushed和popped两个序列，每个序列中的 值都不重复，
        // 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            int N = pushed.length;
            int[] stk = new int[N + 1];
            int top = -1;
            int index = 0;
            for (int i = 0; i < N; ++i) {
                if (top == -1 || stk[top] != popped[i]) {
                    if (index == N) return false;
                    do {
                        stk[++top] = pushed[index++];
                    } while (stk[top] != popped[i] && index < N);
                    if (stk[top] != popped[i]) return false;
                }
                --top;
            }
            return true;
        }

        public static boolean validateStackSequences1(int[] pushed, int[] popped) {
            int N = pushed.length;
            Stack<Integer> stack = new Stack();
            int j = 0;
            for (int x : pushed) {
                stack.push(x);
                while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                    stack.pop();
                    j++;
                }
            }

            return j == N;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
