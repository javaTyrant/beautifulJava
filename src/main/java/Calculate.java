import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author lufengxiang
 * @since 2021/7/7
 **/
public class Calculate {
    class TwoStack {
        public int calculate(String s) {
            // 存放所有的数字
            Deque<Integer> nums = new ArrayDeque<>();
            // 为了防止第一个数为负数，先往 nums 加个 0
            nums.addLast(0);
            // 将所有的空格去掉
            s = s.replaceAll(" ", "");
            // 存放所有的操作，包括 +/-
            Deque<Character> ops = new ArrayDeque<>();
            int n = s.length();
            char[] cs = s.toCharArray();
            for (int i = 0; i < n; i++) {
                char c = cs[i];
                if (c == '(') {
                    ops.addLast(c);
                } else if (c == ')') {
                    // 计算到最近一个左括号为止
                    while (!ops.isEmpty()) {
                        char op = ops.peekLast();
                        if (op != '(') {
                            calc(nums, ops);
                        } else {
                            ops.pollLast();
                            break;
                        }
                    }
                } else {
                    if (isNum(c)) {
                        int u = 0;
                        int j = i;
                        // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                        while (j < n && isNum(cs[j])) u = u * 10 + (int) (cs[j++] - '0');
                        nums.addLast(u);
                        i = j - 1;
                    } else {
                        if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                            nums.addLast(0);
                        }
                        // 有一个新操作要入栈时，先把栈内可以算的都算了
                        while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
                        ops.addLast(c);
                    }
                }
            }
            while (!ops.isEmpty()) calc(nums, ops);
            return nums.peekLast();
        }

        void calc(Deque<Integer> nums, Deque<Character> ops) {
            if (nums.isEmpty() || nums.size() < 2) return;
            if (ops.isEmpty()) return;
            int b = nums.pollLast(), a = nums.pollLast();
            char op = ops.pollLast();
            nums.addLast(op == '+' ? a + b : a - b);
        }

        boolean isNum(char c) {
            return Character.isDigit(c);
        }
    }

    public static void main(String[] args) {
        System.out.println(calculate("(1+(40+5+2)-3)+(6+8)"));
    }

    //224. 基本计算器
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        // sign 代表正负
        int sign = 1, res = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            //如果是数字
            if (Character.isDigit(ch)) {
                //转int
                int cur = ch - '0';
                //不断的寻找数字
                while (i + 1 < length && Character.isDigit(s.charAt(i + 1)))
                    cur = cur * 10 + s.charAt(++i) - '0';
                res = res + sign * cur;
            } else if (ch == '+') {//遇到+
                sign = 1;
            } else if (ch == '-') {//遇到-
                sign = -1;
            } else if (ch == '(') {//遇到(
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
            } else if (ch == ')') {//遇到)
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }

    public int calculate1(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }
}
