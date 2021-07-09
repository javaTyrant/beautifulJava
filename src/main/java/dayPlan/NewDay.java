package dayPlan;

import treenode.Tree;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/6/29
 **/
public class NewDay implements Tree {
    //168. Excel表列名称
    public static String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            //先--
            n--;
            //模26+'A'
            sb.append((char) (n % 26 + 'A'));
            //除26
            n = n / 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(337));
        int[] arr = {1, 2, 3, 4, 5};
        int[] arr1 = {7, 0, 8, 4, 5};
        System.out.println(isContinuous(arr));
        System.out.println(lastRemaining(9, 2));
    }

    //扑克牌中的顺子
    public static boolean isContinuous(int[] nums) {
        int[] cards = new int[14];
        int max = 0, min = 14;

        for (int num : nums) {
            //0
            if (num == 0) continue;
            //重复情况判断
            if (cards[num]++ > 0) return false;
            //最小值,最大值
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        //
        return max - min < 5;
    }

    public static int maxDiff(int[] nums) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n < min) {
                min = n;
            }
            max = Math.max(max, n - min);
        }
        return max;
    }

    //AcWing 82. 圆圈中最后剩下的数字
    public static int lastRemaining(int n, int m) {
        if (n < 1 || m < 1)
            return -1;
        int last = 0;
        //
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //存放当前的一行
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                TreeNode left = cur.left, right = cur.right;
                level.add(cur.val);
                if (left != null) queue.offer(left);
                if (right != null) queue.offer(right);
            }
            res.add(0, level);
        }
        return res;
    }

    //剑指 Offer 32 - I. 从上到下打印二叉树
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            res.add(current.val);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        int[] r = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            r[i] = res.get(i);
        }
        return r;
    }

    //剑指 Offer 32 - III. 从上到下打印二叉树 III
    public List<List<Integer>> levelOrderIII(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return res;
        }
        int count = 0;
        TreeNode end = root;
        queue.offer(root);
        List<Integer> temp = new ArrayList<>();
        while (!queue.isEmpty()) {
            root = queue.poll();
            temp.add(root.val);
            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (root == end) {
                count++;
                end = queue.peekLast();
                if (count % 2 == 1) {
                    res.add(temp);
                } else {
                    Collections.reverse(temp);
                    res.add(temp);
                }
                temp = new ArrayList<>();
            }
        }
        return res;
    }
}
