package solution.leetcode.editor.cn;

public class LeetCode8 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.myAtoi("-98-87"));
        }

        //如何思考
        public int myAtoi(String str) {
            //先trim一下,去掉空格
            str = str.trim();
            //如果长度是0返回0
            if (str.length() == 0) return 0;
            //如果开头不是数字或者符号,返回0
            if (!Character.isDigit(str.charAt(0))
                    && str.charAt(0) != '-' && str.charAt(0) != '+')
                return 0;
            //防止溢出
            long ans = 0L;
            //负数
            boolean neg = str.charAt(0) == '-';
            //如果不是数字,从哪个位置找
            int i = !Character.isDigit(str.charAt(0)) ? 1 : 0;
            //如果只有一个符号,那么肯定是+或者- 且必须是数字,数字中有任何一个符号就直接返回
            while (i < str.length() && Character.isDigit(str.charAt(i))) {
                //获取第一个数字,每次都要乘10
                ans = ans * 10 + (str.charAt(i++) - '0');
                //溢出判断
                if (!neg && ans > Integer.MAX_VALUE) {
                    ans = Integer.MAX_VALUE;
                    break;
                }
                //没有溢出的话,负数也会溢出,哈哈,所以溢出也要考虑两种情况
                if (neg && ans > 1L + Integer.MAX_VALUE) {
                    ans = 1L + Integer.MAX_VALUE;
                    break;
                }
                //其他情况继续循环
            }
            //正负判断
            return neg ? (int) -ans : (int) ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
