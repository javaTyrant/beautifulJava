package solution.leetcode.editor.cn;

public class LeetCode20 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValid(String s) {
            //指针
            int len = 0;
            char[] arr = new char[s.length()];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '[' || c == '(' || c == '{') {
                    arr[len] = c;
                    //前进指针
                    len++;
                } else if (len > 0) {//如果有元素
                    //三个判断
                    //注意是arr不是s.charAt哦
                    if (c == ')' && arr[len - 1] != '(') {
                        return false;
                    }
                    if (c == ']' && arr[len - 1] != '[') {
                        return false;
                    }
                    if (c == '}' && arr[len - 1] != '{') {
                        return false;
                    }
                    //回退指针
                    len--;
                } else {
                    return false;
                }
            }
            //是否==0
            return len == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
