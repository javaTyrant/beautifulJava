package solution.leetcode.editor.cn;

import linklist.Link;

public class LeetCode86 {
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
        public ListNode partition(ListNode head, int x) {
            ListNode dummyHead1 = new ListNode(0);
            ListNode dummyHead2 = new ListNode(0);
            //两个指针
            ListNode node1 = dummyHead1;
            ListNode node2 = dummyHead2;
            while (head != null) {
                if (head.val < x) {
                    node1.next = head;
                    head = head.next;
                    node1 = node1.next;
                    //防止循环
                    node1.next = null;
                } else {
                    node2.next = head;
                    head = head.next;
                    node2 = node2.next;
                    node2.next = null;
                }
            }
            node1.next = dummyHead2.next;
            return dummyHead1.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
