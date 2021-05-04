package solution.leetcode.editor.cn;

import linklist.Link;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2021/1/10
 */
public class NewYear implements Link {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int[] ar1 = {6, 2, 7, 3};
        System.out.println(Arrays.toString(decode(arr, 1)));
        System.out.println(Arrays.toString(decode(ar1, 4)));
        NewYear newYear = new NewYear();
        //64,46,66,35,80
        //[91,56,55,77,91,96,48,53,68]
        //int[] r = {1, 2, 3, 4, 5, 6};
        int[] r1 = {1, 2, 3, 4, 5};
        int[] r = {91, 56, 55, 77, 91, 96, 48, 53, 68};
        ListNode listNode = newYear.arrayToListNode(r);
        ListNode list = newYear.arrayToListNode(r1);
        ListNode listNode1 = newYear.swapNodes(listNode, 7);
        ListNode listNode2 = newYear.swapNodes(list, 2);
        newYear.printNode(listNode1);
        newYear.printNode(listNode2);
    }

    public ListNode swapNodes(ListNode head, int k) {
        int n = 0;
        ListNode c = head;
        while (c != null) {
            n++;
            c = c.next;
        }
        int[] arr = new int[n];
        ListNode b = head;
        int i = 0;
        while (b != null) {
            arr[i++] = b.val;
            b = b.next;
        }
        if (n - k < k - 1) {
            reverse(arr, n - k + 1, k - 1, 1);
        } else {
            reverse(arr, k - 1, n - k, 1);
        }

        ListNode list = new ListNode(-1);
        ListNode cur = list;
        for (int a : arr) {
            cur.next = new ListNode(a);
            cur = cur.next;
        }
        return list.next;
    }

    public void reverse(int[] nums, int start, int end, int k) {
        while (start < end && k > 0) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
            k--;
        }
    }

    public static int[] decode(int[] encoded, int first) {
        int[] res = new int[encoded.length + 1];
        int r = first;
        res[0] = r;
        for (int i = 0; i < encoded.length; i++) {
            r ^= encoded[i];
            res[i + 1] = r;
        }
        return res;
    }
}
