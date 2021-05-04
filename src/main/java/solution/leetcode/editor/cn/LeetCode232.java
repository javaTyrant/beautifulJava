package solution.leetcode.editor.cn;

import java.util.Stack;

public class LeetCode232 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class MyQueue {
        /*
            思路
            push的时候往s1里push
            pop的时候,
            -如果s2为空:
                如果s1不为空,把s1转移到s2
            -如果s2不为空:
                直接取s2的栈顶

         */
        Stack<Integer> s1;
        Stack<Integer> s2;

        public MyQueue() {
            s1 = new Stack();
            s2 = new Stack();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            s1.push(x);
        }

        /**
         * 只要弹出s2就行了
         */
        public int pop() {
            //调用一次peek
            int val = peek();
            s2.pop();
            return val;
        }

        /**
         * s2不为空
         */
        public int peek() {
            //如果s2不为空
            if (s2.isEmpty()) {
                //s1不为空
                while (!s1.isEmpty()) {
                    //转移到s2中
                    s2.push(s1.pop());
                }
            }
            //
            return s2.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return s1.size() + s2.size() == 0;
        }
    }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
