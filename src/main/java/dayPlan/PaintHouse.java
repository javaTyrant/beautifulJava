package dayPlan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2021/5/4
 */
public class PaintHouse {
    static class MySolution {
        //的倍数.
        public static boolean checkSubarraySum(int[] nums, int k) {
            int len = nums.length;
            for (int left = 0; left < len - 1; left++) {
                for (int right = left + 1; right < len; right++) {
                    int sum = 0;
                    for (int i = left; i <= right; i++) {
                        sum += nums[i];
                    }
                    if (sum % k == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static void main(String[] args) {
            int[] arr = {23, 2, 4, 6, 7, 6};
            System.out.println(checkSubarraySum(arr, 6));
        }

        //740. 删除并获得点数
        //给你一个整数数组nums，你可以对它进行一些操作。
        //每次操作中，选择任意一个nums[i]，删除它并获得nums[i]的点数。之后，你必须删除每个等于nums[i] - 1或nums[i] + 1的元素。
        //开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
        public int deleteAndEarn(int[] nums) {
            //将数组变换，转换为每个值的所有点数。就成了打家劫舍问题
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
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //

    //给定一个包含 非负数 的数组和一个目标 整数 k ，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，
    //且总和为 k 的倍数，即总和为 n * k ，其中 n 也是一个整数。
    //连续的子数组和 ontinuous Subarray Sum
    //1.暴力:O(n^3)
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        for (int left = 0; left < len - 1; left++) {
            for (int right = left + 1; right < len; right++) {
                int sum = 0;
                //累计求和.
                for (int i = left; i <= right; i++) {
                    sum += nums[i];
                }
                //
                if (sum == k || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSubarraySum1(int[] nums, int k) {
        int len = nums.length;

        // preSum[i] 表示：区间 [0..i) 的前缀和
        int[] preSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }


        for (int left = 0; left < len - 1; left++) {
            for (int right = left + 1; right < len; right++) {
                int sum = preSum[right + 1] - preSum[left];
                if (sum == k || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSubarraySum2(int[] nums, int k) {
        int sum = 0;

        // key：区间 [0..i] 里所有元素的和 % k
        // value：下标 i
        Map<Integer, Integer> map = new HashMap<>();
        // 理解初始化的意义
        map.put(0, -1);
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }

            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }

        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {23, 2, 4, 6, 7};
        PaintHouse solution = new PaintHouse();
        System.out.println(solution.checkSubarraySum(arr, 6));
        System.out.println(solution.checkSubarraySum1(arr, 6));
        System.out.println(solution.checkSubarraySum2(arr, 6));
    }
    //假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，
    //你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
    //当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
    //每个房子粉刷成不同颜色的花费是以一个n x 3的矩阵来表示的。
    //例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2]表示第 1 号房子粉刷成绿色的花费，
    //以此类推。请你计算出粉刷完所有房子最少的花费成本。
    //注意：
    //所有花费均为正整数。
    //示例：
    //输入: [[17,2,17],[16,16,5],[14,3,19]]
    //输出: 10
    //解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
    //最少花费: 2 + 5 + 3 = 10。

    //约束:相邻的两个房子颜色不能相同。
    //
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n < 1) {
            return 0;
        }
        int red = costs[0][0];
        int green = costs[0][1];
        int blue = costs[0][2];
        int tmp_red;
        int tmp_green;
        for (int i = 1; i < n; i++) {
            tmp_red = red;
            tmp_green = green;
            red = costs[i][0] + Math.min(green, blue);
            green = costs[i][1] + Math.min(tmp_red, blue);
            blue = costs[i][2] + Math.min(tmp_red, tmp_green);
        }
        return Math.min(Math.min(red, green), blue);
    }


    //在一个小城市里，有m个房子排成一排，你需要给每个房子涂上 n种颜色之一（颜色编号为 1 到 n）。
    // 有的房子去年夏天已经涂过颜色了，所以这些房子不需要被重新涂色。
    //我们将连续相同颜色尽可能多的房子称为一个街区。（比方说 houses = [1,2,2,3,3,2,1,1] ，
    // 它包含 5 个街区 [{1}, {2,2}, {3,3}, {2}, {1,1}] 。）
    //给你一个数组houses，一个m * n的矩阵cost和一个整数target，其中：
    //houses[i]：是第i个房子的颜色，0表示这个房子还没有被涂色。
    //cost[i][j]：是将第i个房子涂成颜色j+1的花费。
    //请你返回房子涂色方案的最小总花费，使得每个房子都被涂色后，恰好组成target个街区。如果没有可用的涂色方案，请返回-1。
    //示例 1：
    //输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
    //输出：9
    //解释：房子涂色方案为 [1,2,2,1,1]
    //此方案包含 target = 3 个街区，分别是 [{1}, {2,2}, {1,1}]。
    //涂色的总花费为 (1 + 1 + 1 + 1 + 5) = 9。
    //示例 2：
    //输入：houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
    //输出：11
    //解释：有的房子已经被涂色了，在此基础上涂色方案为 [2,2,1,2,2]
    //此方案包含 target = 3 个街区，分别是 [{2,2}, {1}, {2,2}]。
    //给第一个和最后一个房子涂色的花费为 (10 + 1) = 11。
    //示例 3：
    //输入：houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
    //输出：5
    //示例 4：
    //输入：houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
    //输出：-1
    //解释：房子已经被涂色并组成了 4 个街区，分别是 [{3},{1},{2},{3}] ，无法形成 target = 3 个街区。
    //极大值
    //选择 Integer.MAX_VALUE / 2 的原因是防止整数相加溢出
    static final int INFTY = Integer.MAX_VALUE / 2;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // 将颜色调整为从 0 开始编号，没有被涂色标记为 -1
        for (int i = 0; i < m; ++i) {
            --houses[i];
        }

        // dp 所有元素初始化为极大值
        int[][][] dp = new int[m][n][target];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], INFTY);
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (houses[i] != -1 && houses[i] != j) {
                    continue;
                }

                for (int k = 0; k < target; ++k) {
                    for (int j0 = 0; j0 < n; ++j0) {
                        if (j == j0) {
                            if (i == 0) {
                                if (k == 0) {
                                    dp[i][j][k] = 0;
                                }
                            } else {
                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k]);
                            }
                        } else if (i > 0 && k > 0) {
                            dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j0][k - 1]);
                        }
                    }

                    if (dp[i][j][k] != INFTY && houses[i] == -1) {
                        dp[i][j][k] += cost[i][j];
                    }
                }
            }
        }

        int ans = INFTY;
        for (int j = 0; j < n; ++j) {
            ans = Math.min(ans, dp[m - 1][j][target - 1]);
        }
        return ans == INFTY ? -1 : ans;
    }
}
