package solution.leetcode.editor.cn;

public class LeetCode402 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //402. 移掉K位数字
        public static String removeKdigits(String num, int k) {
            if (num.length() == k) {
                return "0";
            }
            StringBuilder s = new StringBuilder(num);
            for (int i = 0; i < k; i++) {
                int idx = 0;
                //先删除最大的
                for (int j = 1; j < s.length() && s.charAt(j) >= s.charAt(j - 1); j++) {
                    idx = j;
                }
                //删除
                s.delete(idx, idx + 1);
                //删除开头的0
                while (s.length() > 1 && s.charAt(0) == '0') {
                    s.delete(0, 1);
                }
            }
            return s.toString();
        }

        public static void main(String[] args) {
            //System.out.println(removeKdigits("348885", 3));
            System.out.println(removeKdigits("10200", 1));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
