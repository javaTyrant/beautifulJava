package solution.leetcode.editor.cn;

import linklist.Link;

public class LeetCode328 {
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
    //328. 奇偶链表
    //给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
    //请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
    static class Solution implements Link {
        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {1, 2, 3, 4, 5, 6};
            ListNode listNode = solution.arrayToListNode(arr);
            solution.oddEvenList(listNode);
        }

        //简单,递归怎么做呢
        public ListNode oddEvenList(ListNode head) {
            if (head == null) return head;
            //为什么需要evenHead
            ListNode odd = head, even = head.next, evenhead = even;
            while (odd.next != null && even.next != null) {
                //连接后面的值
                odd.next = odd.next.next;
                even.next = even.next.next;
                //
                odd = odd.next;
                even = even.next;
            }
            //偶数链表放到odd后面
            odd.next = evenhead;
            return head;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
