package dayPlan;

import java.util.List;

public class MiniS {
    private int idx = -1;

    public NestedInteger deserialize(String s) {
        NestedInteger nestedInteger = new NestedInteger();
        while (++idx < s.length()) {
            char ch = s.charAt(idx);
            // 如果是正数或负数。
            if (ch == '-' || Character.isDigit(ch)) {
                int num = 0;
                int sign = 1;
                while (idx < s.length() && (ch == '-' || Character.isDigit(ch))) {
                    if (ch == '-') {
                        sign = -1;
                    } else {
                        num = num * 10 + ch - '0';
                    }
                    if (++idx < s.length()) ch = s.charAt(idx);
                }
                // 解析数字。
                num = num * sign;
                NestedInteger ni = new NestedInteger(num);
                // 如果解析完毕的同时也到字符串的最后，说明它没有遇到】，那就是简简单单的一个数字
                // 而非列表,直接返回数字
                if (idx == s.length()) return ni;
                nestedInteger.add(ni);
                idx--;
            } else if (ch == '[') {
                if (idx == 0) return deserialize(s);
                nestedInteger.add(deserialize(s));
            } else if (ch == ',') {
                continue;
            } else {
                // 为 ]时,break 跳出当前递归的循环，将解析的结果返回至上一层。
                break;
            }
        }
        return nestedInteger;
    }

    class NestedInteger {
        NestedInteger() {
        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {

        }

        ;

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return 0;
        }

        ;

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {

        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {

        }

        public List<NestedInteger> getList() {
            return null;
        }
    }


}

