package conquerdp;

/**
 * @author lufengxiang
 * @since 2021/5/14
 **/
public class Roman {
    static class MySolution {
        public static void main(String[] args) {
            System.out.println(intToRoman(89));
        }

        public static String intToRoman(int num) {
            int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] dic = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numbers.length; i++) {
                if (num >= numbers[i]) {
                    int count = num / numbers[i];
                    for (int j = 0; j < count; j++) {
                        sb.append(dic[i]);
                    }
                    num -= numbers[i] * count;
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Roman solution = new Roman();
        System.out.println(solution.intToRoman(89));
    }

    //12. 整数转罗马数字
    public String intToRoman(int num) {
        //维护两个数组
        int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] dic = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        //循环
        for (int i = 0; i < numbers.length; i++) {
            //找到最大的值
            if (num >= numbers[i]) {
                //判断需要处理几次
                int count = num / numbers[i];
                for (int j = 0; j < count; j++) {
                    sb.append(dic[i]);
                    //减
                    num = num - numbers[i];
                }
            }
        }
        return sb.toString();
    }

    public int romanToInt(String s) {
        //返回
        int sum = 0;
        //前一个数字
        int preNum = getInt(s.charAt(0));
        //
        for (int i = 1; i < s.length(); i++) {
            //获取当前的值
            int num = getInt(s.charAt(i));
            //小则减
            if (preNum < num) {
                sum -= preNum;
            } else {
                //大则加
                sum += preNum;
            }
            //更新preNum
            preNum = num;
        }
        return sum + preNum;
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
