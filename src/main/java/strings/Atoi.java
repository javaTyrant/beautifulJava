package strings;

import java.util.HashMap;
import java.util.Map;

/**
 * 自动机
 *
 * @author lumac
 * @since 2020-05-12
 */
public class Atoi {
    class Automaton {
        //初始状态
        final String START = "start";
        //有符号
        final String SIGNED = "signed";
        //数字
        final String NUM = "number";
        //结束
        final String END = "end";

        String state = START;
        Map<String, String[]> map;
        int sign = 1;
        long ans = 0;

        /**
         * 什么意思呢?顿悟了已经哈哈哈舒服
         */
        public Automaton() {
            map = new HashMap<>();
            //牛逼啊,getCol的返回值决定了这个string的顺序
            //
            map.put(START, new String[]{START, SIGNED, NUM, END});
            map.put(SIGNED, new String[]{END, END, NUM, END});
            map.put(NUM, new String[]{END, END, NUM, END});
            map.put(END, new String[]{END, END, END, END});
        }

        public int getCol(char c) {
            if (c == ' ') return 0;
            if (c == '+' || c == '-') return 1;
            if (c >= '0' && c <= '9') return 2;
            return 3;
        }

        /**
         * @param c
         */
        public void get(char c) {
            //
            state = map.get(state)[getCol(c)];
            if (state.equals(NUM)) {
                ans = ans * 10 + c - '0';
                if (sign == 1) {
                    ans = Math.min(ans, Integer.MAX_VALUE);
                } else {
                    // -(long)Integer.MIN_VALUE，这个操作有点东西，不然越界
                    ans = Math.min(ans, -(long) Integer.MIN_VALUE);
                }
            } else if (state.equals(SIGNED))
                sign = c == '+' ? 1 : -1;
        }
    }

    public int myAtoiRaw(String str) {
        Automaton automaton = new Automaton();
        char[] c = str.toCharArray();
        for (char ch : c) {
            automaton.get(ch);
        }
        return automaton.sign * ((int) automaton.ans);
    }

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
        //第一位如果是数字,就从0开始,否则从1开始,不是数字就是+-
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

    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoiRaw("+419,5"));
        System.out.println(atoi.myAtoi1("-91283472332"));
    }

    public int myAtoi1(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;
        char first = s.charAt(0);
        if (!Character.isDigit(first) && first != '-' && first != '+') return 0;
        boolean neg = first == '-';
        int i = Character.isDigit(first) ? 0 : 1;
        int res;
        long ans = 0L;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            ans = ans * 10 + (s.charAt(i) - '0');
            //溢出
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
            }
            if (neg && -ans < Integer.MIN_VALUE) {
                ans = Integer.MIN_VALUE;
            }
            i++;
        }
        res = (int) ans;
        return neg ? -res : res;
    }

}
