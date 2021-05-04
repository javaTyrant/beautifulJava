package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lumac
 * @since 2020/7/14
 */
public class Triangel {
    //120. 三角形最小路径和
    public static int minimumTotal(List<List<Integer>> triangle) {
        //取二维数组的size
        int n = triangle.size();
        //构造一个二维dp数组
        int[][] f = new int[n][n];
        //第一个数赋值
        f[0][0] = triangle.get(0).get(0);
        //遍历
        for (int i = 1; i < n; ++i) {
            //设置f[i][0],最边的值直接累加就okay了
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            //设置i,j
            for (int j = 1; j < i; ++j) {
                //非边界节点,要取上面的较小值,先取i再取j
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            //
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        //最后一行的最小值
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }

    public int minimumTotalOpt(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }

    public static void main(String[] args) {
        List<List<Integer>> tri = new ArrayList<>();
        tri.add(Arrays.asList(1));
        tri.add(Arrays.asList(2, 1));
        tri.add(Arrays.asList(1, 2, 3));
        tri.add(Arrays.asList(1, 2, 2, 2));
        System.out.println(minimumTotal(tri));
    }
}
