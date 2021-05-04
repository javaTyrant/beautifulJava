package design;

import java.util.Queue;
import java.util.Stack;

/**
 * @author lumac
 * @since 2020/6/30
 */
public class CQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public CQueue() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    //插入
    public void appendTail(int value) {
        stack1.push(value);
    }
    //删除
    public int deleteHead() {
        //如果stack2还是空的,如果stack2不为空,那么我们就一直删除stack2
        if (stack2.size() <= 0) {
            //如果stack1也是空的,说明都是空的了
            if (stack1.size() <= 0) return -1;
            //否则
            for (int i = stack1.size(); i > 0; i--) {
                //
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }
}
