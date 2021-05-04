package solution.leetcode.editor.cn;

import linklist.Link;

public class LeetCode147 {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution implements Link {
        public ListNode insertionSortList(ListNode head) {
            if (head == null) {
                return null;
            }
            return sort(head);
        }

        private ListNode sort(ListNode head) {
            if (head.next == null) {
                return head;
            }
            ListNode mid = findMid(head);
            head = sort(head);
            mid = sort(mid);
            return merge(head, mid);
        }

        private ListNode merge(ListNode node1, ListNode node2) {
            ListNode dummy = new ListNode(-1);
            ListNode cursor = dummy;
            while (node1 != null && node2 != null) {
                if (node1.val > node2.val) {
                    cursor.next = node2;
                    node2 = node2.next;
                } else {
                    cursor.next = node1;
                    node1 = node1.next;
                }
                cursor = cursor.next;
            }
            if (node1 != null) {
                cursor.next = node1;
            }
            if (node2 != null) {
                cursor.next = node2;
            }
            return dummy.next;
        }

        private ListNode findMid(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode cursor1 = head;
            ListNode cursor2 = head;
            while (cursor2.next != null && cursor2.next.next != null) {
                cursor1 = cursor1.next;
                cursor2 = cursor2.next.next;
            }
            ListNode ans = cursor1.next;
            cursor1.next = null;
            return ans;
        }

        public ListNode insertionSortListEasy(ListNode head) {
            if (head == null)
                return head;
            ListNode newHead = new ListNode(0);
            ListNode cur = head, pre = newHead, next;
            while (cur != null) {
                next = cur.next;
                if (pre != newHead && pre.val > cur.val)
                    pre = newHead;
                while (pre.next != null && pre.next.val < cur.val)
                    pre = pre.next;
                cur.next = pre.next;
                pre.next = cur;
                cur = next;
            }
            return newHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
