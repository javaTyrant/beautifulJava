package solution.leetcode.editor.cn;

import linklist.Link;

public class LeetCode203 {
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
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummyHead = new ListNode(0), p = dummyHead;
            dummyHead.next = head;
            while (p.next != null)
                if (p.next.val == val) p.next = p.next.next;
                else p = p.next;
            return dummyHead.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
