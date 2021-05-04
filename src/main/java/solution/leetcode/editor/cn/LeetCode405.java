package solution.leetcode.editor.cn;

public class LeetCode405 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.toHex(111));
        }

        private static final char[] HEX_MAP = "0123456789abcdef".toCharArray();

        //数字转换为十六进制数
        public String toHex(int num) {
            if (num == 0) {
                return "0";
            }
            StringBuilder sb = new StringBuilder();
            for (; num != 0; num >>>= 4) {
                int x = num & 0b1111;
                sb.append(HEX_MAP[x]);
            }
            return sb.reverse().toString();
        }
        //十六进制转10进制呢
    }
//leetcode submit region end(Prohibit modification and deletion)

}
