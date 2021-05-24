package conquerdp.offer;

import treenode.Tree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author lufengxiang
 * @since 2021/5/22
 **/
public class P implements Tree {
    //输入：[3,4,5,1,2]
    //输出：1
    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (numbers[r] > numbers[mid]) {
                r = mid;
            } else if (numbers[r] < numbers[mid]) {
                //mid处可以放弃的
                l = mid + 1;
            } else r--;
        }
        return numbers[l];
    }

    public static boolean xorGame(int[] nums) {
        if (nums.length % 2 == 0) {
            return true;
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor == 0;
    }

    public static boolean xorGame1(int[] nums) {
        return false;
    }

    public TreeNode inorderSuccessor(TreeNode p) {
        return null;
    }

    public static int fib(int n) {
        int a = 0, b = 1;
        if (n == a) return a;
        if (n == b) return b;
        int temp = 0;
        for (int i = 2; i <= n; i++) {
            temp = b;
            b += a;
            a = temp;
        }
        return b;
    }

    public static int fib2(int n) {
        if (n <= 1)
            return n;
        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 2; i <= n; i++) {
            c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(fib(0));
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
        int[] arr = {1, 2, 3, 4, -2, 4, 5};
        System.out.println(maxProduct(arr));
    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean search(char[][] board, String word, int i, int j, int k) {
        if (k >= word.length()) return true;
        if (i < 0
                || i >= board.length
                || j < 0
                || j >= board[0].length
                || board[i][j] != word.charAt(k))
            return false;
        board[i][j] += 256;
        boolean result = search(board, word, i - 1, j, k + 1)
                || search(board, word, i + 1, j, k + 1)
                || search(board, word, i, j - 1, k + 1)
                || search(board, word, i, j + 1, k + 1);
        board[i][j] -= 256;
        return result;
    }

    //152. 乘积最大子数组
    public static int maxProduct(int[] nums) {
        //一个结果 一个保存最大的，一个保存最小的。
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int num : nums) {
            //如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
            if (num < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * num, num);
            imin = Math.min(imin * num, num);
            max = Math.max(max, imax);
        }
        return max;
    }
}
