import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lumac
 * @since 2020/7/26
 */
public class MergeArray {
    public static void main(String[] args) {
        int[][] arr = {{2, 6}, {3, 5}, {1, 2}};
        int[][] arr1 = {{1, 2}, {2, 4}, {4, 7}};
        Arrays.sort(arr, Comparator.comparingInt(v -> v[1]));
        System.out.println(Arrays.deepToString(arr));
        System.out.println();
        System.out.println(Arrays.deepToString(merge(arr)));
//        System.out.println(Arrays.deepToString(merge(arr1)));
    }

    public static int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序,按照尾部排序
        Arrays.sort(intervals, Comparator.comparingInt(v -> v[0]));
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        //截取数组,长度可能变小了
        return Arrays.copyOf(res, idx + 1);
    }
}
