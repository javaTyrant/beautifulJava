package strings;

/**
 * @author lumac
 * @since 2020/6/10
 */
public class IntegerPa {
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int temp = x;
        //比如是0
        int y = 0;
        while (x > 0) {
            //每次乘10
            y *= 10;
            //取余累加
            y += x % 10;
            x /= 10;
        }
        return temp == y;
    }

    public static void main(String[] args) {
        //问题1,给一个数字1234567,如果逆序一下
        System.out.println(isPalindrome(787));
        System.out.println(reverse(12345));
    }

    public static int reverse(int n) {
        int y = 0;
        while (n > 0) {
            y *= 10;
            y += n % 10;
            n = n / 10;
        }
        return y;
    }

}
