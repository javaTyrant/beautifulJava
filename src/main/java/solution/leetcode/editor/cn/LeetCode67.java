package solution.leetcode.editor.cn;

public class LeetCode67 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //输入: a = "11", b = "1"
        //输出: "100"
        public static String addBinary(String a, String b) {
            int p1 = a.length() - 1;
            int p2 = b.length() - 1;
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            while (p1 >= 0 || p2 >= 0) {
                int charA = p1 >= 0 ? a.charAt(p1) - '0' : 0;
                int charB = p2 >= 0 ? b.charAt(p2) - '0' : 0;
                int sum = charA + charB + carry;
                //是不是可以简化成一行代码
                //if (sum >= 2) carry = 1;
                //else carry = 0;
                carry = sum / 2;
                sb.append(sum % 2);
                if (p1 >= 0) p1--;
                if (p2 >= 0) p2--;
            }
            if (carry > 0) sb.append(1);
            return sb.reverse().toString();
        }

        public static void main(String[] args) {
            System.out.println(addBinary("1010", "1011"));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
