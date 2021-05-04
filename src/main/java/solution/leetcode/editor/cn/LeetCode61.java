package solution.leetcode.editor.cn;

import linklist.Link;

public class LeetCode61 {
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
        //1.计算链表的总长度
        //2.获取循环的次数
        //3.找到头结点
        //4.打断
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || k == 0) {
                return head;
            }
            //两个指针,cursor,tail
            ListNode cursor = head;
            ListNode tail;//尾指针
            //
            int length = 1;
            //循环 得到总长度
            while (cursor.next != null) {
                cursor = cursor.next;
                length++;
            }
            //原理
            int loop = length - (k % length);//得到循环的次数
            //
            tail = cursor;//指向尾结点
            cursor.next = head;//改成循环链表
            cursor = head;//指向头结点
            //开始循环,找到头结点
            for (int i = 0; i < loop; i++) {
                cursor = cursor.next;
                tail = tail.next;
            }
            //打断
            tail.next = null;//改成单链表
            return cursor;//返回当前头
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            int[] arr = {1, 2, 3, 4, 5};
            ListNode node = solution.arrayToListNode(arr);
            ListNode res = solution.rotateRight(node, 2);
            solution.printNode(res);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
