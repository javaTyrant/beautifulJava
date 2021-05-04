package solution.leetcode.editor.cn;

import linklist.Link;

public class LeetCode142 {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution implements Link {
        public ListNode detectCycle(ListNode head) {
            if (head == null) return null;
            ListNode slow = head;
            ListNode fast = head;
            boolean cycle = false;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    cycle = true;
                    break;
                }
            }
            //如果有环
            if (cycle) {
                ListNode first = head;
                while (first != slow) {
                    slow = slow.next;
                    first = first.next;
                }
                return first;
            }
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
