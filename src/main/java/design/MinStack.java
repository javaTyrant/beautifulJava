package design;

/**
 * @author lumac
 * @since 2020-05-12
 */
public class MinStack {
    class Node {
        private int min;
        private int val;
        private Node next;

        public Node(int val, int min, Node node) {
            this(min, val);
            this.next = node;

        }

        public Node(int min, int val) {
            this.min = min;
            this.val = val;
        }
    }

    private Node node;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

    }

    public void push(int x) {
        if (node == null) {
            node = new Node(x, x);
        } else {
            node = new Node(x, Math.min(x, node.min), node);
        }
    }

    public void pop() {
        node = node.next;
    }

    public int top() {
        return node.val;
    }

    public int min() {
        return node.min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(1);
        minStack.push(-1);
        System.out.println(minStack.min());
        minStack.push(-2);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
    }
}
