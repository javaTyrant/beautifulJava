package stack;

/**
 * @author lumac
 * @since 2020/6/25
 */
public class ValidPa {
    //有效的括号
    //ArrayDeque
    public static boolean isValid(String s) {
        char[] chars = new char[s.length()];
        //单指针
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //左边入栈
            if (ch == '(' || ch == '[' || ch == '{') {
                chars[len] = ch;
                //指针右移
                len++;
                //非首括号,且len>0
            } else if (len > 0) {
                //len - 1
                if (ch == ')' && chars[len - 1] != '(') {
                    return false;
                } else if (ch == ']' && chars[len - 1] != '[') {
                    return false;
                } else if (ch == '}' && chars[len - 1] != '{') {
                    return false;
                }
                //记得减1
                len--;
            } else {
                return false;
            }
        }
        return len == 0;
    }

    public static void main(String[] args) {
        System.out.println(isValid("(()))"));
        System.out.println(isValid("((()))"));
        System.out.println(isValidde("()[]"));
    }

    public static boolean isValidde(String s) {
        int len = 0;
        char[] arr = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '(' || c == '{') {
                arr[len] = c;
                len++;
            } else if (len > 0) {
                if (c == ')' && arr[len - 1] != '(') {
                    return false;
                }
                if (c == ']' && arr[len - 1] != '[') {
                    return false;
                }
                if (c == '}' && arr[len - 1] != '{') {
                    return false;
                }
                len--;
            } else {
                return false;
            }
        }
        return len == 0;
    }
}
