package solution.leetcode.editor.cn;

public class LeetCode12 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        public String intToRoman(int num) {
            //900,400,40要维护一下
            int[] numbers = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] dic = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numbers.length; i++) {
                if (num >= numbers[i]) {
                    int count = num / numbers[i];
                    for (int j = 0; j < count; j++) {
                        sb.append(dic[i]);
                        num = num - numbers[i];
                    }
                }
            }
            return sb.toString();
        }

        public static void main(String[] args) {
            Solution solution = new Solution();
            System.out.println(solution.intToRoman(3999));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
