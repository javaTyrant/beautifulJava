package conquerdp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 对基本功的绝佳训练.
 *
 * @author lumac
 * @since 2021/5/29
 */
public class PreSum {
    //相似

    //题目两数之和

    //连续的子数组和

    //乘积小于K的子数组

    //寻找数组的中心下标

    //和可被 K 整除的子数组

    //暴力法.注意遍历的路径.
    public int subarraySumForce(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    //560. 和为K的子数组 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        //为什么要放0和1.
        mp.put(0, 1);
        for (int num : nums) {
            //
            pre += num;
            //是否包含pre - k.
            if (mp.containsKey(pre - k)) {
                //
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

    //1074. 元素和为目标值的子矩阵数量
    public int numSubmatrixSumTarget(int[][] mat, int t) {
        int n = mat.length, m = mat[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int top = 1; top <= n; top++) {
            for (int bot = top; bot <= n; bot++) {
                int cur = 0;
                Map<Integer, Integer> map = new HashMap<>();
                for (int r = 1; r <= m; r++) {
                    cur = sum[bot][r] - sum[top - 1][r];
                    if (cur == t) ans++;
                    if (map.containsKey(cur - t)) ans += map.get(cur - t);
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return ans;
    }

    //303 区域和检索 - 数组不可变
    static class NumArray {
        int[] sums;

        public NumArray(int[] nums) {
            int n = nums.length;
            sums = new int[n + 1];
            for (int i = 0; i < n; i++) {
                sums[i + 1] = sums[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            return sums[j + 1] - sums[i];
        }
    }

    //304. 二维区域和检索 - 矩阵不可变
    //小学数学，田字形，已知整体面积，上面面积，左边面积，左上面积，求右下角矩形的面积。 右下角矩形的面积=整体面积-上面面积-左边面积+左上面积
    static class NumMatrix {
        int[][] prefix;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return;
            }
            int row = matrix.length;
            int col = matrix[0].length;
            prefix = new int[row + 1][col + 1];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    prefix[i + 1][j + 1] = prefix[i][j + 1] + prefix[i + 1][j] - prefix[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            for (int[] a : prefix) {
                System.out.println(Arrays.toString(a));
            }
            // 2  1 4 3
            return prefix[row2 + 1][col2 + 1] - prefix[row2 + 1][col1] - prefix[row1][col2 + 1] + prefix[row1][col1];
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 4, 5, 2};
        PreSum pre = new PreSum();
        //System.out.println(pre.subarraySum(arr, 7));
        int[] arr1 = {-2, 0, 3, -5, 2, 1};
        NumArray array = new NumArray(arr1);
        int[][] m = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix matrix = new NumMatrix(m);
        System.out.println(matrix.sumRegion(2, 1, 4, 3));
        array.sumRange(2, 5);
    }
}
