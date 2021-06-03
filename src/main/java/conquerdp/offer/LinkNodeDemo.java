package conquerdp.offer;

import linklist.Link;
import treenode.Tree;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/6/2
 **/
public class LinkNodeDemo implements Link, Tree {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            l1 = l1.next;
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            l2 = l2.next;
            return l2;
        }
    }

    //剑指 Offer 26. 树的子结构.好难想!!.
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null) return false;
        return A.val == B.val && dfs(A.left, B.left) && dfs(A.right, B.right);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        return root1.val == root2.val && helper(root1.left, root2.right) &&
                helper(root1.right, root2.left);
    }

    //pushed = [1,2,3,4,5], popped = [4,5,3,2,1] 好题.
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int elem : pushed) {
            //先push.
            stack.push(elem);
            //小于len 栈不为空 peek == pop
            while (j < popped.length && !stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return j == popped.length;
    }

    public static void main(String[] args) {
        int[] p = {1, 2, 3, 4, 5}, q = {4, 5, 3, 2, 1};
        System.out.println(validateStackSequences(p, q));
        int[] arr = {1, 1, 0, 0, 0, 1, 1, 1};
        System.out.println(findMaxLength(arr));
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    //复杂链表的复制
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Map<Node, Node> map = new HashMap<>();
        //p指向head.
        Node p = head;
        //
        while (p != null) {
            //clone一个new node
            Node clone = new Node(p.val);
            //p对应的clone建立映射
            map.put(p, clone);
            //往下走
            p = p.next;
        }
        p = head;
        //
        while (p != null) {
            //
            map.get(p).next = map.get(p.next);
            //
            map.get(p).random = map.get(p.random);
            //往下走.
            p = p.next;
        }
        return map.get(head);
    }

    //扑克牌中的顺子.思路牛逼.
    public boolean isStraight(int[] nums) {
        //hash表
        int[] cards = new int[14];
        int max = 0, min = 14;

        for (int num : nums) {
            if (num == 0) continue;
            //重复情况判断
            if (cards[num]++ > 0) return false;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return max - min < 5;
    }

    //顺时针打印矩阵.
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int u = 0;
        int d = matrix.length - 1;
        int l = 0;
        int r = matrix[0].length - 1;
        while (u <= d && l <= r) {
            for (int i = l; i <= r; i++) {
                res.add(matrix[u][i]);
            }
            u++;
            for (int i = u; i <= d; i++) {
                res.add(matrix[i][r]);
            }
            r--;
            for (int i = r; i >= l && u <= d; i--) {
                res.add(matrix[d][i]);
            }
            d--;
            for (int i = d; i >= u && l <= r; i--) {
                res.add(matrix[i][l]);
            }
            l++;
        }
        return res;
    }
    //相同数量的最大长度.好题.
    public static int findMaxLength(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }
}
