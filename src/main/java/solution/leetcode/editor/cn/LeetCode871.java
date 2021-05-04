package solution.leetcode.editor.cn;

import java.util.PriorityQueue;

public class LeetCode871 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            int[][] arr = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
            Solution solution = new Solution();
            System.out.println(solution.minRefuelStops(100, 10, arr));
        }

        //动态规划
        public int minRefuelStopsdp(int target, int startFuel, int[][] stations) {
            int N = stations.length;
            long[] dp = new long[N + 1];
            dp[0] = startFuel;
            for (int i = 0; i < N; ++i)
                for (int t = i; t >= 0; --t)
                    if (dp[t] >= stations[i][0])
                        dp[t + 1] = Math.max(dp[t + 1], dp[t] + (long) stations[i][1]);

            for (int i = 0; i <= N; ++i)
                if (dp[i] >= target) return i;
            return -1;
        }

        //最低加油次数 优先队列,策略是啥
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            if (stations.length == 0)
                return startFuel >= target ? 0 : -1;
            //排序的规则
            PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            //初始的油
            int sum = startFuel;
            //保存结果
            int ans = 0;
            //每个站点遍历
            for (int i = 0; i < stations.length; i++) {
                //需要加油
                while (sum < stations[i][0]) {
                    //油不够就返回-1
                    Integer oil = queue.poll();
                    if (oil == null) return -1;
                    //否则sum+
                    sum += oil;
                    //加了一次了
                    ans++;
                }
                //不需要加油,记录下能跑的距离
                queue.offer(stations[i][1]);
            }
            //当sum < target 还没到
            while (sum < target) {
                Integer oil = queue.poll();
                if (oil == null) return -1;
                sum += oil;
                ans++;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
