package solution.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode870 {
    static class Solution2 {
        public static int[] advantageCount(int[] a, int[] b) {
            return null;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //非常典型的贪心，每次在A中寻找大于B[i]的最小值，若没有，则返回A中的最小值。
    static class Solution {
        //优势洗牌 又名田忌赛马
        public static int[] advantageCount(int[] a, int[] b) {
            //记录b的长度
            int n = b.length;
            //排序
            Arrays.sort(a);
            //构建一个二维数组,记录b的值和下标
            int[][] pair = new int[n][2];
            for (int i = 0; i < n; i++)
                pair[i] = new int[]{b[i], i};//把下标保存下来
            //排序pair b的值
            Arrays.sort(pair, Comparator.comparingInt(x -> x[0]));
            //结果
            int[] res = new int[n];
            //双指针
            int r = n - 1, l = 0;
            for (int i = 0; i < n; i++) {
                //如果a的最小值小于b的最小值,a就是废物,最小的匹配最大的
                if (a[i] <= pair[l][0]) {
                    res[pair[r--][1]] = a[i];
                }
                //否则放到左边
                else {
                    res[pair[l++][1]] = a[i];
                }
            }
            return res;
        }

        private static final int BASE = 1 << 14;

        public int[] advantageCountBest(int[] A, int[] B) {
            int len = A.length;
            Arrays.sort(A);
            long[] aux = new long[len];
            for (int i = 0; i < len; i++) {
                aux[i] = ((long) B[i] << 14) + i;
            }
            Arrays.sort(aux);
            int left = 0;
            int right = len - 1;
            for (int num : A) {
                if (num > aux[left] >> 14) {
                    B[(int) (aux[left++] % BASE)] = num;
                } else {
                    B[(int) (aux[right--] % BASE)] = num;
                }
            }
            return B;
        }

        public static void main(String[] args) {
            int[] a = {2, 7, 11, 15};
            int[] b = {1, 10, 4, 11};
            System.out.println(Arrays.toString(advantageCount(a, b)));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
