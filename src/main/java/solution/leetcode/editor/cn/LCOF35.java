package solution.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2020/12/17
 */
public class LCOF35 {
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

    //复杂链表的复制
    //推荐双百方法一：指针版  需要一个辅助的map，下面还有一个调用方法的版本，不需要额外创建指针

    //map前面保存原链表节点，后面保存复制节点，则可通过前面的key直接找到后面的value
    Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node res = new Node(head.val);
        map.put(head, res);
        Node tmpR = res;
        Node tmpH = head;
        while (tmpH.next != null) {
            tmpR.next = new Node(tmpH.next.val);
            tmpR = tmpR.next;
            tmpH = tmpH.next;
            map.put(tmpH, tmpR);
        }
        tmpR = res;
        tmpH = head;
        while (tmpH != null) {
            if (tmpH.random != null) tmpR.random = map.get(tmpH.random);
            tmpH = tmpH.next;
            tmpR = tmpR.next;
        }
        return res;
    }
}
