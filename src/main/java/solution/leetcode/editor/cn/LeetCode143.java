package solution.leetcode.editor.cn;

import linklist.Link;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode143 {
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
            Solution solution = new Solution();
            int[] arr = {1, 2, 3, 4, 5};
            ListNode node = solution.arrayToListNode(arr);
            solution.printNode(node);
            solution.reorderList(node);
            solution.printNode(node);
        }

        //O(N):时空都是
        public void reorderList(ListNode head) {
            //队列
            LinkedList<ListNode> queue = new LinkedList<>();
            ListNode cur = head;
            while (cur != null) {
                queue.addLast(cur);
                cur = cur.next;
            }
            //
            while (!queue.isEmpty()) {
                //首尾操作
                //cur = null取first
                if (cur == null) {
                    cur = queue.pollFirst();
                } else {
                    //cur.next
                    cur.next = queue.pollFirst();
                    cur = cur.next;
                }
                //取next
                cur.next = queue.pollLast();
                cur = cur.next;
            }
            //如果cur!= null
            if (cur != null) {
                cur.next = null;
            }
        }

        //空间O(1)的写法呢:寻找链表中点 + 链表逆序 + 合并链表
        public void reorderList1(ListNode head) {
            if (head == null) {
                return;
            }
            ListNode mid = middleNode(head);
            ListNode l1 = head;
            ListNode l2 = mid.next;
            mid.next = null;
            l2 = reverseList(l2);
            mergeList(l1, l2);
        }

        //双指针找中间节点
        public ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        //反转链表
        public ListNode reverseList(ListNode head) {
            //pre null
            ListNode prev = null;
            //cur -> head
            ListNode curr = head;
            //当curr != null
            while (curr != null) {
                //保存一个temp
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            //返回pre
            return prev;
        }

        public void mergeList(ListNode l1, ListNode l2) {
            ListNode l1_tmp;
            ListNode l2_tmp;
            while (l1 != null && l2 != null) {
                l1_tmp = l1.next;
                l2_tmp = l2.next;

                l1.next = l2;
                l1 = l1_tmp;

                l2.next = l1;
                l2 = l2_tmp;
            }
        }

        //[1,2,3]
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            findAllSubSets(res, nums, new ArrayList<>(), 0);
            return res;
        }

        private void findAllSubSets(List<List<Integer>> res, int[] nums, List<Integer> cur, int index) {
            //深拷贝
            res.add(new ArrayList<>(cur));
            for (int i = index; i < nums.length; i++) {
                cur.add(nums[i]);
                //这边肯定是加i啊,不可能用到初始值index的啊
                findAllSubSets(res, nums, cur, i + 1);
                cur.remove(cur.size() - 1);
            }
        }

        //582. 杀死进程
        private List<Integer> res = new ArrayList<>();

        private int[] parent;

        public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
            if (pid.size() == 0 || ppid.size() == 0) {
                return res;
            }
            this.parent = new int[100000];
            for (int i = 0; i < pid.size(); i++) {
                parent[pid.get(i)] = ppid.get(i);
            }
            for (int i = 0; i < pid.size(); i++) {
                if (find(pid.get(i), kill)) {
                    res.add(pid.get(i));
                }
            }
            return res;

        }

        // find 函数，
        private boolean find(int val, int kill) {
            if (val == kill) {
                return true;
            }
            while (val != parent[val]) {
                if (parent[val] == kill) {
                    return true;
                }
                val = parent[val];
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
