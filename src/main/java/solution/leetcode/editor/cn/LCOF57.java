package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LCOF57 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            System.out.println(Arrays.deepToString(findContinuousSequence(15)));
            System.out.println(Arrays.deepToString(findContinuousSequenceBf(15)));
        }

        //连续正整数序列,时间复杂度O(target*根号target)
        public static int[][] findContinuousSequenceBf(int target) {
            List<int[]> vec = new ArrayList<>();
            int sum = 0, limit = (target - 1) / 2; // (target - 1) / 2 等效于 target / 2 下取整
            for (int i = 1; i <= limit; ++i) {
                for (int j = i; ; ++j) {
                    sum += j;
                    if (sum > target) {
                        sum = 0;
                        break;
                    } else if (sum == target) {
                        int[] res = new int[j - i + 1];
                        //i到j是满足条件的
                        for (int k = i; k <= j; ++k) {
                            res[k - i] = k;
                        }
                        vec.add(res);
                        sum = 0;
                        break;
                    }
                }
            }
            return vec.toArray(new int[vec.size()][]);
        }

        //解法太牛逼了 和为s的连续正数序列
        public static int[][] findContinuousSequence(int target) {
            List<int[]> result = new ArrayList<>();
            int i = 1;

            while (target > 0) {
                target -= i++;
                if (target > 0 && target % i == 0) {
                    int[] array = new int[i];
                    for (int k = target / i, j = 0; k < target / i + i; k++, j++) {
                        array[j] = k;
                    }
                    result.add(array);
                }
            }
            Collections.reverse(result);
            //List<int[]>如何转二维数组
            return result.toArray(new int[0][]);
        }

        //牛逼 O(target)
        public int[][] findContinuousSequencePointer(int target) {
            List<int[]> vec = new ArrayList<>();
            for (int l = 1, r = 2; l < r; ) {
                //求和公式l到r的和
                int sum = (l + r) * (r - l + 1) / 2;
                if (sum == target) {
                    int[] res = new int[r - l + 1];
                    for (int i = l; i <= r; ++i) {
                        res[i - l] = i;
                    }
                    vec.add(res);
                    l++;
                } else if (sum < target) {
                    r++;
                } else {
                    l++;
                }
            }
            return vec.toArray(new int[vec.size()][]);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
