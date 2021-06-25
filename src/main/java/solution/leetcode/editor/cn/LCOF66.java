package solution.leetcode.editor.cn;

import java.util.Arrays;

/**
 * @author lumac
 * @since 2020/12/17
 */
public class LCOF66 {
    //指 Offer 66. 构建乘积数组:这个思路太牛逼了.
    public static int[] constructArr(int[] a) {
        int[] res = new int[a.length];
        for (int i = 0, cur = 1; i < a.length; i++) {
            res[i] = cur;   // 先乘左边的数(不包括自己)
            cur *= a[i];
        }
        for (int i = a.length - 1, cur = 1; i >= 0; i--) {
            res[i] *= cur;  // 再乘右边的数(不包括自己)
            cur *= a[i];
        }
        return res;
    }

    public static void main(String[] args) {
        //res[0] = 1, cur = 1 * 1 = 1
        //res[1] = 1, cur = 1 * 2 = 2
        //res[2] = 2, cur = 2 * 3 = 6
        //res[3] = 6, cur = 6 * 4 = 24
        //res[4] = 24 cur = 24 * 5 = 120
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(constructArr(arr)));
    }
}
