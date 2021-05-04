package solution.leetcode.editor.cn;

public class LeetCode43 {
    //leetcode submit region begin(Prohibit modification and deletion)
    static class Solution {
        //思路要清楚
        public static String multiplyOpt(String num1, String num2) {
            int sumLen = num1.length() + num2.length();
            int[] res = new int[sumLen];
            for (int i = 0; i < num1.length(); i++) {
                int a = num1.charAt(num1.length() - 1 - i) - '0';//3
                for (int j = 0; j < num2.length(); j++) {
                    int b = num2.charAt(num2.length() - 1 - j) - '0';//6,5,4
                    res[i + j] += a * b;            //序列和相同相加
                }
            }
            //数组处理
            for (int i = 0; i < res.length - 1; i++) {
                if (res[i] >= 10) {
                    res[i + 1] += res[i] / 10;//后位加上
                    res[i] %= 10;//余数
                }
            }
            int i = res.length - 1;
            while (i > 0 && res[i] == 0) {
                i--;
            } // 去除结果前面的 0
            StringBuilder sb = new StringBuilder();
            for (; i >= 0; i--) {
                sb.append(res[i]);
            }
            return sb.toString();
        }

        public static void main(String[] args) {
            System.out.println(multiply("123", "345"));
            System.out.println(multiplyOpt("123", "345"));
        }

        //牛逼的解法
        public static String multiply(String num1, String num2) {
            //基本的判断
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            //保存两个长度
            int m = num1.length(), n = num2.length();
            //保存每次结果的数组
            int[] ansArr = new int[m + n];
            //
            for (int i = m - 1; i >= 0; i--) {
                //用数组来消除*10的操作
                int x = num1.charAt(i) - '0';
                for (int j = n - 1; j >= 0; j--) {
                    int y = num2.charAt(j) - '0';
                    //累加
                    ansArr[i + j + 1] += x * y;
                }
            }
            //统一处理,让每个数组保存一位
            for (int i = m + n - 1; i > 0; i--) {
                // /10 %10基本操作
                ansArr[i - 1] += ansArr[i] / 10;
                ansArr[i] %= 10;
            }
            int index = ansArr[0] == 0 ? 1 : 0;
            //转string
            StringBuffer ans = new StringBuffer();
            while (index < m + n) {
                ans.append(ansArr[index]);
                index++;
            }
            return ans.toString();
        }
    }

    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    //leetcode submit region end(Prohibit modification and deletion)
    static class MySolution {
        public static String multiply(String num1, String num2) {
            if (num1.equals("0") || num2.equals("0")) {
                return "0";
            }
            int n = num1.length();
            int m = num2.length();
            int[] res = new int[m + n];
            return "";
        }
    }
}
