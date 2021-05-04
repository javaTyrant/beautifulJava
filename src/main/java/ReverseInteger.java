/**
 * @author lumac
 * @since 2020/6/30
 */
public class ReverseInteger {
    public static int reverse(int x) {
        //用long规避溢出
        long n = 0;
        while (x != 0) {
            //基本功
            n = n * 10 + x % 10;
            x = x / 10;
        }
        //(int n) == n处理溢出
        System.out.println(n);
        return (int) n == n ? (int) n : 0;
    }

    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }
}
