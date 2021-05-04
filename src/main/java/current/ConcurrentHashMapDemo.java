package current;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author lumac
 * @since 2020/8/19
 */
public class ConcurrentHashMapDemo {
    static class Key {
        @Override
        public int hashCode() {
            return 1;
        }
    }

    public static void main(String[] args) {
//        HashMap<Key, Integer> hashMap = new HashMap<>(16);
//        for (int i = 0; i < 65; i++) {
//            hashMap.put(new Key(), i);
//        }
//        ExecutorService service = Executors.newFixedThreadPool(10);
//        Map<String, Integer> map = new ConcurrentHashMap<>();
//        for (int i = 0; i < 10000; i++) {
//            int finalI = i;
//            service.execute(() -> map.put("数学" + finalI, finalI));
//        }
        List<String> list = new ArrayList<>();
        list.add("服装加工3部");
        list.add("服装加工1部");
        list.add("服装加工2部");
        list.add("服装加工4部");
        List<String> sorted = list.stream().sorted().collect(Collectors.toList());
        System.out.println(sorted);
    }
}
