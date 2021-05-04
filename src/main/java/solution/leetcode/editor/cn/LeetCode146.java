package solution.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LeetCode146 {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.put(4, 4);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    static class LRUCache {

        class Node {
            int key;
            int val;
            Node pre;
            Node next;

            public Node() {

            }

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private int size;
        private int capacity;
        private Node head;
        private Node tail;
        //缓存
        private Map<Integer, Node> cache = new HashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        //把节点移到链表头
        public int get(int key) {
            //先从缓存取
            Node cur = cache.get(key);
            if (cur == null) return -1;
            //删除当前的node
            //移动到链表头
            moveToHead(cur);
            return cur.val;
        }
        //
        public void put(int key, int value) {
            Node cur = cache.get(key);
            if (cur == null) {
                Node node = new Node(key, value);
                cache.put(key, node);
                //插入到头节点,无需删除操作
                addNode(node);
                size++;
                //超出容量大小,注意边界值
                if (size > capacity) {
                    //删除尾节点
                    Node tail = popTail();
                    //删除尾结点的缓存
                    cache.remove(tail.key);
                    size--;
                }
            } else {
                //更新老节点的值
                cur.val = value;
                //更新缓存
                cache.put(key, cur);
                //移动到头结点
                moveToHead(cur);
            }
        }

        //所以我们还需要实现几个方法
        //删除当前的node,都是删除,尾结点
        private void addNode(Node node) {
            //这个顺序可别乱写
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        //
        private void removeNode(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
        }

        //新增链表节点到到链表头,包含删除的操作吧,简化到极致
        private void moveToHead(Node node) {
            removeNode(node);
            addNode(node);
        }

        //删除尾节点,要返回删除的节点,因为我们还要删除缓存
        private Node popTail() {
            Node tail = this.tail.pre;
            removeNode(tail);
            return tail;
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
