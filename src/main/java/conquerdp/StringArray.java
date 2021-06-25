package conquerdp;

import treenode.Tree;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/6/22
 **/
public class StringArray implements Tree {


    //
    private final List<String> list = new ArrayList<>();

    public String[] permutation(String s) {
        perm(s.toCharArray(), 0, s.length() - 1);
        //list转数组
        return list.toArray(new String[0]);
    }

    private void perm(char[] seq, int curPos, int n) {
        //base case
        if (curPos == n) {
            list.add(new String(seq));
            return;
        }
        //
        for (int i = curPos; i <= n; i++) {
            // 判断第i个元素是否在seq[curPos,i－1]中出现过,如果出现过就不用交换了
            if (!findSame(seq, curPos, i)) {
                swap(seq, curPos, i);
                perm(seq, curPos + 1, n);
                swap(seq, i, curPos);
            }
        }
    }

    private boolean findSame(char[] seq, int from, int candidate) {
        for (int j = from; j < candidate; j++) {
            if (seq[j] == seq[candidate]) {
                return true;
            }
        }
        return false;
    }

    private void swap(char[] seq, int i, int j) {
        char tmp = seq[i];
        seq[i] = seq[j];
        seq[j] = tmp;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static int[] findNumbersWithSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int com = target - nums[i];
            if (map.containsKey(com)) {
                return new int[]{nums[map.get(com)], nums[i]};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] ss = s.trim().split(" ");
        for (int i = ss.length - 1; i >= 0; i--) {
            if (ss[i].equals("")) {
                continue;
            }
            sb.append(ss[i]);
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public String reverseLeftWords(String s, int n) {
        if (s == null || s.length() == 0) return s;
        if (n > s.length()) return s;
        //
        String pre = s.substring(0, n);
        String last = s.substring(n);
        return last + pre;
    }

    //平衡二叉树
    boolean flag = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        dfs(root);
        return flag;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        // 左右递归
        // 左子树的深度
        int leftDepth = dfs(root.left);
        // 右子树的深度
        int rightDepth = dfs(root.right);
        // 检查该结点所在子树是否平衡
        if (Math.abs(leftDepth - rightDepth) > 1) flag = false;
        //返回打的值加1
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 和为S的连续正数序列 很精妙的一题.
    //输入：target = 15
    //输出：[[1,2,3,4,5],[4,5,6],[7,8]]
    public static int[][] findContinuousSequence(int target) {
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 1; i <= target / 2; i++) {
            int sum = 0;
            //需要一个j来记录加了多少次
            int j = i;
            while (sum < target) {
                sum += j;
                j++;
            }
            //如果sum == target
            if (sum == target) {
                //相等了.new一个新数组
                int[] sol = new int[j - i];
                //
                for (int k = 0; k < j - i; k++) {
                    //k+i
                    sol[k] = k + i;
                }
                res.add(sol);
            }
        }
        //list转二维数组.
        return res.toArray(new int[res.size()][]);
    }

    //剑指 Offer 38. 字符串的排列
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(findContinuousSequence(15)));
        StringArray solution = new StringArray();
        System.out.println(Arrays.toString(solution.permutation("abc")));
        int[] nums = {2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(findNumbersWithSum(nums, 9)));
        System.out.println(solution.reverseLeftWords("abcdefg", 2));
        int[] arr1 = {1, 21, 1, 1, 3, 3, 3};
        System.out.println(singleNumber2(arr1));
        System.out.println(removeDuplicates("abbaca"));
    }

    //给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
    public int[] singleNumbers(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        int flag = sum & (-sum);
        int res = 0;
        for (int num : nums) {
            if ((flag & num) != 0) {
                res ^= num;
            }
        }
        return new int[]{res, res ^ sum};
    }

    //除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
    //数字电路设计
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            a = (a ^ num) & ~b;
            b = (b ^ num) & ~a;
        }
        return a;
    }

    //依次确定每一个二进制位.搞定.
    public static int singleNumber2(int[] nums) {
        int ans = 0;
        //从0开始
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            for (int num : nums) {
                //累加第i位1出现的次数 右移i位 & 1
                total += ((num >> i) & 1);
            }
            //
            if (total % 3 != 0) {
                //
                System.out.println("res:" + (1 << i));
                // 0001
                // 0100
                // 1000 为什么要是|=
                ans |= (1 << i);
            }
        }
        //
        return ans;
    }

    //汉明距离
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            //
            n = n & (n - 1);
        }
        return count;
    }

    //输入："abbaca"
    //输出："ca"
    public static String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();
        //初始值是-1
        int top = -1;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                //
                stack.deleteCharAt(top);
                --top;
            } else {
                //
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }
}
