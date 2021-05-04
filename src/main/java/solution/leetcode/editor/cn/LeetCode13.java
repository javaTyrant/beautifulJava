package solution.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LeetCode13 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        /**
         * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
         * <p>
         * 字符          数值
         * I             1
         * V             5
         * X             10
         * L             50
         * C             100
         * D             500
         * M             1000
         */
        public static int romanToInt(String s) {
            Map<Character, Integer> map = new HashMap<>();
            map.put('M' , 1000);
            map.put('D' , 500);
            map.put('C' , 100);
            map.put('L' , 50);
            map.put('X' , 10);
            map.put('V' , 5);
            map.put('I' , 1);
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                int nextVal = i < s.length() - 1 ? map.get(s.charAt(i + 1)) : 0;
                int cur = map.get(s.charAt(i));
                res += nextVal > cur ? -cur : cur;
            }
            return res;
        }

        public static void main(String[] args) {
            System.out.println(romanToInt("LVIII"));
        }

        public int romanToIntBetter(String s) {
            char[] chars = s.toCharArray();
            if (chars.length <= 0) return 0;
            int sum = getInt(chars[0]);
            if (chars.length == 1) return sum;

            int prev = sum;
            for (int i = 1; i < chars.length; i++) {
                int now = getInt(chars[i]);
                if (now > prev) {
                    sum = sum - 2 * prev;
                }
                sum = sum + now;
                prev = now;

            }
            return sum;
        }

        public static int getInt(char c) {
            if (c == 'I') return 1;
            else if (c == 'V') return 5;
            else if (c == 'X') return 10;
            else if (c == 'L') return 50;
            else if (c == 'C') return 100;
            else if (c == 'D') return 500;
            else if (c == 'M') return 1000;
            else return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
