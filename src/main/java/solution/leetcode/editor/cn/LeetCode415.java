package solution.leetcode.editor.cn;

public class LeetCode415 {
    static class Solution {
        public static String addStrings(String num1, String num2) {
            int i = num1.length() - 1;
            int j = num2.length() - 1;
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            while (i >= 0 || j >= 0) {
                int a = i >= 0 ? num1.charAt(i) - '0' : 0;
                int b = j >= 0 ? num2.charAt(j) - '0' : 0;
                int sum = a + b + carry;
                sb.append(sum % 10);
                carry = sum / 10;
                j--;
                i--;
            }
            if(carry >= 0) sb.append(carry);
            sb.reverse();
            return sb.toString();
        }

        public static void main(String[] args) {
            System.out.println(addStrings("9", "1"));
        }
    }

}
