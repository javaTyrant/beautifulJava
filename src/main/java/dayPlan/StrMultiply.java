package dayPlan;

/**
 * @author lufengxiang
 * @since 2021/5/14
 **/
public class StrMultiply {
    public static void main(String[] args) {
        StrMultiply solution = new StrMultiply();
        solution.multiply("123", "456");
    }

    //字符串相乘
    public String multiply(String num1, String num2) {
        int sumLen = num1.length() + num2.length();
        //用数组
        int[] res = new int[sumLen];
        for (int i = 0; i < num1.length(); i++) {
            int num11 = num1.charAt(num1.length() - 1 - i) - '0';//3
            for (int j = 0; j < num2.length(); j++) {
                int num22 = num2.charAt(num2.length() - 1 - j) - '0';//6,5,4
                res[i + j] += num11 * num22;            //序列和相同相加
            }
        }
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
}
