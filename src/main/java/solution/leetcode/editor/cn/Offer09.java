package solution.leetcode.editor.cn;

import java.util.Stack;

public class Offer09 {

    //leetcode submit region begin(Prohibit modification and deletion)
    class CQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack2.size() <= 0) {
                if (stack1.size() <= 0) return -1;
                int size = stack1.size();
                for (int i = 0; i < size; i++) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
//leetcode submit region end(Prohibit modification and deletion)

    }
}
