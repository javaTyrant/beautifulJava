package binarysearch;

/**
 * @author lumac
 * @since 2020/7/3
 */
public class FindKLeast {
    //378. 有序矩阵中第K小的元素
    //
    public static int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int lo = matrix[0][0];
        int hi = matrix[m - 1][n - 1];
        while (lo <= hi) {
            int cnt = 0, mid = lo + (hi - lo) / 2;
            int i = m - 1, j = 0;
            //统计个数
            while (i >= 0 && j < n) {
                if (matrix[i][j] <= mid) {
                    cnt += i + 1;
                    j++;
                } else i--;
            }
            //二分法控制mid
            if (cnt == k) return lo;
            if (cnt < k) lo = mid + 1;
            else hi = mid - 1;
        }
        return lo;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 3, 5, 7, 9, 11},
                {2, 4, 6, 8, 10, 12},
                {3, 5, 7, 9, 11, 13},
                {4, 6, 8, 10, 12, 14},
                {5, 7, 9, 11, 13, 15},
                {6, 8, 10, 12, 14, 16},
        };
        System.out.println(kthSmallest(arr, 3));
    }
}
