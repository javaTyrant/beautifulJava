package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode435 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            int[][] arr = {
                    {1, 2}, {2, 3}, {3, 4}, {1, 3}
            };
            System.out.println(solution.eraseOverlapIntervalsBest(arr));
        }

        //给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
        //[ [1,2], [2,3], [3,4], [1,3] ]
        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
            int n = intervals.length;
            int[] f = new int[n];
            Arrays.fill(f, 1);
            for (int i = 1; i < n; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (intervals[j][1] <= intervals[i][0]) {
                        f[i] = Math.max(f[i], f[j] + 1);
                    }
                }
            }
            return n - Arrays.stream(f).max().getAsInt();
        }

        public int eraseOverlapIntervalsBest(int[][] intervals) {
            Arrays.sort(intervals, (o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            });

            int cnt = 0;
            int i = 0;
            int j = 1;
            while (j < intervals.length) {
                if (intervals[i][1] <= intervals[j][0]) {
                    i = j;
                    j++;
                } else {
                    if (intervals[i][1] < intervals[j][1]) {
                        cnt++;
                        j++;
                    } else {
                        cnt++;
                        i = j;
                        j++;
                    }
                }
            }

            return cnt;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
