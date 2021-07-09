/**
 * @author lufengxiang
 * @since 2021/7/7
 **/
public class DiffArray {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        System.out.println(isCovered(arr, 2, 5));
    }

    public static boolean isCovered(int[][] ranges, int left, int right) {
        int[] covered = new int[52];
        //思想:ranges区间覆盖到的值始终大于0
        for (int[] range : ranges) {
            covered[range[0]]++;
            covered[range[1] + 1]--;
        }
        //更新整个桶
        for (int i = 1; i < covered.length; i++) {
            covered[i] += covered[i - 1];
        }
        for (int i = left; i <= right; i++) {
            if (covered[i] == 0) return false;
        }
        return true;
    }

    public int minOperationsToFlip(String expression) {
        return 0;
    }

}
