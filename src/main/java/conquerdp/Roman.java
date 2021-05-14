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
}
