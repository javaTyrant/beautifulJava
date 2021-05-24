package design;

import java.util.Stack;

/**
 * @author lumac
 * @since 2020/6/30
 */
public class MyQueue<T> {
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        //直接往栈一添加
        stack1.push(value);
    }

    public int deleteHead() {
        //如果栈2没有元素
        if (stack2.size() <= 0) {
            //如果栈一也没元素了
            if (stack1.size() <= 0) return -1;
            //获取1的大小
            int size = stack1.size();
            //放入stack2
            for (int i = 0; i < size; i++) {
                stack2.push(stack1.pop());
            }
        }
        //从stack2取.
        return stack2.pop();
    }
}
