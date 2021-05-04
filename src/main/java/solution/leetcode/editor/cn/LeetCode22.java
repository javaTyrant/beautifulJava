package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //括号生成 牛逼
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            dfs(n, n, "");
            return res;
        }

        private void dfs(int left, int right, String curStr) {
            System.out.println(left + ":" + right);
            if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
                res.add(curStr);
                return;
            }

            if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
                dfs(left - 1, right, curStr + "(");
            }
            if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
                dfs(left, right - 1, curStr + ")");
            }
        }

        //左到右 每 3 个一组 分块
        public String reformatNumber(String number) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int j = number.length() - 1;
            while (i <= j) {
                if (number.charAt(i) == '-' || number.charAt(i) == ' ') {
                    i++;
                } else {
                    sb.append(number.charAt(i) - '0');
                    i++;
                }
            }
            number = sb.toString();
            int len = number.length();
            int count = 3;
            i = 0;
            while (i < len) {

            }
            return null;
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.reformatNumber("123 4-5678"));
//            solution.generateParenthesis(3);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
