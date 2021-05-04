package design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lumac
 * @since 2020/6/30
 */
public class MyStack {
    //用队列实现栈
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    //维护一个栈顶元素
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
        q1.add(x);
        top = x;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        int t = q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
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
