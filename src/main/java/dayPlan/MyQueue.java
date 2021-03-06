package dayPlan;

import java.util.Stack;

/**
 * @author lumac
 * @since 2021/3/5
 */
public class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        s1.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        int val = peek();
        s2.pop();
        return val;
    }

    /**
     * Get the front element.
     */
    public int peek() {
        //s2是空的话才转移,不然一直s2.peek
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return s1.size() + s2.size() == 0;
    }
}
