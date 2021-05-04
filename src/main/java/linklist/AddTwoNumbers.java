package linklist;

/**
 * @author lumac
 * @since 2020/6/24
 */
public class AddTwoNumbers implements Link {
    //Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    //Output: 7 -> 0 -> 8
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        //需要几个指针呢? 不要忘了dummy的指针
        ListNode p = l1, q = l2, cur = dummy;
        //所以carry是必须放外面的
        int carry = 0;
        while (p != null || q != null) {
            int x = (p == null) ? 0 : p.val;
            int y = (q == null) ? 0 : q.val;
            int sum = carry + x + y;
            //carry / 10
            carry = sum / 10;
            //值取余
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            //判空
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        //最后才判断异常carry
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //1->8->9  2->9->9 ∫
    public ListNode addTwoNumbersOwn(ListNode l1, ListNode l2) {
        //做链表的题一定要搞清楚需要几个指针
        //1.需要一个结果链表
        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null || p2 != null) {
            int a = p1 == null ? 0 : p1.val;
            int b = p2 == null ? 0 : p2.val;
            int sum = a + b + carry;
            carry = sum >= 10 ? sum / 10 : 0;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            p1 = p1.next;
            p2 = p2.next;
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
}
