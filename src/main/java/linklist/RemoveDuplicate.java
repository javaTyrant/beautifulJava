package linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lumac
 * @since 2020/6/26
 */
public class RemoveDuplicate implements Link {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        //新建一个hashset
        Set<Integer> set = new HashSet<>();
        //res
        ListNode dummyHead = new ListNode(-1);
        //head赋值
        dummyHead.next = head;
        //指针
        ListNode pre = dummyHead;
        //1, 1, 2, 1, 2, 3, 3
        while (pre.next != null) {
            //如果集合不包含
            if (!set.contains(pre.next.val)) {
                //加进去
                set.add(pre.next.val);
                //指针后移
                pre = pre.next;
            } else {
                //重复,删除重复的数据
                pre.next = pre.next.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        RemoveDuplicate removeDuplicate = new RemoveDuplicate();
        int[] arr = {1, 1, 2, 1, 2, 3, 3};
        ListNode node = removeDuplicate.arrayToListNode(arr);
        ListNode nodes = removeDuplicate.removeDuplicateNodes(node);
        removeDuplicate.printNode(nodes);
    }
}
