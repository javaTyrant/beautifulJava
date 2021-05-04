package solution.leetcode.editor.cn;

import linklist.Link;

/**
 * @author lumac
 * @since 2020/12/31
 */
public class LCOF52 implements Link {
    //两个链表的第一个公共节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //两个指针
        ListNode p1 = headA, p2 = headB;
        //如果不等,就移动指针
        while (p1 != p2) {
            //边界判断
            p1 = (p1 == null) ? headB : p1.next;
            p2 = (p2 == null) ? headA : p2.next;
        }
        //返回p1
        return p1;
    }
}
