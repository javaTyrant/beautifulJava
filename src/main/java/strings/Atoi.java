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
        //初始化是start
        String state = START;
        Map<String, String[]> map;
        int sign = 1;
        long ans = 0;

        /**
         * 什么意思呢?顿悟了已经哈哈哈舒服
         */
        public Automaton() {
            map = new HashMap<>();
            map.put(START, new String[]{START, SIGNED, NUM, END});
            map.put(SIGNED, new String[]{END, END, NUM, END});
            map.put(NUM, new String[]{END, END, NUM, END});
            map.put(END, new String[]{END, END, END, END});
        }

        public int getCol(char c) {
            if (c == ' ') return 0;
            //有符号是1
            if (c == '+' || c == '-') return 1;
            //数字是2
            if (c >= '0' && c <= '9') return 2;
            //其他情况.
            return 3;
        }

        public void get(char c) {
            //每次修改状态
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

    public static void main(String[] args) {
        Atoi atoi = new Atoi();
        System.out.println(atoi.myAtoiRaw("+419,5"));
        int[][] arr = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println(isCovered(arr, 2, 5));
    }

    //1893. 检查是否区域内所有整数都被覆盖
    public static boolean isCovered(int[][] ranges, int left, int right) {
        int[] covered = new int[52];
        //思想:ranges区间覆盖到的值始终大于0
        for (int[] range : ranges) {
            covered[range[0]]++;
            covered[range[1] + 1]--;
        }
        //更新整个桶
        for (int i = 1; i < covered.length; i++) {
            covered[i] += covered[i - 1];
        }
        //判断不满足的条件
        for (int i = left; i <= right; i++) {
            if (covered[i] == 0) return false;
        }
        return true;
    }
}
