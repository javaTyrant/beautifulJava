package dayPlan;

import treenode.Tree;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2021/3/3
 */
public class Day8 implements Tree {
    public static void main(String[] args) {
        Day8 day8 = new Day8();
        int[] arr = {6, 3, 2, 4, 1, 5, 7};
        System.out.println(day8.isPalindrome(1221));
        System.out.println(day8.findKthLargest(arr, 5));
    }

    //剑指 Offer 40. 最小的k个数
    public int[] getLeastNumbers(int[] arr, int k) {
        //二分
        int low = 0, high = arr.length - 1;
        //
        while (low < high) {
            //
            int i = partition(arr, low, high);
            if (i == k) {
                return Arrays.copyOf(arr, k);
            }
            //high和low
            if (i > k) high = i - 1;
            //
            if (i < k) low = i + 1;
        }
        //copy of
        return Arrays.copyOf(arr, k);
    }

    //正统的partition
    int partition(int[] nums, int low, int high) {
        //选择low
        int pivot = nums[low];
        while (low < high) {
            //大于则--
            while (low < high && nums[high] >= pivot)
                high--;
            //交换一次
            nums[low] = nums[high];
            //小于则++
            while (low < high && nums[low] <= pivot)
                low++;
            //交换一次
            nums[high] = nums[low];
        }
        //再交换一次
        nums[low] = pivot;
        return low;
    }

    //数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, k);
    }

    static int quickSort(int[] nums, int l, int r, int k) {
        //base case
        if (l >= r) return nums[l];
        // -1 + 1
        int i = l - 1, j = r + 1;
        j = partition(nums, nums[(l + r) / 2], i, j);
        //放弃哪一部分,应该的节点在哪个位置
        if (r - j >= k) return quickSort(nums, j + 1, r, k);
        return quickSort(nums, l, j, k - (r - j));
    }

    private static int partition(int[] nums, int mid, int i, int j) {
        while (i < j) {
            do {
                i++;
            } while (nums[i] < mid);
            do {
                j--;
            } while (nums[j] > mid);
            //直接交换
            if (i < j) swap(nums, i, j);
        }
        return j;
    }

    static void swap(int[] nums, int l, int r) {
        int tmp = nums[l];
        nums[l] = nums[r];
        nums[r] = tmp;
    }

    //二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //
        if (root == null || p == root || q == root) return root;
        //
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //
        if (left != null && right != null) {
            return root;
        }
        //
        return left == null ? right : left;
    }

    //二叉搜索树的最近公共祖先
    public TreeNode lowestSearchCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val == p.val) return p;
        if (root.val == q.val) return q;
        if (q.val > root.val && p.val > root.val) {
            return lowestSearchCommonAncestor(root.right, p, q);
        } else if (q.val < root.val && p.val < root.val) {
            return lowestSearchCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

    //9. 回文数
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        //反转整数
        int revert = 0;
        while (x > revert) {
            revert = revert * 10 + x % 10;
            x /= 10;
        }
        //奇数位,不影响,/10
        return x == revert || x == revert / 10;
    }

    //79. 单词搜索
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
        //base case
        if (k >= word.length()) return true;
        if (i < 0
                || i >= board.length
                || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(k))
            return false;
        board[i][j] += 256;
        //k要累加
        boolean result = search(board, word, i - 1, j, k + 1)
                || search(board, word, i + 1, j, k + 1)
                || search(board, word, i, j - 1, k + 1)
                || search(board, word, i, j + 1, k + 1);
        board[i][j] -= 256;
        return result;
    }


}
