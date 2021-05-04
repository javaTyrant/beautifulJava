package design;

import java.util.HashMap;

/**
 * @author lumac
 * @since 2020-05-25
 */
public class Lru {
    //缓存减少链表遍历的时间
    private final HashMap<Integer, Node> cache = new HashMap<>();
    //size
    private int size;
    //capacity
    private final int capacity;
    //head
    private final Node head;
    //tail
    private final Node tail;

    //链表节点
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    /**
     * 构造器
     * 刚开始的时候head和tail是互相指向的
     *
     * @param capacity 容量
     */
    public Lru(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        //头尾设置
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 对外暴露的api,get
     * 获取一次,移动到head
     *
     * @param key key
     * @return int
     */
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;

        // move the accessed node to the head;
        moveToHead(node);

        return node.value;
    }

    /**
     * 对外暴露的api,添加
     * 1.缓存中不存在,新建node,移动到head,如果超了,删除尾部
     * 2.缓存存在,更新value,移动到head
     *
     * @param key   key
     * @param value value
     */
    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            addNode(newNode);
            ++size;
            if (size > capacity) {
                // pop the tail
                Node tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            // update the value.
            node.value = value;
            moveToHead(node);
        }
    }

    private void addNode(Node node) {
        /*
          把新节点加入到head的右边
          head -> next
                <-
         */
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        /*
          删除已存在的节点
         */
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(Node node) {
        /*
          把该节点移到head右边
         */
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        /*
          删除尾结点
         */
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    public static void main(String[] args) {
        Lru lru = new Lru(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        System.out.println(lru.get(2));
        System.out.println(lru.get(1));
    }
}
