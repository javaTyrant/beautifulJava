package design;

/**
 * @author lumac
 * @since 2020/7/28
 */
public class MaxQueue {
    Node head;
    Node tail;
    Node max;

    public MaxQueue() {
        head = tail = max = null;
    }

    public int max_value() {
        if(max == null) return -1;
        return max.val;
    }

    public void push_back(int value) {
        if(head == null)
            head = tail = max = new Node(value);
        else{
            Node node = new Node(value);
            tail.next = node;
            tail = node;

            if(max.val <= node.val)
                max = node;
        }
    }

    public int pop_front() {
        if(head == null) return -1;
        Node node = head.next;
        if(head == max){
            max = node;
            while(node != null){
                if(node.val >= max.val)
                    max = node;
                node = node.next;
            }
        }
        node = head;
        head = head.next;
        return node.val;
    }

    class Node{
        int val;
        Node next;

        Node(int val){
            this.val = val;
        }
    }
}
