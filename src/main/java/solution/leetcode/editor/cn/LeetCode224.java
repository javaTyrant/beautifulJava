package solution.leetcode.editor.cn;

import java.time.LocalDate;
import java.util.Stack;

public class LeetCode224 {
    //leetcode submit region begin(Prohibit modification and deletion)
    //对基本功的极好锻炼
    static class MySolution {
        public static int calculate(String s) {
            int res = 0;
            int sign = 1;
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                int cur = 0;
                if (Character.isDigit(s.charAt(i))) {
                    cur = s.charAt(i) - '0';
                    while (i < s.length() && Character.isDigit(s.charAt(i + 1))) {
                        cur = cur * 10 + s.charAt(++i) - '0';
                    }

                } else if (s.charAt(i) == ')') {

                } else if (s.charAt(i) == '(') {

                } else if (s.charAt(i) == '+') {

                } else if (s.charAt(i) == '-') {

                }
            }
            return 0;
        }
    }

    //
    //
    //
    //
    //
    //
    static class Solution {
        public static void main(String[] args) {
            System.out.println(LocalDate.now());
            String expr = "(30+2)+3-3";
            System.out.println(calculate(expr));
            Solution solution = new Solution();
            System.out.println(solution.calculateStack(expr));
        }


        //基本计算器
        public static int calculate(String s) {
            return process(s.toCharArray(), 0)[0];
        }

        public int calculateStack(String s) {
            Stack<Integer> stack = new Stack<>();
            // sign 代表正负
            int sign = 1, res = 0;
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char ch = s.charAt(i);
                if (Character.isDigit(ch)) {
                    //截取数字
                    int cur = ch - '0';
                    while (i + 1 < length && Character.isDigit(s.charAt(i + 1)))
                        cur = cur * 10 + s.charAt(++i) - '0';
                    //res和sign
                    res = res + sign * cur;
                } else if (ch == '+') {
                    sign = 1;
                } else if (ch == '-') {
                    sign = -1;
                } else if (ch == '(') {
                    //push进去
                    stack.push(res);
                    //清0
                    res = 0;
                    //符号push进去
                    stack.push(sign);
                    sign = 1;
                } else if (ch == ')') {
                    //弹出来计算
                    res = stack.pop() * res + stack.pop();
                }
            }
            return res;
        }

        private static int[] process(char[] chs, int index) {
            int res = 0;
            char opera = '+';
            int first = 0; // opera的前一个数
            int second = 0; // opera的后一个数
            //入栈
            while (index < chs.length && chs[index] != ')') {
                if (chs[index] >= '0' && chs[index] <= '9') {
                    second = second * 10 + (int) chs[index++] - '0';
                } else if (chs[index] != '(' && chs[index] != ' ') {
                    if (opera == '+' || opera == '-') {
                        res += first;
                        first = opera == '+' ? second : -second;
                    } else {
                        first = opera == '*' ? first * second : first / second;
                    }
                    opera = chs[index++];
                    second = 0;
                } else if (chs[index] == '(') {
                    int[] info = process(chs, index + 1);
                    second = info[0];
                    index = info[1] + 1;
                } else {
                    ++index;
                }
            }
            if (opera == '+' || opera == '-') {
                res += opera == '+' ? first + second : first - second;
            } else {
                res += opera == '*' ? first * second : first / second;
            }
            return new int[]{res, index};
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
