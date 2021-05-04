package solution.leetcode.editor.cn;

import linklist.Link;

public class LeetCode25 {
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
        public static ListNode reverseKGroup(ListNode head, int k) {
            int count = 0;
            ListNode curr = head;
            //走到第一个k的位置
            while (curr != null && k != count) {
                curr = curr.next;
                count++;
            }
            //k == count
            if (k == count) {
                //递归调用
                curr = reverseKGroup(curr, k);
                //count不等于0,翻转一次
                while (count != 0) {
                    count--;//下面是翻转链表的经典实现
                    ListNode next = head.next;
                    head.next = curr;
                    curr = head;
                    head = next;
                }
                //head指向curr
                head = curr;
            }
            return head;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {1, 2, 3, 4, 5, 6};
            ListNode node = solution.arrayToListNode(arr);
            ListNode resNode = reverseKGroup(node, 2);
            solution.printNode(resNode);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
