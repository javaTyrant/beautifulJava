package linklist;

/**
 * @author lumac
 * @since 2020/7/13
 */
public class MergeTwoList implements Link {
    public ListNode mergeTwoListsReverse(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //保存结果
        ListNode prehead = new ListNode(-1);
        //指针,指针改变而不会改变prehead
        ListNode prev = prehead;
        //都不为空
        while (l1 != null && l2 != null) {
            //小
            if (l1.val <= l2.val) {
                prev.next = l1;
                //l1指针移动
                l1 = l1.next;
            } else {
                prev.next = l2;
                //l2指针移动
                l2 = l2.next;
            }
            //prev指针移动
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
