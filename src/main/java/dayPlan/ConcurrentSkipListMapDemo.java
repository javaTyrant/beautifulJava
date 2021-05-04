package dayPlan;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author lumac
 * @since 2021/3/13
 */
public class ConcurrentSkipListMapDemo {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new ConcurrentSkipListMap<>();
        for (int i = 0; i < 64; i++) {
            map.put(i, i);
        }
        System.out.println(map.get(1));
    }
}
