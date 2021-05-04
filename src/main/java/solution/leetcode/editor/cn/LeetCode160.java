package solution.leetcode.editor.cn;

import linklist.Link;

import java.util.Arrays;

public class LeetCode160 {
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution implements Link {
        //编写一个程序，找到两个单链表相交的起始节点。
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int[] arr = {1, 2, 3};

            /**
             定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一轮移动中恰好抹除了长度差)
             两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两条指针的长度
             **/
            if (headA == null || headB == null) return null;
            ListNode pA = headA, pB = headB;
            // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
            while (pA != pB) {
                pA = pA == null ? headB : pA.next;
                pB = pB == null ? headA : pB.next;
            }
            return pA;
        }

        public int[] mostCompetitive(int[] nums, int k) {
            int[] result = Arrays.copyOfRange(nums, nums.length - k, nums.length);
            boolean incre = true;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[i - 1]) {
                    incre = false;
                    break;
                }
            }
            if (incre) {
                return Arrays.copyOfRange(nums, 0, k);
            }
            for (int i = nums.length - k - 1; i >= 0; i--) {
                int j = 0;
                if (nums[i] <= result[0]) {
                    int pre = result[j];
                    result[0] = nums[i];
                    while (++j < result.length) {
                        if (result[j] < pre) {
                            j = nums.length;
                            break;
                        }
                        int temp = pre;
                        pre = result[j];
                        result[j] = temp;
                    }
                }
                // System.out.println(Arrays.toString(result));
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
