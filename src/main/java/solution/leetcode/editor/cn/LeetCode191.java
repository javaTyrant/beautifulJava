package solution.leetcode.editor.cn;

public class LeetCode191 {
    //leetcode submit region begin(Prohibit modification and deletion)
    public static class Solution {
        // you need to treat n as an unsigned value
        public static void main(String[] args) {
            System.out.println(hammingWeight(9));
        }

        //汉明距离
        public static int hammingWeight(int n) {
            int sum = 0;
            while (n != 0) {
                sum++;
                //&都为1才是1
                n &= (n - 1);
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
