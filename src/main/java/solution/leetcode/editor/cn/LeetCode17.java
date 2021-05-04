package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode17 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //17. 电话号码的字母组合
        public static List<String> letterCombinations(String digits) {
            //
            if (digits.length() == 0) return new ArrayList<>();
            char[][] numChar = new char[][]{
                    {'a', 'b', 'c'},
                    {'d', 'e', 'f'},
                    {'g', 'h', 'i'},
                    {'j', 'k', 'l'},
                    {'m', 'n', 'o'},
                    {'p', 'q', 'r', 's'},
                    {'t', 'u', 'v'},
                    {'w', 'x', 'y', 'z'}
            };
            //结果
            List<String> result = new ArrayList<>();
            //tem
            char[] tem = new char[digits.length()];
            //
            char[][] temp = new char[digits.length()][];
            for (int i = 0; i < digits.length(); i++) {
                temp[i] = numChar[digits.charAt(i) - '2'];
            }
            build(result, temp, tem, 0);
            return result;

        }

        private static void build(List<String> result, char[][] temp, char[] tem, int idx) {
            for (char tt : temp[idx]) {
                tem[idx] = tt;
                if (idx == temp.length - 1) {
                    result.add(new String(tem));
                } else {
                    build(result, temp, tem, idx + 1);
                }
            }
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            solution.letterCombinationsEasy("23");
        }

        //
        public List<String> letterCombinationsEasy(String digits) {
            List<String> combinations = new ArrayList<>();
            if (digits.length() == 0) {
                return combinations;
            }
            //构建字典表
            Map<Character, String> phoneMap = new HashMap<Character, String>() {{
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }};
            //
            backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
            return combinations;
        }

        public void backtrack(List<String> combinations,
                              Map<Character, String> phoneMap,
                              String digits, int index,
                              StringBuffer combination) {
            //base case index
            if (index == digits.length()) {
                combinations.add(combination.toString());
            } else {
                //获取当前的数字
                char digit = digits.charAt(index);
                //获取当前的字母集合
                String letters = phoneMap.get(digit);
                //长度
                int lettersCount = letters.length();
                //回溯
                for (int i = 0; i < lettersCount; i++) {
                    //add
                    combination.append(letters.charAt(i));
                    backtrack(combinations, phoneMap, digits, index + 1, combination);
                    //remove
                    combination.deleteCharAt(index);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
