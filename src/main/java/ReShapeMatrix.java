import java.util.Arrays;

/**
 * 锻炼基础:明天手写
 *
 * @author lufengxiang
 * @since 2021/7/5
 **/
public class ReShapeMatrix {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };
        ReShapeMatrix matrix = new ReShapeMatrix();
        System.out.println(Arrays.deepToString(matrix.matrixReshape(arr, 2, 8)));
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        //
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        int m = nums[0].length;
        //不等则无法重塑
        if (n * m != r * c) return nums;
        //辅助数组
        int[] aux = new int[n * m];
        //
        int[][] res = new int[r][c];
        //
        for (int i = 0; i < n; i++) {
            System.arraycopy(nums[i], 0, aux, i * m, m);
        }
        for (int i = 0; i < r; i++) {
            System.arraycopy(aux, i * c, res[i], 0, c);
        }
        //
        return res;
    }
}
