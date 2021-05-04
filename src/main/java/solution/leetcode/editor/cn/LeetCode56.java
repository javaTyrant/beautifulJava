package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;

public class LeetCode56 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //合并区间
        public static void main(String[] args) {
            //[[2,3],[2,2],[3,3],[1,3],[5,7],[2,2],[4,6]]
            //
            int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
            //int[][] intervals = {{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}};

            Solution solution = new Solution();
            System.out.println(Arrays.deepToString(solution.merge(intervals)));
            System.out.println(Arrays.deepToString(solution.mergeDiff(intervals)));
            System.out.println(Arrays.deepToString(solution.mergeWithOutSort(intervals)));
        }

        /**
         * 仔细体会下
         * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
         * 输出: [[1,6],[8,10],[15,18]]
         * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
         */
        public int[][] merge(int[][] intervals) {
            //时间复杂度NlogN
            Arrays.sort(intervals, Comparator.comparing(i -> i[0]));
            //
            int[][] res = new int[intervals.length][2];
            //idx
            int idx = -1;
            for (int[] ints : intervals) {
                //第一个数大于,原数组第0个和idx索引的第一个比
                if (idx == -1 || ints[0] > intervals[idx][1]) {//没有交集
                    //需要++
                    res[++idx] = ints;
                } else {
                    //有交集,区最大的                  不要取错数组啊,要取res
                    res[idx][1] = Math.max(ints[1], res[idx][1]);
                }
            }
            return Arrays.copyOf(res, idx + 1);
        }

        //BitSet有点意思
        public int[][] mergeDiff(int[][] intervals) {
            BitSet bitSet = new BitSet();
            int max = 0;
            for (int[] interval : intervals) {
                int temp = interval[1] * 2 + 1;
                bitSet.set(interval[0] * 2, temp, true);
                max = Math.max(temp, max);
            }

            int index = 0, count = 0;
            while (index < max) {
                int start = bitSet.nextSetBit(index);
                int end = bitSet.nextClearBit(start);

                int[] item = {start / 2, (end - 1) / 2};
                intervals[count++] = item;

                index = end;
            }
            int[][] ret = new int[count][2];
            //if (count >= 0) System.arraycopy(intervals, 0, ret, 0, count);
            for (int i = 0; i < count; i++) {
                ret[i] = intervals[i];
            }

            return ret;
        }

        public int[][] mergeWithOutSort(int[][] intervals) {
            if (intervals == null || intervals.length == 0) return intervals;
            int count = 0;
            for (int i = 0; i < intervals.length; i++) {
                for (int j = i + 1; j < intervals.length; j++) {
                    if (intervals[i][1] >= intervals[j][0] && intervals[i][0] <= intervals[j][1]) {
                        if (intervals[i][0] < intervals[j][0]) intervals[j][0] = intervals[i][0];
                        if (intervals[i][1] > intervals[j][1]) intervals[j][1] = intervals[i][1];
                        intervals[i] = null;
                        count++;
                        break;
                    }
                }
            }
            int[][] ans = new int[intervals.length - count][];
            for (int i = 0, j = 0; j < intervals.length; j++) {
                if (intervals[j] != null) {
                    ans[i++] = intervals[j];
                }
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
