package binarysearch;

/**
 * @author lumac
 * @since 2020-05-11
 */
public class MyPow {
    public static void main(String[] args) {
        System.out.println(myPow(2, 9));
    }
    //-100.0 < x < 100.0
    //-231 <= n <= 231-1
    //-104 <= xn <= 104

    //8 4 2 1
    public static double myPow(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            //奇数 = 乘一次 i % 2 == -1 || i % 2 == 1
            if ((i & 1) != 0) {
                res *= x;
            }
            //快速幂
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }
}
