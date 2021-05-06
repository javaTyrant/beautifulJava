package conquerdp;

import treenode.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lumac
 * @since 2021/5/5
 */
public class RobHouse implements Tree {
    //你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
    //影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
    //给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    public int rob(int[] nums) {
        //如果数组长度为0返回0
        if (nums.length == 0) return 0;
        //小于等于1返回第一个元素
        if (nums.length <= 1) return nums[0];
        //dp数组
        int[] dp = new int[nums.length];
        //
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            //选最大的.
            dp[i] = Math.max((nums[i] + dp[i - 2]), dp[i - 1]);
        }
        //返回dp
        return dp[nums.length - 1];
    }
    //这题该如何思考呢?

    //213. 打家劫舍 II .首尾是相连的.
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        //两个数组
        int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];
        dp1[1] = nums[0]; //从第1个房屋开始偷
        dp2[1] = nums[1]; //从第2个房屋开始偷
        for (int i = 2; i < nums.length; i++) {
            dp1[i] = Math.max(dp1[i - 2] + nums[i - 1], dp1[i - 1]);
            dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);
        }
        //选最大的
        return Math.max(dp1[nums.length - 1], dp2[nums.length - 1]);
    }

    //337. 打家劫舍 III
    public int rob3(TreeNode root) {
        int[] rootStatus = dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    //时间空间复杂度都是O(n)
    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        //
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        //选择跟节点,子节点就不能选了
        int selected = node.val + l[1] + r[1];
        //
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

    // 乘积最大子数组
    //给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
    //示例 1:
    //输入: [2,3,-2,4]
    //输出: 6
    //解释:子数组 [2,3] 有最大乘积 6。
    //示例 2:
    //输入: [-2,0,-1]
    //输出: 0
    //解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
    public int maxProduct(int[] nums) {
        //一个保存最大的，一个保存最小的。
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

    //删除并获得点数
    public int deleteAndEarn(int[] nums) {
        int[] trans = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            trans[nums[i]] += nums[i];
        }

        int[] dp = new int[10001];

        dp[0] = 0;
        dp[1] = trans[1];
        for (int i = 2; i < trans.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + trans[i]);
        }

        return dp[dp.length - 1];
    }

    //656. 金币路径
    //给定一个数组 A（下标从 1 开始）包含 N 个整数：A1，A2，……，AN和一个整数 B。
    //你可以从数组 A 中的任何一个位置（下标为 i）跳到下标i+1，i+2，……，i+B的任意一个可以跳到的位置上。
    //如果你在下标为 i 的位置上，你需要支付 Ai 个金币。如果 Ai 是 -1，意味着下标为 i 的位置是不可以跳到的。
    //现在，你希望花费最少的金币从数组 A 的 1 位置跳到N 位置，你需要输出花费最少的路径，依次输出所有经过的下标（从 1 到 N）。
    //如果有多种花费最少的方案，输出字典顺序最小的路径。
    //如果无法到达 N 位置，请返回一个空数组。
    public List<Integer> cheapestJump(int[] A, int B) {
        int[] next = new int[A.length];
        long[] dp = new long[A.length];
        Arrays.fill(next, -1);
        List<Integer> res = new ArrayList<>();
        for (int i = A.length - 2; i >= 0; i--) {
            long min_cost = Integer.MAX_VALUE;
            for (int j = i + 1; j <= i + B && j < A.length; j++) {
                if (A[j] >= 0) {
                    long cost = A[i] + dp[j];
                    if (cost < min_cost) {
                        min_cost = cost;
                        next[i] = j;
                    }
                }
            }
            dp[i] = min_cost;
        }
        int i;
        for (i = 0; i < A.length && next[i] > 0; i = next[i])
            res.add(i + 1);
        if (i == A.length - 1 && A[i] >= 0)
            res.add(A.length);
        else
            return new ArrayList<Integer>();
        return res;
    }
}
