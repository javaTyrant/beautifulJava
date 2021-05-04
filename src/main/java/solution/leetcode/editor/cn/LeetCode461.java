package solution.leetcode.editor.cn;

public class LeetCode461 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static int hammingDistance(int x, int y) {
            //^相同为0,不同为1,所以统计异或后的结果里的1的个数
            x = x ^ y;
            y = 0;
            while (x > 0) {
                //&相同为1,不同为0
                if ((x & 1) == 1) {
                    y++;
                }
                //移位
                x >>= 1;
            }
            return y;
        }

        //根据 与运算 定义，设二进制数字 nn ，则有：
        //若 n & 1 = 0 ，则 n 二进制 最右一位 为 0；
        //若 n & 1 = 1 ，则 n 二进制 最右一位 为 1 。
        //
        //二进制中1的个数
        public int hammingWeight(int n) {
            int count = 0;
            while (n != 0) {
                count++;
                //和低一位按位与
                n = n & (n - 1);
            }
            return count;
        }

        //2的幂
        public static boolean isPowerOfTwo(int n) {
            return (n > 0) && (n & -n) == n;
        }

        public static void main(String[] args) {
            hammingDistance(1, 4);
            System.out.println(isPowerOfTwo(4));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
