package solution.leetcode.editor.cn;

import linklist.Link;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode23 {
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
        //合并k个有序链表
        public static ListNode mergeKLists(ListNode[] lists) {
            //步长
            int interval = 1;
            while (interval < lists.length) {
                //太妙了吧,len - interval ;i += interval * 2;2倍
                for (int i = 0; i < lists.length - interval; i += interval * 2) {
                    lists[i] = merge2Lists(lists[i], lists[i + interval]);
                }
                //不断*2
                interval *= 2;
            }
            return lists.length > 0 ? lists[0] : null;
        }

        //合并两个链表
        public static ListNode merge2Lists(ListNode l1, ListNode l2) {
            //l1为null返回l2
            //l2为null返回l1
            if (l1 == null)
                return l2;
            if (l2 == null)
                return l1;
            //小的next等于大的next
            if (l1.val < l2.val) {
                l1.next = merge2Lists(l1.next, l2);
                //返回l1
                return l1;
            }
            //返回l2
            l2.next = merge2Lists(l1, l2.next);
            return l2;
        }

        private static void test(int len) {
            int interval = 1;
            while (interval < len) {
                //
                for (int i = 0; i < len - interval; i += interval * 2) {
                    System.out.println(i + ":" + (i + interval));
                }
                interval *= 2;
            }
        }

        public ListNode mergeKListsQueue(ListNode[] lists) {
            //优先队列
            PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
            for (ListNode node : lists) {
                if (node != null) {
                    //放入优先队列
                    q.add(node);
                }
            }
            //
            ListNode head = new ListNode(0);
            ListNode tail = head;
            while (!q.isEmpty()) {
                tail.next = q.poll();
                tail = tail.next;
                if (tail.next != null) {
                    q.add(tail.next);
                }
            }
            return head.next;
        }

        public static void main(String[] args) {
            test(10);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
