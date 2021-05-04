package solution.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LeetCode138 {
    //leetcode submit region begin(Prohibit modification and deletion)
// Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return head;
            }
            // map方法，空间复杂度O(n)
            Node node = head;
            // 使用hash表存储旧结点和新结点的映射
            Map<Node, Node> map = new HashMap<>();
            //把node全部放到map里
            while (node != null) {
                Node clone = new Node(node.val);
                map.put(node, clone);
                node = node.next;
            }
            //node再重新指向head
            node = head;
            //再设置
            while (node != null) {
                //设置next和random
                map.get(node).next = map.get(node.next);
                map.get(node).random = map.get(node.random);
                node = node.next;
            }
            //
            return map.get(head);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
