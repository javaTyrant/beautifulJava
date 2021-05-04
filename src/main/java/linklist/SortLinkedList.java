package linklist;

/**
 * @author lumac
 * @since 2020/12/2
 */
public class SortLinkedList implements Link {
    public static void main(String[] args) {
        SortLinkedList sort = new SortLinkedList();
        int[] arr = {2, 3, 1, 4, 5};
        ListNode node = sort.arrayToListNode(arr);
        ListNode sortList = sort.sortList(node);
        sort.printNode(sortList);
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // pre的作用分割链表,不然链表会形成环?
        // 使用两个指针，一快一慢，当快指针走到链表尾部时，慢指针正好到中间位置。
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //prev表示第一个链表的尾部，所以指向空。slow为第二个指针的头部
        prev.next = null;

        // 继续分裂
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // 当分裂到最后每个元素时，开始两两合并
        return merge(l1, l2);
    }

    //将两个有序链表合并的函数
    ListNode merge(ListNode l1, ListNode l2) {
        //dummy node 和dummy node的指针
        ListNode l = new ListNode(0), p = l;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            //指针
            p = p.next;
        }
        //还有一个可能不为空
        if (l1 != null)
            p.next = l1;

        if (l2 != null)
            p.next = l2;

        return l.next;
    }
}
