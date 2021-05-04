package current;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lumac
 * @since 2020/11/11
 */
public class ConcurrentHashMapTransferDemo {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        int i = 0;
        if (--i >= 0) {
            System.out.println("yea");
        } else {
            System.out.println("减了");
        }
    }
}
