package solution.leetcode.editor.cn;

public class LeetCode378 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //为什么left一定在数组中,拿官方的用例来说,很容易就发现,mid的走势是8,12,14,13,13
        // 好了,小于8的有两个元素,小于12的有5元素,小于13的有6个元素,小于14的有8个元素.
        // 来,再画一次重点,如果小于13的有6个,小于14的有8个,那么中间差的两个是不是13.
        // 为了加深下印象,我们再改变一下k的值,把k改成5,mid的走势是,8,12,10,11,11
        // 现在是不是发现规律了,最后的几个mid,都是排序后是连续的,也就是说,每个都会逐渐逼近的.
        // 再上一个k=6的mid走势,8,12,10,11,12,各位看官懂了吗.
        public static int kthSmallest(int[][] matrix, int k) {
            int m = matrix.length, n = matrix[0].length;
            int lo = matrix[0][0], hi = matrix[m - 1][n - 1];
            while (lo <= hi) {
                int cnt = 0, mid = lo + (hi - lo) / 2;
                System.out.println(mid);
                int i = m - 1, j = 0;
                //找到矩阵中小于等于mid的值
                while (i >= 0 && j < n) {
                    if (matrix[i][j] <= mid) {
                        cnt += i + 1;
                        j++;
                    } else i--;
                }
                //mid是减1和加1的
                if (cnt < k) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return lo;
        }

        public static void main(String[] args) {
            int[][] matrix = {
                    {1, 5, 9},
                    {10, 11, 13},
                    {12, 13, 15}};
            System.out.println(kthSmallest(matrix, 6));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
