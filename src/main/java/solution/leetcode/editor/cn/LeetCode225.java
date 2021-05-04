package solution.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode225 {
    //leetcode submit region begin(Prohibit modification and deletion)
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    //核心思想维护一个top记录最后的值,然后就是栈顶,插入O(1),pop O(n)
    static class MyStack {
        //用队列实现栈
        private Queue<Integer> q1 = new LinkedList<>();
        private Queue<Integer> q2 = new LinkedList<>();
        private int top;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {

        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            //只往q1里添加
            q1.add(x);
            //更新top
            top = x;
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            //如果q1不为空
            while (q1.size() > 1) {
                //取出top
                top = q1.remove();
                //放入q2
                q2.add(top);
            }
            //top
            int t = q1.remove();
            //交换q1,q2
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            //返回t
            return t;
        }

        /**
         * Get the top element.
         */
        public int top() {
            return top;
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return q1.size() == 0;
        }
    }

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
