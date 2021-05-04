package solution.leetcode.editor.cn;

import linklist.Link;

public class LeetCode19 {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    static class Solution implements Link {
        public static void main(String[] args) {
            Solution so = new Solution();
            int[] arr = {1, 2, 3, 4, 5};
            ListNode listNode = so.arrayToListNode(arr);
            so.removeNthFromEnd(listNode, 2);
        }

        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (n == 0) return head;
            //复制一个
            ListNode dummy = new ListNode(0);
            dummy.next = head;

            ListNode pre = dummy, cur = dummy;
            //cur先走n步
            while (n >= 0 && cur != null) {
                cur = cur.next;
                n--;
            }
            //双指针同时走:pre会落到倒数第n个位置
            while (cur != null) {
                pre = pre.next;
                cur = cur.next;
            }
            //删除倒数第n个节点
            pre.next = pre.next.next;
            //返回dummy
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
