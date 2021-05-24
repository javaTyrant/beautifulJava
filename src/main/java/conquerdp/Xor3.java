package conquerdp;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/5/19
 **/
public class Xor3 {
    public static void main(String[] args) {
        String s = "%20";
        System.out.println(Integer.parseInt(s.substring(1)));
    }

    //不修改数组找出重复的数字
    public static int duplicateInArray(int[] nums) {
        Set<Integer> map = new HashSet<>();
        for (int n : nums) {
            if (map.contains(n)) {
                return n;
            } else {
                map.add(n);
            }
        }
        return -1;
    }

    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        List<Integer> results = new ArrayList<>();
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
                results.add(pre[i][j]);
            }
        }
        Collections.sort(results, (num1, num2) -> num2 - num1);
        return results.get(k - 1);
    }
}
