package design;

import java.util.Stack;

/**
 * @author lumac
 * @since 2020/6/30
 */
public class MyQueue<T> {
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    /**
     * 队列是先进先出的
     * 栈是先进后出的
     * 栈1负责插入
     */
    public void appendTail(T t) {
        stack1.add(t);
    }

    public T popHead() {
        //如何删除呢
        //如果stack1不为空
        if (stack1.size() > 0) {

        }
        return stack2.pop();
    }
}
