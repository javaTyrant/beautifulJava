package netty.current;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 非阻塞的栈
 * @author lumac
 * @since 2020/6/8
 */
public class MyConcurrentStack<T> {
    private AtomicReference<Node> head = new AtomicReference<>();

    public MyConcurrentStack() {
    }

    public void push(T t) {
        if (t == null) {
            return;
        }
        Node<T> n = new Node<T>(t);
        Node<T> current;

        do {
            current = head.get();
            n.setNext(current);
        } while (!head.compareAndSet(current, n));
    }

    public T pop() {
        Node<T> currentHead;
        Node<T> futureHead;
        do {
            currentHead = head.get();
            if (currentHead == null) {
                return null;
            }
            futureHead = currentHead.next;
        } while (!head.compareAndSet(currentHead, futureHead));

        return currentHead.data;
    }

    /**
     * @return null if no element present else return a element. it does not
     * remove the element from the stack.
     */
    public T peek() {
        Node<T> n = head.get();
        if (n == null) {
            return null;
        } else {
            return n.data;
        }
    }

    public boolean isEmpty() {
        if (head.get() == null) {
            return true;
        }
        return false;
    }

    private static class Node<T> {

        private final T data;
        private Node<T> next;

        private Node(T data) {
            this.data = data;
        }

        private void setNext(Node next) {
            this.next = next;
        }
    }
}
