package solution.leetcode.editor.cn;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

public class LeetCode394 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //显然这个解法更优秀
        public static String decodeString_(String s) {
            Stack<Integer> mul = new Stack<>();
            Stack<String> pre = new Stack<>();
            //因为可能是20ab,这种,肯定要把值截取出来
            int num = 0;
            StringBuilder res = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (c <= '9' && c >= '0') {
                    num = num * 10 + c - '0';
                } else if (c == '[') {
                    mul.push(num);
                    //清空num
                    num = 0;
                    //入栈后清空
                    pre.push(res.toString());
                    res = new StringBuilder();
                } else if (c == ']') {
                    //遇到 ] 开始处理
                    //弹出数字
                    int time = mul.pop();
                    //弹出表达式
                    String tmp = pre.pop();
                    StringBuilder sb = new StringBuilder(tmp);
                    //重复time次
                    while (time-- > 0) {
                        sb.append(res.toString());
                    }
                    //拼接好的值赋值给res
                    res = new StringBuilder(sb.toString());
                } else {
                    res.append(c);
                }
            }
            //返回string
            return res.toString();
        }

        public static void main(String[] args) {
            System.out.println(decodeString_("2[a2[b]]"));
            System.out.println(decodeString("2[a2[b]]"));
            System.out.println(decodeString("3[abc]"));
        }

        //字符串解码
        static int ptr;

        public static String decodeString(String s) {
            LinkedList<String> stk = new LinkedList<>();
            ptr = 0;

            while (ptr < s.length()) {
                char cur = s.charAt(ptr);
                if (Character.isDigit(cur)) {
                    // 获取一个数字并进栈
                    String digits = getDigits(s);
                    stk.addLast(digits);
                } else if (Character.isLetter(cur) || cur == '[') {
                    // 获取一个字母并进栈
                    stk.addLast(String.valueOf(s.charAt(ptr++)));
                } else {
                    ++ptr;
                    LinkedList<String> sub = new LinkedList<>();
                    //如果栈顶不是[
                    while (!"[".equals(stk.peekLast())) {
                        sub.addLast(stk.removeLast());
                    }
                    Collections.reverse(sub);
                    // 左括号出栈
                    stk.removeLast();
                    // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                    int repTime = Integer.parseInt(stk.removeLast());
                    StringBuffer t = new StringBuffer();
                    String o = getString(sub);
                    // 构造字符串
                    while (repTime-- > 0) {
                        t.append(o);
                    }
                    // 将构造好的字符串入栈
                    stk.addLast(t.toString());
                }
            }

            return getString(stk);
        }

        public static String getDigits(String s) {
            StringBuffer ret = new StringBuffer();
            while (Character.isDigit(s.charAt(ptr))) {
                ret.append(s.charAt(ptr++));
            }
            return ret.toString();
        }

        public static String getString(LinkedList<String> v) {
            StringBuffer ret = new StringBuffer();
            for (String s : v) {
                ret.append(s);
            }
            return ret.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
