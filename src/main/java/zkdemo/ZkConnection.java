package zkdemo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lumac
 * @since 2020/8/9
 */
public class ZkConnection {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        System.out.println(add(1));
        System.out.println(add(2));
        System.out.println(add(100));
    }

    public static double add(int n) {
        double res = 0;
        //1-1/2+1/3-.......-1/100
        for (double i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                res -= 1 / i;
            } else {
                res += 1 / i;
            }
        }
        return res;
    }
}
