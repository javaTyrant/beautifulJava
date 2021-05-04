import linklist.Link;

public class LeetCode141 {
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
        public boolean hasCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && slow != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (slow == fast) return true;
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
