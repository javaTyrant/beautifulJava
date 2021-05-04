package linklist;

/**
 * @author lumac
 * @since 2020/6/24
 */
public class ReverseList implements Link {
    //迭代
    public ListNode reverseListIter(ListNode head) {
        //保存前面的
        ListNode prev = null;
        //指针
        ListNode curr = head;
        while (curr != null) {
            //保存一个临时节点
            ListNode nextTemp = curr.next;
            //打断
            curr.next = prev;
            //保存打断的
            prev = curr;
            //curr恢复
            curr = nextTemp;
        }
        //返回prev
        return prev;
    }

    //反转链表递归
    public static ListNode reverseList(ListNode head) {
        //head
        if (head == null || head.next == null) return head;
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        ReverseList list = new ReverseList();
        int[] arr = {1, 2, 3, 4, 5};
        ListNode node = list.arrayToListNode(arr);
        list.printNode(node);
        ListNode iter = list.reverseList(node);
        list.printNode(iter);
    }
}
